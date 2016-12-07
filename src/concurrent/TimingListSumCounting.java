package concurrent;

import java.util.stream.*;

/*
*  Comparing different ways to count sum.
*  The program prints also execution time of counting sum. 
*
* @author Juha Peltomäki
*/

public class TimingListSumCounting {
  public final static int max = 10000000;

  public static long iteration() {
    long res = 0;
    for (long i=0; i<=max; i++)
      res = res + i;    
    return res;
  } 

 public static long sequential() {
    return Stream.iterate(1L, i -> i+1).
    limit(max).reduce(Long::sum).get();
 }

public static long parallel() {
    return Stream.iterate(1L, i -> i+1).parallel().
    limit(max).reduce(Long::sum).get();
 }

public static long rangedSequential() {
    return LongStream.rangeClosed(1L, max).
    reduce(Long::sum).getAsLong();
 }

 public static long rangedParallel() {
    return LongStream.rangeClosed(1L, max).parallel().
    reduce(Long::sum).getAsLong();
  }

  public static void main(String[] args) {
    long startTime = System.currentTimeMillis();
    long sum = iteration();
    long endTime = System.currentTimeMillis() - startTime;      
    System.out.println("Time for iterative sum: " + endTime + " and sum " + sum);

    startTime = System.currentTimeMillis();
    sum = sequential();
    endTime = System.currentTimeMillis() - startTime;      
    System.out.println("Time for sequential sum: " + endTime+ " and sum " + sum);

    startTime = System.currentTimeMillis();
    sum = parallel();
    endTime = System.currentTimeMillis() - startTime;      
    System.out.println("Time for parallel sum: " + endTime+ " and sum " + sum);
    
    startTime = System.currentTimeMillis();
    sum = rangedSequential();
    endTime = System.currentTimeMillis() - startTime;      
    System.out.println("Time for rangedSequential sum: " + endTime+ " and sum " + sum);
    
    startTime = System.currentTimeMillis();
    sum = rangedParallel();
    endTime = System.currentTimeMillis() - startTime;      
    System.out.println("Time for rangedParallel sum: " + endTime+ " and sum " + sum);
  }
}
