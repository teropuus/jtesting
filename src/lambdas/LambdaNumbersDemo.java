/**
 * @author Juha Peltomäki
 *
 *
 * Example of Using binary lambda expression operations which returns the value.
 */
package lambdas;

import java.util.function.*;

public class LambdaNumbersDemo {

    public static void main(String[] args) {
        System.out.println("Lambda examples: ");

        BinaryOperator<Integer> sumOrMultiply = (a, b) -> {
            if (a > b) {
                return a + b;
            } else {
                return a * b;
            }
        };

        BinaryOperator<Integer> maxnumber = (a, b) -> (a > b) ? a : b;

        int sum = sumOrMultiply.apply(30, 20);
        int multiply = sumOrMultiply.apply(10, 12);
        int max = maxnumber.apply(30, 25);

        System.out.println("sumOrMultiply(30,20): " + sum);
        System.out.println("sumOrMultiply(10,12): " + multiply);
        System.out.println("Max number of(30,25) is: " + max);

    }
}

