package JoiningThreads;

import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        var startTime = System.currentTimeMillis();

        List<Long> inputNumbers = Arrays.asList(0L, 9223L, 9233L, 8339L, 5860L, 2L, 2203L);

        List<FactorialThread> threads = new ArrayList<>();

        for(long inputNumber : inputNumbers){
            threads.add(new FactorialThread(inputNumber));
        }

        for(Thread thread: threads){
            thread.setDaemon(true); // a very large thread over 2s will mean the main thread will finish DOES NOT BLOCK MAIN APP
            thread.start();
        }
        //makes main thread wait until all other factorials are finished and everything is printed together
        for(Thread thread: threads){
            thread.join(2000); // if thread hasnt finished in 2 seconds join method returns
        }
        // we dont know what threads are at what stages when main thread is checking through them
        // so it misses some of the answers
        for( int i = 0; i < inputNumbers.size(); i++){
            FactorialThread factorialThread = threads.get(i);
            if(factorialThread.isFinished()){
                System.out.println("Factorial of " + inputNumbers.get(i) + " is finished result:" + factorialThread.getResult());
            } else {
                System.out.println("Still working on " + inputNumbers.get(i));
            }
        }

        System.out.println(System.currentTimeMillis() - startTime);
    }
}
