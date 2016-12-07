package testlambdas;


import org.junit.*;
import java.util.*;
import java.util.stream.*;


import static org.junit.Assert.assertEquals;

/* 
 Examples: shows Lambda expressions 
*/


public class LambdaExpressionTest {

   final static List<Integer> numbers = Arrays.asList(5,10,15,20,11,16,21,26,25,3);
  
    @Test
    public void sortNumbers() {

        numbers.sort( (i1, i2) -> i1 - i2 );        

        assertEquals(numbers.get(0), (Integer)3);
        assertEquals(numbers.get(numbers.size()-1), (Integer)26);
    }


    @Test 
    public void createStringOfNumbers() {
       String result = numbers.stream().map(String::valueOf).reduce((String res, String i) -> res + "," + i).get();
       assertEquals(result, (String)"3,5,10,11,15,16,20,21,25,26");
       //assertEquals((Integer)result.length(), (Integer)27);
    }

   @Test 
    public void wordCounterTest() {
        String[] terms = {"test", "java", "test", "java", "lambda", "if", "else"};
        Map<String, Long> counter = Stream.of(terms).collect(Collectors.groupingBy(String::toString, Collectors.counting()));
        assertEquals(counter.get("java"), (Long)2L);
        assertEquals(counter.get("else"), (Long)1L);

    }

   @Test 
    public void selectByStartLetter() {
        List<String> ids = Stream.of("a1", "b2", "c3", "a4", "d5", "c6").map(s -> s.toUpperCase()).filter(s -> s.startsWith("A")).collect(Collectors.toList());
        assertEquals(ids.get(0), (String)"A1");
        assertEquals(ids.get(1), (String)"A4");
    }

@Test 
    public void joinByStartLetter() {
       String ceeString = Stream.of("a1", "b2", "c3", "a4", "d5", "c6").map(s -> s.toUpperCase()).filter(s -> s.startsWith("C")).collect(Collectors.joining(" and "));
       assertEquals((String)ceeString, (String)"C3 and C6");       
    }

    @Test 
    public void joinByStartLetter2() {
       String aaString = Stream.of("a1", "b2", "c3", "a4", "a5", "c6").map(s -> s.toUpperCase()).filter(s -> s.startsWith("A")).collect(Collectors.joining("+"));
       assertEquals(aaString, (String)"A1+A4+A5");  
    }

    @Test 
    public void countProcessors() {
        java.util.concurrent.ForkJoinPool commonPool = java.util.concurrent.ForkJoinPool.commonPool();
        System.out.println("** Processors in this computer: " + (Integer)commonPool.getParallelism());
        //assertEquals((Integer)commonPool.getParallelism(), (Integer)3);
    }


    @Test 
    public void ifExist() {
        Arrays.asList("aa", "bee", "cee").stream().findFirst().ifPresent(System.out::println);
        Stream<String> idStream = IntStream.range(1, 6).mapToObj(i -> "a".toUpperCase() + i);
        List<String> identifiers = idStream.collect(Collectors.toList());
         assertEquals(identifiers.get(0), "A1");
         assertEquals(identifiers.get(4), "A5");
    }


    
}
