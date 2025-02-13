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

    public static int maxProduct(int[] nums) {

        int n = nums.length;
        long leftProduct = 1;
        long rightProduct = 1;
        long ans = nums[0];

        for (int i = 0; i < n; i++) {

            //if any of leftProduct or rightProduct become 0 then update it to 1
            leftProduct = leftProduct == 0 ? 1 : leftProduct;
            rightProduct = rightProduct == 0 ? 1 : rightProduct;

            //prefix product
            leftProduct *= nums[i];

            //suffix product
            rightProduct *= nums[n - 1 - i];

            ans = Math.max(ans, Math.max(leftProduct, rightProduct));
        }

        return (int)ans;
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

    public static int minSubArrayLen(int target, int[] nums) {

        // Replace this placeholder return statement with your code

        int i = 0 ,j = 0, minLength = Integer.MAX_VALUE, currentSum = 0;

        while( j < nums.length){

            currentSum += nums[j];

            if(currentSum < target)
                j++;
            else{
                while(currentSum >= target){
                    minLength = Math.min(minLength, j - i + 1);
                    currentSum -= nums[i];
                    i++;
                }
                j++;
            }

        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static int maxProfit(int[] prices) {

        // Replace this placeholder return statement with your code


        int buyPrice, currentProfit, maxProfit = 0;

        buyPrice = prices[0];

        for(int i = 1;i <prices.length;i++){

            if(prices[i] <  buyPrice)
                buyPrice = prices[i];
            else{
                currentProfit = prices[i] - buyPrice;
                maxProfit = Math.max(currentProfit, maxProfit);
            }
        }



        return maxProfit;
    }

    /**
     * You are visiting a farm that has a single row of fruit trees arranged from left to right.
     * The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.
     *
     * You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:
     *
     * You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on
     * the amount of fruit each basket can hold.Starting from any tree of your choice, you must pick exactly
     * one fruit from every tree (including the start tree) while moving to the right.
     * The picked fruits must fit in one of your baskets.Once you reach a tree with fruit that cannot fit
     * in your baskets, you must stop.Given the integer array fruits, return the maximum number of fruits you can pick.
     *
     * Example 1:
     *
     * Input: fruits = [1,2,1]
     * Output: 3
     * Explanation: We can pick from all 3 trees.
     * Example 2:
     *
     * Input: fruits = [0,1,2,2]
     * Output: 3
     * Explanation: We can pick from trees [1,2,2].
     * If we had started at the first tree, we would only pick from trees [0,1].
     * Example 3:
     *
     * Input: fruits = [1,2,3,2,2]
     * Output: 4
     * Explanation: We can pick from trees [2,3,2,2].
     * If we had started at the first tree, we would only pick from trees [1,2].
     *
     *
     * Constraints:
     *
     * 1 <= fruits.length <= 105
     * 0 <= fruits[i] < fruits.length
     */

    public int totalFruit(int[] fruits) {

        int i = 0, j = 0, maxCount = 0, count;

        Map<Integer,Integer> fruitBasket = new HashMap<>();

        while ( j < fruits.length){

            fruitBasket.put(fruits[j], fruitBasket.getOrDefault(fruits[j], 0) + 1);

            if(fruitBasket.size() <= 2 ){
                maxCount = Math.max(maxCount, j - i + 1);
            }

            while(fruitBasket.size() > 2){
                fruitBasket.put(fruits[i], fruitBasket.get(fruits[i]) -  1);
                if(fruitBasket.get(fruits[i]) == 0)
                    fruitBasket.remove(fruits[i]);
                i ++;
            }
            j++;
        }

        return maxCount;

    }

    public static int[][] mergeIntervals(int[][] intervals) {
        // Replace this placeholder return statement with your code


        List<int[]> output = new LinkedList<>();

        if(intervals.length <=1)
            return intervals;

        output.add(intervals[0]);

        for(int i = 1; i < intervals.length;i++){

            int[] currentInterval = intervals[i];

            int[] lastAddedIntervals = output.get(output.size()-1);

            if(lastAddedIntervals[1] >= currentInterval[0]){
                lastAddedIntervals[1] = Math.max(lastAddedIntervals[1], currentInterval[1]);
            }
            else
                output.add(currentInterval);
        }

        return output.toArray(new int[][]{});
    }

    public static int[][] insertInterval(int[][] existingIntervals, int[] newInterval) {

        // Replace this placeholder return statement with your code

        int i  = 0;

        List<int[]> output = new LinkedList<>();

        while(i < existingIntervals.length && existingIntervals[i][0] < newInterval[0])
        {
            output.add(existingIntervals[i]);
            i++;
        }

        if(output.size() == 0 || output.get(output.size()-1)[1] < newInterval[0])
            output.add(newInterval);
        else
            output.get(output.size()-1)[1] = Math.max(output.get(output.size()-1)[1], newInterval[1]);


        while(i < existingIntervals.length){

            if(output.get(output.size()-1)[1] < existingIntervals[i][0])
                output.add(existingIntervals[i]);
            else
                output.get(output.size()-1)[1] = Math.max(output.get(output.size()-1)[1], existingIntervals[i][1]);
            i++;
        }

        return output.toArray(new int[][]{});

    }

    public static int[][] intervalsIntersection(int[][] intervalLista, int[][] intervalListb) {
        // Replace this placeholder return statement with your code

        List<int[]> output = new LinkedList<>();

        int i = 0 , j = 0;

        while( i < intervalLista.length && j < intervalListb.length){

            int start = Math.max(intervalLista[i][0], intervalListb[j][0]);
            int end = Math.min(intervalLista[i][1], intervalListb[j][1]);

            if(start <= end)
                output.add(new int[]{start,end});

            if(intervalLista[i][1] < intervalListb[j][1])
                i++;
            else
                j++;
        }

        return output.toArray(new int[][]{});
    }

    static class Interval{
        int start;
        int end;
        boolean closed;
        public Interval(int start, int end)
        {
            this.start = start;
            this.end = end;
            this.closed = true; // by default, the interval is closed
        }

        // set the flag for closed/open
        public void setClosed(boolean closed)
        {
            this.closed = closed;
        }

    }

    /**
     *
     * Time complexity
     * The time complexity of the solution is 𝑂(𝑛⋅log n), as there will be total n elements(intervals for all
     * employees) in the heap.
     *  Space complexity O(n), where n is the total number of elements
     */

    public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {

        // Replace this placeholder return statement with your code

        List<Interval> ans = new ArrayList<Interval>();

        PriorityQueue<Interval> queue = new PriorityQueue<>((a, b)-> a.start- b.start);

        for(List<Interval> intervals: schedule){
            queue.addAll(intervals);
        }

        Interval prev = queue.poll();

        while(!queue.isEmpty()){

            Interval curr = queue.poll();
            if(prev.end < curr.start){
                ans.add(new Interval(prev.end, curr.start));
                prev = curr;

            }
            else{
                prev.end = Math.max(prev.end, curr. end);
            }
        }
        return ans;
    }

    public static int getHammingWeight(int n){
        int count = 0;
        while(n != 0){
            if((n & 1) == 1)
                count++;
            n = n >> 1;
        }
        return count;
    }

    public static int addTwoNumbers(int  a, int b){
        // a = 1110 , b = 11010 ( a & b =  01010
        while( b != 0){
            int temp = (a & b) << 1;
            a = a ^ b;
            b = temp;
        }

        return a;
    }

    static int N;
    static int M;
    static int K;
    static int MOD = (int) 1e9 + 7;

    public static int solve(int idx,int searchCost, int maxSoFar){
        if(idx == N){
            if(searchCost == K)
                return 1;
            else
                return 0;
        }

        int result = 0;

        for(int i =1 ;i<=M;i++){
            if(i > maxSoFar)
                result = (result + solve(idx+1, searchCost+1,i)) % MOD;
            else
                result += (result + solve(idx+1, searchCost,maxSoFar)) % MOD;
        }

        return result % MOD;
    }

    public static int numOfArrays(int n, int m, int k) {
        N = n;
        M = m;
        K = k;
        return solve(0,0,-1);

    }




    public static void main(String[] args) {

        //System.out.println(numOfArrays(2,3,1));

       // System.out.println(coinChange(new int[]{1,2,5}, 11));

        Thread thread = new Thread(()-> System.out.println("run"),"thread new ");

        thread.start();

        //System.out.println(TotalWays(22));

        //int[] nums = new int[]{0,10,10,10,10,10,10,10,10,10,-10,10,10,10,10,10,10,10,10,10,0};

        //System.out.println(maxProduct(nums));

        //System.out.println(getHammingWeight(10));

       /* int[][][] allIntervals = {
                {{1, 5}, {3, 7}, {4, 6}},
                {{1, 5}, {4, 6}, {6, 8}, {11, 15}},
                {{3, 7}, {6, 8}, {10, 12}, {11, 15}},
                {{1, 5}},
                {{1, 9}, {3, 8}, {4, 4}},
                {{1, 2}, {3, 4}, {8, 8}},
                {{1, 5}, {1, 3}},
                {{1, 5}, {6, 9}},
                {{0, 0}, {1, 18}, {1, 3}}
        };

        int index = 1;
        for (int[][] intervals : allIntervals) {
            System.out.println(index + ".\tIntervals to merge: " + Arrays.deepToString(intervals));
            int[][] result = mergeIntervals(intervals);
            System.out.println("\tMerged intervals: " + Arrays.deepToString(result));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }

        int[][] newIntervals = {
                {5, 7},
                {8, 9},
                {10, 12},
                {1, 3},
                {1, 10}
        };

        int[][][] existingIntervals = {
                {{1, 2}, {3, 5}, {6, 8}},
                {{1, 3}, {5, 7}, {10, 12}},
                {{8, 10}, {12, 15}},
                {{5, 7}, {8, 9}},
                {{3, 5}}
        };

        for (int i = 0; i < newIntervals.length; i++) {
            System.out.print((i + 1) + ".\tExisting intervals: ");
            System.out.println(Arrays.deepToString(existingIntervals[i]));
            System.out.println("\tNew interval: [" + newIntervals[i][0] + ", " + newIntervals[i][1] + "]");
            int[][] output = insertInterval(existingIntervals[i], newIntervals[i]);
            System.out.println("\tUpdated intervals: " + Arrays.deepToString(output));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }

        int[][][] inputIntervalLista = {
                {{1, 2}},
                {{1, 4}, {5, 6}, {9, 15}},
                {{3, 6}, {8, 16}, {17, 25}},
                {{4, 7}, {9, 16}, {17, 28}, {39, 50}, {55, 66}, {70, 89}},
                {{1, 3}, {5, 6}, {7, 8}, {12, 15}}
        };

        int[][][] inputIntervalListb = {
                {{1, 2}},
                {{2, 4}, {5, 7}, {9, 15}},
                {{2, 3}, {10, 15}, {18, 23}},
                {{3, 6}, {7, 8}, {9, 10}, {14, 19}, {23, 33}, {35, 40}, {45, 59}, {60, 64}, {68, 76}},
                {{2, 4}, {7, 10}}
        };

        for (int i = 0; i < inputIntervalLista.length; i++) {
            System.out.println(i + 1 + ".\t Interval List A: " + Arrays.deepToString(inputIntervalLista[i]));
            System.out.println("\t Interval List B: " + Arrays.deepToString(inputIntervalListb[i]));
            System.out.println("\t Intersecting intervals in 'A' and 'B' are: " +
                    Arrays.deepToString(intervalsIntersection(inputIntervalLista[i], inputIntervalListb[i])));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }

        List<List<List<Interval>>> inputs1 = Arrays.asList(
                Arrays.asList(
                        Arrays.asList(new Interval(1, 2), new Interval(5, 6)),
                        Arrays.asList(new Interval(1, 3)),
                        Arrays.asList(new Interval(4, 10))
                ),
                Arrays.asList(
                        Arrays.asList(new Interval(1, 3), new Interval(6, 7)),
                        Arrays.asList(new Interval(2, 4)),
                        Arrays.asList(new Interval(2, 5), new Interval(9, 12))
                ),
                Arrays.asList(
                        Arrays.asList(new Interval(2, 3), new Interval(7, 9)),
                        Arrays.asList(new Interval(1, 4), new Interval(6, 7))
                ),
                Arrays.asList(
                        Arrays.asList(new Interval(3, 5), new Interval(8, 10)),
                        Arrays.asList(new Interval(4, 6), new Interval(9, 12)),
                        Arrays.asList(new Interval(5, 6), new Interval(8, 10))
                ),
                Arrays.asList(
                        Arrays.asList(new Interval(1, 3), new Interval(6, 9), new Interval(10, 11)),
                        Arrays.asList(new Interval(3, 4), new Interval(7, 12)),
                        Arrays.asList(new Interval(1, 3), new Interval(7, 10)),
                        Arrays.asList(new Interval(1, 4)),
                        Arrays.asList(new Interval(7, 10), new Interval(11, 12))
                ),
                Arrays.asList(
                        Arrays.asList(new Interval(1, 2), new Interval(3, 4), new Interval(5, 6), new Interval(7, 8)),
                        Arrays.asList(new Interval(2, 3), new Interval(4, 5), new Interval(6, 8))
                ),
                Arrays.asList(
                        Arrays.asList(new Interval(1, 2), new Interval(3, 4), new Interval(5, 6), new Interval(7, 8), new Interval(9, 10), new Interval(11, 12)),
                        Arrays.asList(new Interval(1, 2), new Interval(3, 4), new Interval(5, 6), new Interval(7, 8), new Interval(9, 10), new Interval(11, 12)),
                        Arrays.asList(new Interval(1, 2), new Interval(3, 4), new Interval(5, 6), new Interval(7, 8), new Interval(9, 10), new Interval(11, 12)),
                        Arrays.asList(new Interval(1, 2), new Interval(3, 4), new Interval(5, 6), new Interval(7, 8), new Interval(9, 10), new Interval(11, 12))
                )
        );

        int i = 1;
        List<List<List<Interval>>> inputs = new ArrayList<>();

        for (int j = 0; j < inputs1.size(); j++) {
            inputs.add(new ArrayList<List<Interval>>());

            for (int k = 0; k < inputs1.get(j).size(); k++) {
                inputs.get(j).add(new ArrayList<Interval>());

                for (int g = 0; g < inputs1.get(j).get(k).size(); g++) {
                    inputs.get(j).get(k).add(inputs1.get(j).get(k).get(g));
                }
            }
        }

        for (int j = 0; j < inputs.size(); j++) {
            System.out.println(i + ".\tEmployee Schedules:\n");

            for (int s = 0; s < inputs.get(j).size(); s++) {
                System.out.println("\t\t" + inputs.get(j).get(s));
            }

            System.out.println("\n\tEmployees' free time " + employeeFreeTime(inputs.get(j)));
            System.out.println(new String(new char[100]).replace('\0', '-'));
            i += 1;
        }*/

    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    static int n;

    public static int solve(int[] coins, int amount , int idx){

        if(amount == 0)
            return 0;

        if(idx == n)
            return Integer.MAX_VALUE-1;


        if(coins[idx] <= amount)
        {
            int taken = 1 + solve(coins, amount - coins[idx], idx);
            int skip = solve(coins, amount, idx+1);

            return Math.min(taken, skip);
        }
        else
            return solve(coins, amount, idx+1);


    }

    public static int coinChange(int[] coins, int amount) {
        n = coins.length;

        return solve(coins, amount , 0);

    }


    public static int TotalWays(int N)
    {
        // Code here

        int m = (int)(1e9 + 7);

        long[] dp = new long[N + 1];
        dp[0] = 1; // only one way no plots
        dp[1] = 2;

        for(int i=2 ;i <=N;i++){
            dp[i] = ((dp[i-1] % m) + (dp[i-2] % m)) % m;
        }


        return (int)((dp[N] * dp[N]) % m );

    }
}
