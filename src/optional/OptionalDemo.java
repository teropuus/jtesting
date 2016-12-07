/**
 * @author Juha Peltomäki
 *
 *
 * Some use cases with Optional types and lambda expressions. Optional act as a
 * container that may or may not contain some value. Just like all references in
 * Java can point to some object or be null, Option may enclose some (non-null!)
 * reference or be empty.
 */
package optional;

import java.util.*;
import java.util.function.*;

/* 
 */
public class OptionalDemo {

    public static void main(String[] args) {

        final Function<String, Predicate<String>> startsWithLetter
                = letter -> name -> name.startsWith(letter);

        List<String> names = Arrays.asList("Bob", "Juha", "John", "Steven", "Jack", "Bill", "Bert");

        Optional<String> jname = Optional.of("java se 8");
        Optional<String> upperName = jname.map(v -> v.toUpperCase());
        System.out.println(upperName.orElse("No value found"));

        jname = Optional.ofNullable(null);
        upperName = jname.map(v -> v.toUpperCase());
        System.out.println(upperName.orElse("No value found"));
        System.out.println(upperName);

        //Example where the value fails when the condition (string length greater than 10) passed to filter method.
        Optional<String> test = Optional.of("thebook");
        Optional<String> shortName = test.filter(v -> v.length() > 10);
        System.out.println(shortName.orElse("The name is less than 10 characters"));
        for (String name : names) {
            test = Optional.of(name);
            shortName = test.filter(startsWithLetter.apply("B"));
            System.out.println(shortName.orElse("The name does not start with 'B' letter"));
            if (shortName.isPresent()) {
                //Invoking get method returns the value present within the Optional instance.
                System.out.println(shortName.get());
            }
        }

    }
}

