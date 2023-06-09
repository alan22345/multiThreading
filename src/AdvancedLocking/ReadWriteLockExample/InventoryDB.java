package AdvancedLocking.ReadWriteLockExample;

import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantLock;

public class InventoryDB {
    private TreeMap<Integer,Integer> priceToCountMap = new TreeMap<>();
    private ReentrantLock lock = new ReentrantLock();

    public int getNumberOfItemsInPriceRange(int lowerBound, int upperBound) {
        lock.lock();
        try {
            Integer fromKey = priceToCountMap.ceilingKey(lowerBound);
            Integer toKey = priceToCountMap.floorKey(upperBound);

            if (fromKey == null || toKey == null) {
                return 0;
            }
            NavigableMap<Integer, Integer> rangeOfPrices = priceToCountMap.subMap(fromKey,
                    true, toKey, true); // take snapshot of tree within the price range
            int sum = 0;
            for (int numberOfItemsForPrice : rangeOfPrices.values()) {
                sum += numberOfItemsForPrice;
            }
            return sum;
        } finally {
            lock.unlock();
        }
    }

    public void addItem(int price) {
        lock.lock();
        try {
            Integer numberOfItemsForPrice = priceToCountMap.get(price);
            if (numberOfItemsForPrice == null) {
                priceToCountMap.put(price, 1);
            } else {
                priceToCountMap.put(price, numberOfItemsForPrice + 1);
            }
        } finally {
            lock.unlock();
        }
    }

    public void removeItem(int price) {
        lock.lock();
        try {
            Integer numberOfItemsForPrice = priceToCountMap.get(price);
            if (numberOfItemsForPrice == null || numberOfItemsForPrice == 1) {
                priceToCountMap.remove(price);
            } else {
                priceToCountMap.put(price, numberOfItemsForPrice - 1);
            }
        } finally {
            lock.unlock();
        }
    }
}
