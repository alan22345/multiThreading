package ThreadTermination;


import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        // point is to show how a thread can be interrupted if it is blocking an app

        Thread thread = new Thread(new RunMe());

        thread.start();
        thread.interrupt();

        Thread longComp = new Thread(new LongComputation(new BigInteger("2"), new BigInteger("10")));
        longComp.start();
        // here interrupt is not enough because it's in the for loop so adding line 23 - 26 in long computation for loop
        // to prevent a thread from blocking the app, need to set the thread to be a daemon thread.
    }
}
