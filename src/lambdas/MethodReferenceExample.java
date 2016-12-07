package lambdas;

import java.util.function.*;
/*
* Example Shows some defined methods used as lambdas and method reference 
*/

public class MethodReferenceExample {
 
    public int sum(int a, int b) {
        return a + b;
    }
 
    public static int multiply(int a, int b) {
        return a * b;
    }
 
    public String operateString(Function<String, String> operator, String a) {
        return operator.apply(a);
    }
 
    public int operateInt(IntBinaryOperator operator, int a, int b) {
        return operator.applyAsInt(a, b);
    }
 
 }      
