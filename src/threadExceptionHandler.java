public class threadExceptionHandler implements Thread.UncaughtExceptionHandler {

    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("using class thread: " + t.getName() + " error: " +e);
    }
}
