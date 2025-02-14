package main.java.May2024_problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



public class InterviewPractice {

    public static void main(String[] args) {

        /*String[] activity = new String[]{"8:30am | pickup", "9:10am | dropoff", "10:20am | pickup",
                "12:15pm | pickup",
                "12:45pm | dropoff",
                "2:25pm | dropoff"};

        InterviewPractice i1 = new InterviewPractice();

        System.out.println(i1.getActivity(activity));*/

       /* List<String> paths = generatePaths(2);
        for (String path : paths) {
            System.out.println(path);
        }*/

        String[] orders1 = {"P1", "P1", "D1"};
        String[] orders2 = {"P1", "P2", "D1", "D2"};
        String[] orders3 = {"P1", "D1", "P2", "D2", "P3"};
        String[] orders4 = {"P1", "P2", "D1", "P3"};
        String[] orders5 = {"P1", "P2", "D1", "P3", "D3", "D2"};

        System.out.println(longestValidSubarray(orders1)); // Output: [P1, D1]
        System.out.println(longestValidSubarray(orders2)); // Output: [P1, P2, D1, D2]
        System.out.println(longestValidSubarray(orders3)); // Output: [P1, D1, P2, D2]
        System.out.println(longestValidSubarray(orders4)); // Output: [P1, D1]
        System.out.println(longestValidSubarray(orders5)); // Output: [P1, P2, D1, P3, D3]
    }

    public int getActivity(String[] activity){
        int smallestPickup = Integer.MAX_VALUE;
        int highDrop = Integer.MIN_VALUE;
        List<Integer> pickups = new ArrayList<>();
        List<Integer> dropoffs = new ArrayList<>();

        for(String s:activity ){
            String[] act = s.split(" \\| ");
            String type = act[1];
            String time =act[0];

            int mins  = getMins(time);

            if(type.equals("pickup"))
            {
                pickups.add(mins);
                smallestPickup = Math.min(smallestPickup,mins);
            }
            else
            {
                dropoffs.add(mins);
                highDrop = Math.max(highDrop,mins);
            }

        }

        List<int[]> intervals = new ArrayList<>();

        for(int i = 0;i<pickups.size();i++){
            intervals.add(new int[]{pickups.get(i),dropoffs.get(i)});
        }

        int total_time = highDrop - smallestPickup;

        int idleTime = 0;

       for(int i=0;i<intervals.size()-1;i++){
           int[] currInterval = intervals.get(i);
           int[] nextInterval = intervals.get(i+1);

           if(nextInterval[0] > currInterval[1])
               idleTime += nextInterval[0] - currInterval[1];
           else
               nextInterval[1] = Math.max(currInterval[1], nextInterval[1]);

       }

       return total_time - idleTime;


    }

    public int getMins(String time){

        String[] t = time.split(":");
        int hours = Integer.parseInt(t[0]);
        int mins = Integer.parseInt(t[1].substring(0,2));
        boolean isPM = t[1].startsWith("pm", 2);

        if(isPM){
            if(hours < 12)
                hours = hours + 12;
        }
        else {
            if(hours == 12)
                hours = 0;
        }

        return 60 * hours + mins;
    }

    public static List<String> generatePaths(int n) {
        List<String> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        backtrack(result, path, 0, 0, n);
        return result;
    }

    private static void backtrack(List<String> result, List<String> path, int pickups, int deliveries, int n) {
        if (pickups == n && deliveries == n) {
            result.add(String.join("", path));
            return;
        }

        if (pickups < n) {
            path.add("P" + (pickups + 1));
            backtrack(result, path, pickups + 1, deliveries, n);
            path.remove(path.size() - 1);  // Backtrack
        }

        if (deliveries < pickups) {
            path.add("D" + (deliveries + 1));
            backtrack(result, path, pickups, deliveries + 1, n);
            path.remove(path.size() - 1);  // Backtrack
        }
    }

        public static List<String> longestValidSubarray(String[] orders) {
            int maxLen = 0;
            List<String> maxSubarray = new ArrayList<>();
            List<String> currentSubarray = new LinkedList<>();
            Map<String, Integer> pickupCount = new HashMap<>();
            Map<String, Integer> deliveryCount = new HashMap<>();

            for (String order : orders) {
                currentSubarray.add(order);
                if (order.startsWith("P")) {
                    pickupCount.put(order, pickupCount.getOrDefault(order, 0) + 1);
                } else {
                    String correspondingPickup = "P" + order.substring(1);
                    if (pickupCount.getOrDefault(correspondingPickup, 0) > deliveryCount.getOrDefault(order, 0)) {
                        deliveryCount.put(order, deliveryCount.getOrDefault(order, 0) + 1);
                    } else {
                        while (!currentSubarray.isEmpty() &&
                                pickupCount.getOrDefault(correspondingPickup, 0) <= deliveryCount.getOrDefault(order, 0)) {
                            String removedOrder = currentSubarray.remove(0);
                            if (removedOrder.startsWith("P")) {
                                pickupCount.put(removedOrder, pickupCount.get(removedOrder) - 1);
                            } else {
                                deliveryCount.put(removedOrder, deliveryCount.get(removedOrder) - 1);
                            }
                        }
                    }
                }

                if (currentSubarray.size() > maxLen) {
                    maxLen = currentSubarray.size();
                    maxSubarray = new ArrayList<>(currentSubarray);
                }
            }
            return maxSubarray;
        }

}
