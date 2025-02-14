## Leetcode Problems

This repository contains the solutions to Leetcode problems in Python. 

### Problems

1. Sub Array product less than k

    * **Brute Force**: The brute force solution iterates through each element in the array and
checks all possible sub arrays starting from that element.
For each subarray, it calculates the product of all elements and checks if it is less than k. 
The time complexity of this brute force solution is O(n^2), where n is the length of the input array. 
This is because we have two nested loops iterating through the array. Not optimal for large arrays.

    * **Sliding Window**: The sliding window solution uses a sliding window of size k to calculate the product 
of all elements in the window. The thought process is to have 2 variables both starting at 0 ,
we start the product at 1 and multiply it by the current element as we iterate through the array.
As the product increases the limit k , then we shrink the window by moving the left pointer to the right.
The time complexity of this solution is O(n), where n is the length of the input array.
    * **Logarithmic Solution**: The logarithmic solution is similar to the sliding window solution, 
but instead of using a sliding window, it uses a binary search to find the index of the first element
that is greater than k. The time complexity of this solution is O(nlogn),
where n is the length of the input array.

2. 


