package HighPerformanceIO;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//thread per-request model
// in a system like this if there are too many blocking calls per thread, the CPU can spend more time managing threads
// than actually doing work - this is called threshing
public class IoBoundApp {

    private static final int NUMBER_OF_TASKS = 10000;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("enter to start");
        s.nextLine();

        System.out.printf("Running %d tasks\n", NUMBER_OF_TASKS);
        long start = System.currentTimeMillis();
        performTasks();
        System.out.printf("Tasks took %dms to complete \n", System.currentTimeMillis() - start);
    }

    private static void performTasks() {
        ExecutorService executorService = Executors.newFixedThreadPool(1000);  // newCachedThreadPool(); // dynamic thread pool to create as many
                                                                            // threads as it needs to perform its tasks
        for (int i = 0; i < NUMBER_OF_TASKS; i++) { //but our JVM can run out of memory if there are too many tasks
            executorService.submit(() -> blockingIoOperation());
        }
    }

    public static void blockingIoOperation(){
        System.out.println("executing blocking task from thread " + Thread.currentThread());
        try {
            Thread.sleep(1000);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
