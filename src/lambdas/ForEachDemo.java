/**
 * @author Juha Peltomäki
 *
 *
 * ForEach use cases with lambda functions in Collection.
 */
package lambdas;

import java.util.*;

public class ForEachDemo {

    public static void main(String[] args) {
        Integer arr[] = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List<Integer> numbers = Arrays.asList(arr);

        System.out.println("sqrt for filtered even numbers with traditional for:");
        for (Integer i : numbers) {
            if (i % 2 == 0) {
                System.out.print(i + "=>" + i * i + ", ");
            }
        }
               
        System.out.println();

        System.out.println("sqrt for filtered even numbers with lambda forEach:");

        numbers.stream()
                .filter(i -> i % 2 == 0)
                .forEach(i -> System.out.print(i + "=>" + i * i + ", ")
                );
        System.out.println();

        System.out.println("sqrt for filtered odd numbers with map:");

        numbers.stream()
                .filter(i -> i % 2 != 0)
                .map(i -> i * i)
                .forEach(i -> System.out.print(i + ", ")
                );

        System.out.println();

    }
}
