package May2024_problems;

import java.util.LinkedList;
import java.util.*;

public class ArrayProblems {

    /**
    * Problem 1: You are given a list of numbers, and a target number k.
    * Return whether or not there are two numbers in the list that add up to k.

        Example: Given [4, 7, 1 , -3, 2] and k = 5, return true since 4 + 1 = 5.
    * */

    public boolean two_sum_approach_one(int[] nums, int target){

        int j;
        for(int i=0;i<nums.length;i++){
            j = 0;

            while (j < nums.length){

                if (i == j){
                    j++;
                    continue;
                }
                if (nums[i] + nums[j] == target)
                    return true;
                j++;
            }

        }

        return false;
    }

    public boolean two_sum_approach_two(int[] nums, int target){

        Map<Integer,Integer> map  = new HashMap<>();
        int value;

        for(int i=0;i<nums.length;i++){

            map.put(nums[i],i);
        }

        for(int i=0;i<nums.length;i++){
            value = target - nums[i];
            if (map.containsKey(value) && map.get(value) != i){
                return true;
            }
        }

        return false;
    }

    public boolean two_sum_approach_three(int[] nums, int target){
        Arrays.sort(nums);

        int firstPointer = 0, secondPointer = nums.length-1;

        while( firstPointer < secondPointer ){

            if(nums[firstPointer] + nums[secondPointer] == target)
                return true;
            else if(nums[firstPointer] + nums[secondPointer] > target)
                secondPointer--;
            else
                firstPointer++;
        }

        return false;

    }

    /**

     Problem 2: Given a 32 bit integer, reverse the bits and return that number.

     Example1 : Input: 1234
     # In bits this would be 0000 0000 0000 0000 0000 0100 1101 0010
     Output: 1260388352
     # Reversed bits is 0100 1011 0010 0000 0000 0000 0000 0000

     Example 2: Input : 16 (00000000 00000000 00000000 00010000)
     Output: 134217728 (00001000 00000000 00000000 00000000)

     */

    public long reverseBits(long num){

        long result = 0;

       for(int i=0;i<32;i++){

            result = result << 1;

            if((num & 1) == 1)
                result = result ^ 1;

            num = num >> 1;
        }

        return result;
    }

    /**
     *
     *
     * Problem 3: Circular Array Loop
     *
     */

    public boolean circularArrayLoop(int[] nums) {

        // Replace this placeholder return statement with your code
        int size, fast, slow;
        boolean forward;

        for(int i=0;i<nums.length;i++){

            fast = i;
            slow = i;
            forward = nums[i] > 0;

            while(true){

                slow = getNextStep(nums, slow, forward);

                if(slow == -1)
                    break;

                fast = getNextStep(nums,fast,forward);

                if(fast == -1)
                    break;

                fast = getNextStep(nums,fast,forward);

                if(fast == -1)
                    break;

                if(slow == fast)
                    return true;

            }


        }
        return false;
    }

    public int getNextStep(int[] nums, int pointer, boolean prevDirection){
        boolean currDirection = nums[pointer] > 0;
        if(prevDirection != currDirection)
            return -1;
        int nextStep = (pointer + nums[pointer]) % nums.length;

        if(nextStep < 0){
            nextStep = nextStep + nums.length;
        }

        if(nextStep == pointer)
            return -1;
        return nextStep;
    }

    /**
     *
     * Problem: Find maximum of sub-array of size K
     */

    public int getMaxSum(int[] nums, int k){

        int i , j, maxSum, sum;

        maxSum = Integer.MIN_VALUE;
        sum = 0;

        i = 0;
        j = 0;

        while(j < nums.length){

            sum = sum + nums[j];

            if(j-i+1 < k)
                j++;
            else if(j-i+1 == k){

                maxSum = Math.max(maxSum, sum);
                sum = sum - nums[i];
                i++;
                j++;
            }

        }

        return maxSum;
    }

    public int[] firstNegativeNumberSubArray(int[] nums, int k){

        int i,j,a;

        Queue<Integer> queue = new LinkedList<>();



        int[] result = new int[nums.length];

        i = 0;j = 0;a = 0;

        while(j < nums.length){
            if(nums[j] < 0)
                queue.add(nums[j]);

            if(j - i + 1 < k)
                j++;
            else if(j - i + 1 == k){
                if(queue.size() > 0) {
                    if(nums[i] != queue.peek()) {
                        result[a] = queue.peek();
                        a++;
                    }
                    else{
                        result[a] = queue.remove();
                        a++;
                    }
                }
                i ++;
                j++;
            }
        }

        return result;
    }


    /**
     *
     * Max sub array of size K , leet code hard problem # 239 (Sliding Window Maximum)
     */
    public int[] maxSlidingWindow(int[] nums, int k) {

        int i,j, start;

        Deque<Integer> deque = new ArrayDeque<>();
        int[] maxSums = new int[nums.length-k+1];

        i = 0;
        j = 0;
        start = 0;


        while (j < nums.length){

            while(deque.size() != 0 && deque.peekFirst() < nums[j] ){
                deque.pollFirst();
            }

            while(deque.size() != 0 && deque.peekLast() < nums[j]){
                deque.pollLast();
            }

            deque.addLast(nums[j]);

            if(j- i + 1 < k)
                j++;
            else if(j - i + 1 == k){
                maxSums[start++] = deque.peekFirst();
                if(deque.peekFirst() == nums[i]){
                    deque.pollFirst();
                }
                i++;
                j++;
            }

        }

        return maxSums;

    }

}
