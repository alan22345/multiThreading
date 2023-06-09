package Deadlocking;

import java.util.Random;

public class Intersection {
    public static void main(String[] args) {
        Intersection intersection = new Intersection();
        Thread trainAThread = new Thread(new TrainA(intersection));
        Thread trainBThread = new Thread(new TrainB(intersection));

        trainAThread.start();
        trainBThread.start();
    }

    public static class TrainA implements Runnable {
        private Intersection intersection;
        private Random random = new Random();

        public TrainA(Intersection intersection){
            this.intersection = intersection;
        }

        @Override
        public void run() {
            while(true){
                long sleepingTime = random.nextInt(5);
                try {
                    Thread.sleep(sleepingTime);
                }catch (Exception e){}
                intersection.takeRoadA();
            }
        }
    }

    public static class TrainB implements Runnable {
        private Intersection intersection;
        private Random random = new Random();

        public TrainB(Intersection intersection){
            this.intersection = intersection;
        }

        @Override
        public void run() {
            while(true){
                long sleepingTime = random.nextInt(5);
                try {
                    Thread.sleep(sleepingTime);
                }catch (Exception e){}
                intersection.takeRoadB();
            }
        }
    }

    private Object roadA = new Object();
    private Object roadB = new Object();

    public void takeRoadA(){
        synchronized (roadA){
            System.out.println("Road A is locked by thread " + Thread.currentThread().getName());
            synchronized (roadB){
                System.out.println("Train is passing through road A");
                try {
                    Thread.sleep(1);
                }catch (Exception e){}
            }
        }
    }

    public void takeRoadB(){
        synchronized (roadA){ // RoadB here deadlocks the threads using the same locking as A removes the chance of deadlock
            System.out.println("Road A is locked by thread " + Thread.currentThread().getName());
            synchronized (roadB){
                System.out.println("Train is passing through road B");
                try {
                    Thread.sleep(1);
                }catch (Exception e){}
            }
        }
    }
}
