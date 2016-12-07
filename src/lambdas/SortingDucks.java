package lambdas;

import java.util.*;
import java.util.function.*;

/*
Sorting ducks using anonymous inner class (Java 7) and
using lambdas and method references. 

@Author Juha Peltomäki

*/

public class SortingDucks {
    
    private static final Person[] ducks = new Person[] {
        new Person("Donald", "Duck"),
        new Person("Mickey", "Mouse"),
        new Person("Huey", "Duck"),
        new Person("Dewey", "Duck"),
        new Person("Louie", "Duck"),
        new Person("Elvira", "Duck"),
        new Person("Scrooge", "McDuck"),
        new Person("Gladstone", "Gander"),
        new Person("Gus", "Goose")
    };
    
    private static void printPersons(String title, Person[] ducks) {
        System.out.println(title);
        Arrays.asList(ducks).stream().forEach(s -> System.out.println(s));
    }

    public static void main(String[] args) {

        // Traditional sorting with Anonymous inner class
        Person[] sortedDucks = Arrays.copyOf(ducks, ducks.length);
        Arrays.sort(sortedDucks, new Comparator<Person>() {
            @Override
            public int compare(Person a, Person b) {
                return a.compareTo(b);
            }
        });
        printPersons("Persons sorted with anonymous inner class:", sortedDucks);

        
        // Java 8  - sort array using lambda expression
        sortedDucks = Arrays.copyOf(ducks, ducks.length);
        Arrays.sort(sortedDucks, (Person a, Person b) -> a.compareTo(b));
        // or simpler: by not specifying the type definition which compiler can detect
        // Arrays.sort(sortedDucks, (a, b) -> a.compareTo(b));
        printPersons("Persons sorted with lambda expression:", sortedDucks);
     


        // sort array using key-extractor lambdas
        // Now comparison logic – first sorting by surname and then firstname 
        // Sorting is implemented using the new composition support (thenComparing) for Comparator.
        sortedDucks = Arrays.copyOf(ducks, ducks.length);
        Comparator<Person> personComparator = Comparator.comparing((Person duck1) -> duck1.getSurname()).thenComparing(duck2 -> duck2.getFirstname());
        Arrays.sort(sortedDucks, personComparator);
        printPersons("Persons sorted with comparator", sortedDucks);


         // sort array passing method references
        sortedDucks = Arrays.copyOf(ducks, ducks.length);
        personComparator = Comparator.comparing(Person::getSurname).thenComparing(Person::getFirstname);
        Arrays.sort(sortedDucks, personComparator);
        printPersons("Persons sorted with existing methods", sortedDucks);


        // Create arrayList of ducks
        List<Person> list = new ArrayList<>();
        for (Person p : ducks) {
            list.add(p);
        }


        // Using same Comparator but in reversed order
        System.out.println("Persons Sorted in ArrayList and in reverse order");
        list.stream().sorted(personComparator.reversed()).forEach(s -> System.out.println(s));


        // Define predicates to remove ducks
        Predicate<Person> predFirstname = duck -> "Gus".equals(duck.getFirstname());
        Predicate<Person> predSurname = duck -> "Duck".equals(duck.getSurname());
        Predicate<Person> predSurname2 = duck -> "Gander".equals(duck.getSurname());
        // Remove Ducks if predicate is true
        list.removeIf( predFirstname.or(predSurname).or(predSurname2) );
        
        printPersons("Persons removed by predicate", list.toArray(new Person[list.size()]));

    }
}