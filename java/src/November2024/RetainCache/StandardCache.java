package November2024.RetainCache;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StandardCache<K,V>{

    private final Map<K,V> cache;
    private final TreeMap<Integer, Set<K>> rankKeyMap;
    private final DataSource<K,V> dataSource;
    private final int capacity;
    private final Lock lock;

    public StandardCache(DataSource<K,V> dataSource, int capacity){
        this.cache = new HashMap<>();
        this.rankKeyMap = new TreeMap<>();
        this.dataSource =  dataSource;
        this.capacity = capacity;
        this.lock = new ReentrantLock();

    }

    public V getKey(K key){
       // First, attempt to retrieve the value from the cache.

        lock.lock(); // Locking to ensure thread-safe access to the cache
        try{
            if(this.cache.containsKey(key))
                return this.cache.get(key);

        }
        finally {
            lock.unlock();
        }

        V value = dataSource.getData(key);

        Integer rank = computeRank(value);

        // Insert the value into the cache with thread safety and eviction handling.
        lock.lock();

        try {
            // Check if another thread already inserted the value during the fetch
            if (cache.containsKey(key)) {
                return cache.get(key);
            }

            // Add the new value to the cache
            cache.put(key, value);
            this.rankKeyMap.computeIfAbsent(rank, k -> new HashSet<>()).add(key);

            // If the cache size exceeds capacity, evict the lowest-ranked item
            if (cache.size() > capacity) {
                evictLowestRank();
            }
        } finally {
            lock.unlock();
        }

        return value;
    }

    /**
     * Computes a rank for a given value. This is a placeholder for any custom logic
     * used to determine the rank of a value before it is added to the cache.
     *
     * @param value the value to compute the rank for
     * @return the rank of the value
     */
    private int computeRank(V value) {
        // Custom rank computation logic goes here. This is just a placeholder.
        // For example, you could base it on object properties or any other criteria.
        return value.hashCode(); // Example rank based on hashCode (can be customized)
    }

    /**
     * Evicts the item with the lowest rank from the cache.
     */

    private void evictLowestRank(){

        if(this.rankKeyMap.isEmpty())
            return;

        Map.Entry<Integer, Set<K>> lowestEntry = this.rankKeyMap.firstEntry();

        if(lowestEntry != null && !lowestEntry.getValue().isEmpty()){

            Iterator<K> iterator = lowestEntry.getValue().iterator();

            if(iterator.hasNext()){

                K keyToEvict = iterator.next();

                iterator.remove();

                if(lowestEntry.getValue().isEmpty())
                    this.rankKeyMap.remove(lowestEntry.getKey());

                this.cache.remove(keyToEvict);
            }

        }



    }


    public int getSize(){
        return this.cache.size();
    }

    public List<String> getValues(){

        List<String> values = new ArrayList<>();

        this.cache.forEach((k, v) ->
                values.add((String) v));

        return values;


    }




}
