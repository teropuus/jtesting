package lambdas;

import java.util.*;
import java.util.stream.Collectors;

/* 
 Example: 
 Comparing of Using function and lambda expression to create 
 Comma separated String from the Integer array.
 The classes needed are Collectors and StringJoiner.
*/ 

public class ListToString {
    // How to create comma separated string from the Integer array before Java 8
    public static String createString(List<Integer> vals) {
        StringBuilder builder = new StringBuilder();
        for ( int i = 0; i< vals.size(); i++){
            builder.append(vals.get(i));
            if ( i != vals.size()-1) builder.append(", ");
        }
        return builder.toString();
    }
    
    // new Java SE 8 lambda based way to create Comma separated String from the Integer array
    public static String createStringWithLambdas(List<Integer> vals) {
        return vals.parallelStream()
            .map(n -> String.valueOf(n*n))
            .collect(Collectors.joining(", "));

    }


    public static void main(String args[]) {
       List<Integer> vals = Arrays.asList(11,21,33,100,150,200);
       System.out.println(createString(vals));
       System.out.println(createStringWithLambdas(vals));
    }

}
