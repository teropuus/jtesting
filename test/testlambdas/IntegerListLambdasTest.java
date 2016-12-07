package testlambdas;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.Before;
    
public class IntegerListLambdasTest {    
    List<Integer> values;

    @Before
    public void setUp() {
        values = Arrays.asList(5, 10, 15, 20, 25);
    }

    @Test 
    public void countTraditionally() {
    
        List<Integer> result = new ArrayList<> (values.size());
        for (int i : values) result.add(2 * i);

        System.out.println(result);
        assertEquals(new Integer(20), (Integer)result.get(1));
        assertEquals(5, result.size());
    }

    @Test 
    public void countUsingLambdas() {
        // same implementation using Java 8 lambdas
    
        List<Integer> result = values.stream()
                         .map(i -> 2 * i)
                         .collect(Collectors.toList());

        System.out.println(result);
        assertEquals(new Integer(30), (Integer)result.get(2));
        assertEquals(5, result.size());
    }

}