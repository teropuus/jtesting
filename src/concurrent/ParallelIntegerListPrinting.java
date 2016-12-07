package concurrent;


import java.util.*;
import java.util.stream.Collectors;

/* Example: Parallel processing of items in the stream
 Do printing when integer list stream data structure is processed as a parallel stream. 
   See the order of printing

  Print the elements of the list in an apparently random order. Remember that stream operations use internal iteration when processing elements of a stream. 
  Consequently, when you execute a stream in parallel, the Java compiler and runtime determine the order in which to process the stream's elements to maximize the benefits of parallel computing unless otherwise specified by the stream operation.

   forEachOrdered process the items of the stream in the order specified by its source, regardless of whether you executed the stream in serial or parallel. 
   You may lose the benefits of parallelism if you use operations like forEachOrdered with parallel streams.

   @author   Juha Peltomäki
*/

public class ParallelIntegerListPrinting
{
    public static void main(String[] args)
    {
        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        List<Integer> listOfIntegers =
            new ArrayList<>(Arrays.asList(intArray));

        System.out.println("listOfIntegers:");
        listOfIntegers
            .stream()
            .forEach(e -> System.out.print(e + " "));
        System.out.println("");

        System.out.println("listOfIntegers sorted in reverse order:");
        Comparator<Integer> normal = Integer::compare;
        Comparator<Integer> reversed = normal.reversed(); 
        Collections.sort(listOfIntegers, reversed);  
        listOfIntegers
            .stream()
            .forEach(e -> System.out.print(e + " "));
        System.out.println("");
             
        System.out.println("Parallel stream");
        listOfIntegers
            .parallelStream()
            .forEach(e -> System.out.print(e + " "));
        System.out.println("");
            
        System.out.println("Another parallel stream:");
        listOfIntegers
            .parallelStream()
            .forEach(e -> System.out.print(e + " "));
        System.out.println("");
             
        System.out.println("With forEachOrdered:");
        listOfIntegers
            .parallelStream()
            .forEachOrdered(e -> System.out.print(e + " "));
        System.out.println("");

        // interference

        try {
        List<String> listOfStrings =
            new ArrayList<>(Arrays.asList("one", "two"));
             
        // This will fail as the peek operation will attempt to add the
        // string "three" to the source after the terminal operation has
        // commenced. 
                 
        String concatenatedString = listOfStrings
            .stream()
            
            // Don't do this! Interference occurs here.
            .peek(s -> listOfStrings.add("three"))
            
            .reduce((a, b) -> a + " " + b)
            .get();
                     
        System.out.println("Concatenated string: " + concatenatedString);
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.toString());
        }     
               
        // Stateful Lambda Expressions
        try {

        List<Integer> serialStorage = new ArrayList<>();
             
        System.out.println("Serial stream:");
        listOfIntegers
            .stream()
            
            // Don't do this! It uses a stateful lambda expression.
            .map(e -> { serialStorage.add(e); return e; })
            
            .forEachOrdered(e -> System.out.print(e + " "));
        System.out.println("");
             
        serialStorage
            .stream()
            .forEachOrdered(e -> System.out.print(e + " "));
        System.out.println("");

        System.out.println("Parallel stream:");
        List<Integer> parallelStorage = Collections.synchronizedList(new ArrayList<>());
        
        listOfIntegers
            .parallelStream()
            
            // Don't do this! It uses a stateful lambda expression.
            .map(e -> { parallelStorage.add(e); return e; })
            // when run in parallel and using statefull operation results can vary. So avoid statefull operation in lambda expression
            .forEachOrdered(e -> System.out.print(e + " "));
        System.out.println("");
             
        parallelStorage
            .stream()
            .forEachOrdered(e -> System.out.print(e + " "));
        System.out.println("");

        } catch (Exception e) {
            System.out.println("Exception caught: " + e.toString());
        }


        // Correct way!
        // Stateless lambda expressions used to create new collection
        List<Double> newDoubleList = listOfIntegers.parallelStream()
            .filter(x -> x >= 5)
            .map(e -> new Double(e))           
            .sorted(Collections.reverseOrder())
            .collect(Collectors.toList());

        System.out.println("New Collection created with stateless lambdas");
        newDoubleList
            .stream()
            .forEach(e -> System.out.print(e + " "));
        System.out.println("");


    }
}

/*

listOfIntegers:
1 2 3 4 5 6 7 8 9 10 
listOfIntegers sorted in reverse order:
10 9 8 7 6 5 4 3 2 1 
Parallel stream
5 4 2 1 3 8 7 6 10 9 
Another parallel stream:
5 4 2 1 3 8 7 6 10 9 
With forEachOrdered:
10 9 8 7 6 5 4 3 2 1 
Exception caught: java.util.ConcurrentModificationException
Serial stream:
10 9 8 7 6 5 4 3 2 1 
10 9 8 7 6 5 4 3 2 1 
Parallel stream:
10 9 8 7 6 5 4 3 2 1 
8 7 6 10 9 2 1 3 5 4 
New Collection created with stateless lambdas
10.0 9.0 8.0 7.0 6.0 5.0 




*/