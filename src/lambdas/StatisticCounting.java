/**
 * @author Juha Peltomäki
 *
 */
package lambdas;

import java.util.*;
import java.util.stream.Collectors;

/*
Demo of using IntSummaryStatistics class 
It is used for collecting statistics such as count, min, max, sum, and average and combine statistics: 
  Counting new values to stream.
  Count min, max, sum, and average for numbers in the list.

@Author Juha Peltomäki

*/

public class StatisticCounting {

    public static void main(String[] args) {
        
        // Create List of square of all distinct numbers
        List<Integer> radiuses = Arrays.asList(5,6,5,6,8,9,8,9,12);
        // count area which is PI times radius squared
        List<Double> all = radiuses.stream().map( x -> Math.PI*x*x).collect(Collectors.toList());
        List<Double> distinct = radiuses.stream().map( x -> Math.PI*x*x).distinct().collect(Collectors.toList());
        System.out.printf("areas: %s \n", all);
        System.out.printf("no duplicates : %s \n", distinct);
     
        //Count min, max, sum, and average for numbers in the list        
        List<Integer> radiuses2 = Arrays.asList(2,12,4,15,7);
        IntSummaryStatistics stats = radiuses.stream().mapToInt((x) -> x).summaryStatistics();
        IntSummaryStatistics stats2 = radiuses2.stream().mapToInt((x) -> x).summaryStatistics();
        stats.combine(stats2);   // Combines another IntSummaryStatistics into stats

        System.out.printf("Highest: %d, lowest: %d, count: %d and sum: %d \n", stats.getMax(), stats.getMin(), stats.getCount(), stats.getSum()); 
        System.out.printf("Avg: %f \n", stats.getAverage() );

    }
}

/*
Output: 

areas: [78.53981633974483, 113.09733552923255, 78.53981633974483, 113.09733552923255, 201.06192982974676, 254.46900494077323, 201.06192982974676, 254.46900494077323, 452.3893421169302] 
no duplicates : [78.53981633974483, 113.09733552923255, 201.06192982974676, 254.46900494077323, 452.3893421169302] 
Highest: 15, lowest: 2, count: 14 and sum: 108 
Avg: 7.714286 

*/

