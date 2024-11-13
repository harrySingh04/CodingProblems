package November2024.RetainCache;

import java.util.ArrayList;
import java.util.List;

public class Driver {

    public static void main(String[] args) {
        // Simple data source implementation
        DataSource<String, String> dataSource = key -> "Value for " + key;

        // Create a cache with a capacity of 3
        StandardCache<String, String> cache = new StandardCache<>(dataSource, 3);

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            int threadNum = i;

            // Create a Runnable task
            Runnable task = () -> {
                String key = "Key-" + threadNum;
                cache.getKey(key);  // Fetch data from the cache (or data source)

            };

            // Create a thread with the Runnable and start it
            Thread thread = new Thread(task);
            thread.start();

            // Add thread to the list for joining later
            threads.add(thread);
        }

        for (Thread thread : threads) {
            try {
                thread.join();  // Wait for the thread to complete

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Cache size = " + cache.getSize());

        List<String> values=  cache.getValues();

        for (String v: values){

            System.out.println(v);
        }








        // List to hold references to threads


    }
}
