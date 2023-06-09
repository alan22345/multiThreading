package AdvancedLocking.ReadWriteLockExample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static final int HIGHEST_PRICE = 1000;

    public static void main(String[] args) throws InterruptedException {
        InventoryDB inventoryDB = new InventoryDB();

        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            inventoryDB.addItem(random.nextInt(HIGHEST_PRICE));
        }
        Thread writer = new Thread(() -> {
            while (true){
                inventoryDB.addItem(random.nextInt(HIGHEST_PRICE));
                inventoryDB.removeItem(random.nextInt(HIGHEST_PRICE));

                try{
                    Thread.sleep(10);
                }catch (Exception e){}
            }
        });
        writer.setDaemon(true);
        writer.start();

        int numberOfReaderThreads = 7;
        List<Thread> readers = new ArrayList<>();

        for (int readerIndex = 0; readerIndex < numberOfReaderThreads ; readerIndex++) {
            Thread reader = new Thread(() -> {
                for (int i = 0; i < 100000 ; i++) {
                    int upperBoundPrice = random.nextInt(HIGHEST_PRICE);
                    int lowerBoundPrice = upperBoundPrice > 0 ? random.nextInt(upperBoundPrice) : 0;
                    inventoryDB.getNumberOfItemsInPriceRange(lowerBoundPrice,upperBoundPrice);

                }
            });
            reader.setDaemon(true);
            readers.add(reader);
        }
        long startReadingTime = System.currentTimeMillis();
        for (Thread reader: readers) {
            reader.start();
        }
        for (Thread reader: readers){
            reader.join();
        }
        long endReadingTime = System.currentTimeMillis();
        System.out.println(String.format("Reading took %d ms", endReadingTime - startReadingTime));
    }
}
