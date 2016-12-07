package concurrent;


import java.util.*;

/* Example: parallel processing of items in the stream
 Do printing when data structure is processed as a parallel stream. 
   See the order of printing in each case.
   
   @author   Juha Peltomäki
*/

public class ParallelStreamPrinting
{
    public static void main(String[] args)
    {
        List<String> list = Arrays.asList("D", "E", "C", "B", "A", "F", "D", "B", "A");

        System.out.println("parallelStream: forEach - ordered and distinct set of items");
        list.parallelStream() // do parallel processing
            .distinct() // remove duplicates
            .sorted() 
            .forEach(System.out::println); 

        System.out.println("parallelStream: forEach - printed in sequential processing order");
        list.parallelStream() // do sequential processing
            .distinct() // remove duplicates
            .sorted() 
            .sequential() // change the order before printing items
            .forEach(System.out::println); 


        System.out.println("parallelStream: forEachOrdered - ordered and distinct set of items");

        list.parallelStream() // do parallel processing
            .distinct() 
            .sorted() 
            .forEachOrdered(System.out::println);        


    }
}

/*
Sample output:

parallelStream: forEach - ordered and distinct set of items
D
F
E
B
A
C
parallelStream: forEach - printed in sequential processing order
A
B
C
D
E
F
parallelStream: forEachOrdered - ordered and distinct set of items
A
B
C
D
E
F

*/