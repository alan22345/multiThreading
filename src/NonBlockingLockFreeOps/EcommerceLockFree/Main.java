package NonBlockingLockFreeOps.EcommerceLockFree;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        InventoryCounter inventoryCounter = new InventoryCounter();
        IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);
        DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);

        incrementingThread.start();
        decrementingThread.start();
        incrementingThread.join();
        decrementingThread.join(); // not atomic operations

        System.out.println("We currently have " + inventoryCounter.getItems());
    }

}
