package leetcode_problem;

import java.util.*;

public class SubArrayProduct {

    // Brute-force approach to count subarrays with product less than k
    public static int maxProductArrayBruteForce(int[] nums, int k) {
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            // Skip elements greater than or equal to k
            if (nums[i] >= k) {
                continue;
            }

            int product = 1;
            for (int j = i; j < nums.length; j++) {
                product *= nums[j];

                // If the product exceeds k, stop further processing
                if (product >= k) {
                    break;
                }
                count++;
            }
        }
        return count;
    }

    // Sliding window approach to count subarrays with product less than k
    public static int subArrayProductSlidingWindow(int[] nums, int k) {
        if (k <= 1) return 0;

        int i = 0, j = 0, product = 1, count = 0;
        int n = nums.length;

        while (j < n) {
            product *= nums[j];

            // Shrink the window while product is greater than or equal to k
            while (product >= k && i <= j) {
                product /= nums[i];
                i++;
            }

            // Number of valid subarrays ending at j
            count += j - i + 1;
            j++;
        }
        return count;
    }

    // Logarithmic approach using binary search to count subarrays with product less than k
    public static int subArrayLogarithmic(int[] nums, int k) {
        if (k == 0) return 0;

        double logK = Math.log(k);
        List<Double> logsSum = new ArrayList<>();
        logsSum.add(0.0);

        // Compute cumulative logarithmic sum
        for (int num : nums) {
            logsSum.add(logsSum.get(logsSum.size() - 1) + Math.log(num));
        }

        int count = 0;
        int n = logsSum.size();

        for (int i = 0; i < n; i++) {
            int low = i + 1, high = n;

            // Binary search for the rightmost valid subarray
            while (low < high) {
                int mid = (low + high) / 2;

                if (logsSum.get(mid) < logsSum.get(i) + logK - 1e-9) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }

            count += low - i - 1;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {10, 5, 2, 6};
        int k = 100;

        System.out.println("Brute Force: " + maxProductArrayBruteForce(nums, k));
        System.out.println("Sliding Window: " + subArrayProductSlidingWindow(nums, k));
        System.out.println("Logarithmic Approach: " + subArrayLogarithmic(nums, k));
    }
}
