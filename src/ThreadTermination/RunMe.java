package ThreadTermination;

public class RunMe implements Runnable{
    @Override
    public  void run(){
        try{
            Thread.sleep(50000);
        } catch (InterruptedException e){
            System.out.println("Exiting blocking thread");
        }
    }
}
