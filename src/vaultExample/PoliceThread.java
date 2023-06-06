package vaultExample;

public  class PoliceThread extends Thread{
    @Override
    public void run(){
        for(int i = 10; i>0; i--){
            try {
                Thread.sleep(1000);
            } catch (Exception e){
            }
            System.out.println(i);
        }
        System.out.println("Caught you!");
        System.exit(0);
    }
}
