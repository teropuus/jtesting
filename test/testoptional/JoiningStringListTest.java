package testoptional;

import static org.junit.Assert.assertEquals;

import java.util.function.Function;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.Objects;

import org.junit.Test;
import org.junit.Before;

public class JoiningStringListTest {

    List<String> ducks ;

    public static <T> List<T> optionalAsList(Optional<T> optionalVal) {
    return optionalVal.
            map(Collections::singletonList).
            orElse(Collections.emptyList());
    }

    @Before
    public void setUp() {
           ducks = Arrays.asList("Duck, Donald", "Mouse, Mickey", "Goofy", "Black, Pete", null, null);
    }

    @Test
    public void testJoiningNonNullStrings() {
        // ignore null values using filter which ignore all null values. 
        // Objects.nonNull() returns true if the provided reference is non-null otherwise returns false.
        String ducksJoined = ducks.stream().filter(Objects::nonNull).collect(Collectors.joining(" and "));
        System.out.println(ducksJoined);
           assertEquals("Duck, Donald and Mouse, Mickey and Goofy and Black, Pete",
            ducksJoined);
    }
    
    @Test
    public void testReplaceNullValuesWithString() {
        // replace NULL values with string [null]. 
        // Using map method with suitable map function to replace null values

        Function<String, String> replaceNulls = str -> { if (str == null) return "[null]"; else return str; };
        
        String ducksJoined = ducks.stream()
                .map(replaceNulls)
                .collect(Collectors.joining(" and "));

        System.out.println(ducksJoined);
        assertEquals("Duck, Donald and Mouse, Mickey and Goofy and Black, Pete and [null] and [null]",
            ducksJoined);
    }

    @Test
    public void testOptional() {
        // Creating List of Optional String values
        // so you safely check if there is value present or if it's empty or null
        // this is implemented in the Function

        Function<Optional<String>, String> replaceNulls = opt -> { if (opt.isPresent()) return opt.get(); return "[NullOrEmpty]"; };

        List<Optional<String>> ducksOptional = new ArrayList<Optional<String>>();
        ducksOptional.add(Optional.of(new String("Duck, Donald")));
        ducksOptional.add(Optional.ofNullable(null));
        ducksOptional.add(Optional.of(new String("Mouse, Mickey")));
        ducksOptional.add(Optional.empty());
        ducksOptional.add(Optional.of(new String("Goofy")));
        ducksOptional.add(Optional.ofNullable(null));


        String ducksJoined = ducksOptional.stream()
                .map(replaceNulls)
                .collect(Collectors.joining(" and "));

        System.out.println(ducksJoined);
        assertEquals("Duck, Donald and [NullOrEmpty] and Mouse, Mickey and [NullOrEmpty] and Goofy and [NullOrEmpty]",
            ducksJoined);

        // return Optional values as list and print the result

        List<List<String>> ducksAsList = ducksOptional.stream()
                .map(opt -> optionalAsList(opt))
                .collect(Collectors.toList());
        System.out.println(ducksAsList);
    }



}