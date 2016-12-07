package concurrent;

import java.util.concurrent.TimeUnit;


public class ThreadTest1 {

    public static void main(String[] args) {
        run1();
        run2();
    }


    public static void run1() {
        Runnable runnable = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Starting the Thread " + threadName);
        };

        runnable.run();

        Thread thread = new Thread(runnable);
        thread.start();

        System.out.println("Thread is finished!");
    }

    public static void run2() {
        Runnable runnable = () -> {
            try {
                System.out.println("First output " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(2); 
                System.out.println("Second Output " + Thread.currentThread().getName());
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

}
