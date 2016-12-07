/**
 * @author Juha Peltomäki
 *
 *
 * Using lambda expressions of sorting Employees in List. Note the amount of
 * verbosity which has been reduced by using the Lambda expressions. Also the
 * code is much more clearer now than it was when we used Anonymous inner
 * classes.
 */
package lambdas;

import java.util.*;

class Employee {

    public Employee(String fn, String ln) {
        first = fn;
        last = ln;
    }
    public String first, last;

    @Override
    public String toString() {
        return first + " " + last;
    }
}

public class LambdaSortCollection {

    public static void sortEmployees() {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee("Donald", "Duck"));
        list.add(new Employee("Mickey", "Mouse"));
        list.add(new Employee("Bill", "Gates"));
        list.add(new Employee("Steven", "Elop"));
        list.add(new Employee("Jack", "Nicklaus"));

        //sorting with Lambda expression. 
        Collections.sort(list, (Employee p1, Employee p2) -> p1.first.compareTo(p2.first));
        System.out.println("Collection sorted by first name:");
        System.out.println(list);
        //Lambda expression with type information removed.
        Collections.sort(list, (p1, p2) -> p2.last.compareTo(p1.last));
        System.out.println("Collection reverse sorted by last name:");
        System.out.println(list);
    }

    public static void main(String args[]) {
        sortEmployees();
    }
}
