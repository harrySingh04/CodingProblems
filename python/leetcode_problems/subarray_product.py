import math

def sub_array_logarithmic(nums, k):
    # Handle edge case where k is 0
    if k == 0:
        return 0

    # Precompute the logarithm of k for efficiency
    logK = math.log(k)

    # Initialize a list to store the cumulative sum of logarithms of nums
    logs_sum = [0]

    # Calculate the cumulative sum of logarithms
    for num in nums:
        logs_sum.append(logs_sum[-1] + math.log(num))

    # Initialize count of sub arrays with product less than k
    count = 0

    # Iterate over each possible start of a subarray
    for i in range(len(logs_sum)):
        # Initialize binary search range for end of subarray
        low, high = i + 1, len(logs_sum)

        # Perform binary search to find the end of the subarray
        while low < high:
            mid = (low + high) // 2

            # Check if the product of the subarray is less than k
            # A small tolerance (-1e-9) is used to handle floating-point precision issues.
            if logs_sum[mid] < logs_sum[i] + logK - 1e-9:
                # If true, move the low pointer to mid + 1
                low = mid + 1
            else:
                # If false, move the high pointer to mid
                high = mid

        # Increment count by the number of valid sub arrays ending at low - 1
        count += low - i - 1

    # Return the total count of sub arrays with product less than k
    return count
def max_product_array_brute_force(num,k):

    count = 0

    for i in range(len(nums)):

        if nums[i] >= k:
            continue
        curr = 1

        for j in range(i,len(nums)):
            curr = nums[j] * curr

            if curr >= k:
                break
            count += 1


    return count

def sub_array_product_sliding_window(nums,k):

    if k <= 1:
        return 0

    i , j, product = 0,0,1

    n = len(nums)

    count = 0


    while j < n:
        product = product * nums[j]

        while product >= k:
            product = product // nums[i]
            i += 1

        count += j - i + 1
        j += 1


    return count


if __name__ == '__main__':

    nums = [10, 5, 2, 6]
    k = 100
    print(max_product_array_brute_force(nums,k))
    print(sub_array_product_sliding_window(nums,k))
    print(sub_array_logarithmic(nums,k))









