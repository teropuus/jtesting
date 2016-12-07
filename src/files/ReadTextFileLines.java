package files;

import java.util.stream.*;
import java.nio.file.*;
import java.util.concurrent.atomic.AtomicInteger;

/* 
 @author Juha Peltomäki 
 Example: printing text lines with line numbers.
 Using AtomicInteger and opens file streams safely way with try.
*/

public class ReadTextFileLines {

    public static void main(String[] args) throws Exception {

      Path path = Paths.get("koe.txt");


      System.out.println("Printing all lines in the text file");

      AtomicInteger index = new AtomicInteger();
      

      //Files.lines(path).map(s -> index.incrementAndGet() + ":" + s ).forEach(System.out::println);

      // When lines is closed, it closes stream as well as file it is using.
      try ( Stream<String> lines = Files.lines(path)) {
         lines.map(s -> index.incrementAndGet() + ":" + s ).forEach(System.out::println);
      }


    }
}