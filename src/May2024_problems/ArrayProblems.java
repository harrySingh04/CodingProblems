package May2024_problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArrayProblems {

    /*
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

}
