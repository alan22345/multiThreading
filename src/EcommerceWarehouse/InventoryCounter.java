package EcommerceWarehouse;

public class InventoryCounter {
    private int items = 0;
    Object lock = new Object();

    public void increment(){                    // adding synchronized synchronises the threads so that only one
        synchronized (this.lock) {
            items++;                            // thread can access the synced methods at a time
        }
    }
    public void decrement(){                    // would need to add synchronized here too
        synchronized (this.lock){
            items--;
        }
    }

    public int getItems(){
        synchronized (this.lock){
            return items;
        }
    }

}
