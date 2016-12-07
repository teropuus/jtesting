package lambdas;

import java.util.*;

/*
Example of Using reduce method with Double streams. 
@author   Juha Peltomäki
*/

public class ReduceNumbers {

    public static void main(String args[]) {

        ArrayList<Double> numbers = new ArrayList<>();
        numbers.add(12.5);
        numbers.add(22.4);
        numbers.add(9.2);
        numbers.add(31.5);
        numbers.add((double)20);
        numbers.add(4.5);

        Double max = 
                numbers.stream()
                .reduce(Double.MIN_VALUE, Double::max);
        Double res = 
                numbers.stream()
                .reduce(new Double(1), (Double n1, Double n2) -> n1 * n2);
        Double sum = 
                numbers.stream()
                .reduce(new Double(0), (n1, n2) -> n1 + n2 );
                
        System.out.println("max: " + max);
        System.out.println("mult:" + res);
        System.out.println("sum: " + sum);

    }
}



