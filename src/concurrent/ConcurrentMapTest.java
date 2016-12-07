package concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;

/* 
What is concurrentHashMap:

ConcurrentHashMap

    You should use ConcurrentHashMap when you need very high concurrency in your project.
    It is thread safe without synchronizing the whole map.
    Reads can happen very fast while write is done with a lock.
    There is no locking at the object level.
    The locking is at a much finer granularity at a hashmap bucket level.
    ConcurrentHashMap doesn’t throw a ConcurrentModificationException if one thread tries to modify it while another is iterating over it.
    ConcurrentHashMap uses multitude of locks.
    
@author   Juha Peltomäki
*/


public class ConcurrentMapTest {

    static ConcurrentHashMap<String, String> map;

    public static void main(String[] args) {
        map = new ConcurrentHashMap<>();
        map.putIfAbsent("Java", "8");
        map.putIfAbsent("Java", "9");
        map.putIfAbsent("C#", "5");
        map.putIfAbsent("EcmaScript", "5");
        map.putIfAbsent("Perl", "6");
        map.putIfAbsent("Scala", "2.11");

        System.out.println("Parallelism: " + ForkJoinPool.getCommonPoolParallelism());

        // start the thread with java 8 syntax
        new Thread(() -> testSearch()).start();
        new Thread(() -> { testForEach(); ; addNew("Groovy", "5"); }).start();
        new Thread(() -> { testForEach(); addNew("Perl5", "5"); }).start();
        new Thread(() -> testSearch()).start();        
        new Thread(() -> testSearch()).start();
        new Thread(() -> testForEach()).start();


/*
  Using ForkJoinPool

ForkJoinPool is an ExecutorService for running ForkJoinTasks. A ForkJoinPool provides the entry point for submissions from non-ForkJoinTask clients, as well as management and monitoring operations.

A ForkJoinPool differs from other kinds of ExecutorService mainly by virtue of employing work-stealing: all threads in the pool attempt to find and execute tasks submitted to the pool and/or created by other active tasks 
(eventually blocking waiting for work if none exist). This enables efficient processing when most tasks spawn other subtasks (as do most ForkJoinTasks), as well as when many small tasks are submitted to the pool from external clients. 
Especially when setting asyncMode to true in constructors, ForkJoinPools may also be appropriate for use with event-style tasks that are never joined. 

    We can create own thread pools and can avoid the default shared thread pool 
    and we can even allocate more threads than processor cores if we so wish.
 */
        ForkJoinPool forkJoinPool = new ForkJoinPool(3); 
        ForkJoinPool forkJoinPool2 = new ForkJoinPool(3);
        forkJoinPool.submit(() -> testSearch());
        forkJoinPool2.submit(() -> { testForEach(); ; addNew("Python", "3"); });
        forkJoinPool.submit(() -> { testForEach(); addNew("PHP", "5"); });
        forkJoinPool2.submit(() -> testSearch());        
        forkJoinPool.submit(() -> testSearch());
        forkJoinPool2.submit(() -> testForEach());
    }

    private static void addNew(String lang, String ver) {
        map.putIfAbsent(lang, ver);
    }

    private static void testSearch() {

        String result1 = map.search(1, (key, value) -> {
            System.out.println("search():" + Thread.currentThread().getName());
            if (key.equals("Perl")) {
                return "Perl founded";
            }
            return null;
        });

        String result2 = map.searchValues(1, value -> {
            System.out.println("searchValues():" + Thread.currentThread().getName());
            if (value.equals("5")) {
                return "C# founded: " + value;
            }
            return null;
        });

        if (result1 != null) System.out.println(result1 + ": " + Thread.currentThread().getName());
        if (result2 != null) System.out.println(result2 + ": " + Thread.currentThread().getName());
    }

    private static void testForEach() {
        map.forEach(1, (key, value) -> System.out.printf("key: %s; value: %s; thread: %s\n", key, value, Thread.currentThread().getName()));

        System.out.println("mappingCount: " + map.mappingCount() + ": " + Thread.currentThread().getName());
    }

}

/* Output:


key: C#; value: 5; thread: Thread-3
key: Java; value: 8; thread: Thread-3
key: Perl; value: 6; thread: Thread-3
search():Thread-5
key: Perl; value: 6; thread: ForkJoinPool.commonPool-worker-1
search():Thread-1
search():Thread-1
search():Thread-1
search():Thread-4
search():Thread-4
search():Thread-4
searchValues():Thread-4
Perl founded: Thread-4
C# founded: 5: Thread-4
key: C#; value: 5; thread: Thread-2
key: Java; value: 8; thread: Thread-2
key: EcmaScript; value: 5; thread: Thread-2
key: Scala; value: 2.11; thread: Thread-2
searchValues():Thread-1
Perl founded: Thread-1
C# founded: 5: Thread-1
key: EcmaScript; value: 5; thread: ForkJoinPool.commonPool-worker-1
key: Scala; value: 2.11; thread: ForkJoinPool.commonPool-worker-1
search():ForkJoinPool.commonPool-worker-1
mappingCount: 5: Thread-2
mappingCount: 6: Thread-3
searchValues():Thread-5
Perl founded: Thread-5
C# founded: 5: Thread-5
key: Perl; value: 6; thread: ForkJoinPool.commonPool-worker-1
key: Groovy; value: 5; thread: ForkJoinPool.commonPool-worker-1
key: EcmaScript; value: 5; thread: ForkJoinPool.commonPool-worker-1
key: Scala; value: 2.11; thread: ForkJoinPool.commonPool-worker-1
key: C#; value: 5; thread: Thread-6
key: Perl5; value: 5; thread: Thread-6
key: Java; value: 8; thread: Thread-6
mappingCount: 7: Thread-6


*/

/*
Starting using own ForkJoinPool
search():ForkJoinPool-1-worker-1
search():ForkJoinPool-1-worker-1
search():ForkJoinPool-1-worker-1
search():ForkJoinPool-1-worker-1
search():ForkJoinPool-1-worker-1
search():ForkJoinPool-1-worker-1
searchValues():ForkJoinPool-1-worker-1
Perl founded: ForkJoinPool-1-worker-1
C# founded: 5: ForkJoinPool-1-worker-1
key: C#; value: 5; thread: ForkJoinPool-1-worker-1
key: Perl5; value: 5; thread: ForkJoinPool-1-worker-1
key: Java; value: 8; thread: ForkJoinPool-1-worker-1
key: EcmaScript; value: 5; thread: ForkJoinPool-1-worker-1
key: Scala; value: 2.11; thread: ForkJoinPool-1-worker-1
key: Perl; value: 6; thread: ForkJoinPool-1-worker-1
key: Groovy; value: 5; thread: ForkJoinPool-1-worker-1
mappingCount: 7: ForkJoinPool-1-worker-1
key: C#; value: 5; thread: ForkJoinPool-2-worker-1
key: Perl5; value: 5; thread: ForkJoinPool-2-worker-1
key: Java; value: 8; thread: ForkJoinPool-2-worker-1
key: EcmaScript; value: 5; thread: ForkJoinPool-2-worker-1
key: Scala; value: 2.11; thread: ForkJoinPool-2-worker-1
key: PHP; value: 5; thread: ForkJoinPool-2-worker-1
key: Perl; value: 6; thread: ForkJoinPool-2-worker-1
key: Groovy; value: 5; thread: ForkJoinPool-2-worker-1
mappingCount: 8: ForkJoinPool-2-worker-1
search():ForkJoinPool-2-worker-1
search():ForkJoinPool-2-worker-1
search():ForkJoinPool-2-worker-1
search():ForkJoinPool-2-worker-1
search():ForkJoinPool-2-worker-1
search():ForkJoinPool-2-worker-1
search():ForkJoinPool-2-worker-1
searchValues():ForkJoinPool-2-worker-1
Perl founded: ForkJoinPool-2-worker-1
C# founded: 5: ForkJoinPool-2-worker-1
search():ForkJoinPool-1-worker-3
search():ForkJoinPool-1-worker-3
search():ForkJoinPool-1-worker-1
searchValues():ForkJoinPool-1-worker-1
key: Groovy; value: 5; thread: ForkJoinPool-2-worker-1
key: Python; value: 3; thread: ForkJoinPool-2-worker-1
Perl founded: ForkJoinPool-1-worker-2
C# founded: 5: ForkJoinPool-1-worker-2
key: PHP; value: 5; thread: ForkJoinPool-2-worker-2
key: Perl; value: 6; thread: ForkJoinPool-2-worker-1
key: EcmaScript; value: 5; thread: ForkJoinPool-2-worker-1
key: Scala; value: 2.11; thread: ForkJoinPool-2-worker-1
key: C#; value: 5; thread: ForkJoinPool-2-worker-3
key: Perl5; value: 5; thread: ForkJoinPool-2-worker-3
key: Java; value: 8; thread: ForkJoinPool-2-worker-3
mappingCount: 9: ForkJoinPool-2-worker-3
*/