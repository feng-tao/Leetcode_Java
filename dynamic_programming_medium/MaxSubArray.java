/*
// Source : https://oj.leetcode.com/problems/maximum-subarray/
// Date   : 01/11/2017

/********************************************************************************** 
* 
* Find the contiguous subarray within an array (containing at least one number) 
* which has the largest sum.
* 
* For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
* the contiguous subarray [4,−1,2,1] has the largest sum = 6.
* 
* More practice:
* 
* If you have figured out the O(n) solution, try coding another solution using 
* the divide and conquer approach, which is more subtle.
* 
*               
**********************************************************************************/

package Leetcode_Java.dynamic_programming_medium;

/**
 *
 * @author Borui Wang
 */
public class MaxSubArray {

    public int maxSubArrayGreedy(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        //maxSum is global sum
        int maxSum = Integer.MIN_VALUE;
        //sum is local sum
        int sum = 0;

        for (int num : nums) {
            sum += num;
            maxSum = Math.max(maxSum, sum);
            //assume local sum is always greater than 0
            //only keep sum value if it is greater than 0
            sum = Math.max(sum, 0);

        }

        return maxSum;
    }

    public int maxSubArrayDP(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        //maxSum is global sum
        int maxSum = nums[0];
        //sum is local sum
        int sum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            //find max local sum, either current element or previous sum plus current element
            sum = Math.max(nums[i], sum + nums[i]);
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }

    public int maxSubArrayDivideNConquer(int[] nums) {
        /*
    Divide-and-conquer method.
    The maximum summation of subarray can only exists under following conditions:
    1. the maximum summation of subarray exists in left half.
    2. the maximum summation of subarray exists in right half.
    3. the maximum summation of subarray exists crossing the midpoints to left and right. 
    1 and 2 can be reached by using recursive calls to left half and right half of the subarraies. 
    Condition 3 can be found starting from the middle point to the left,
    then starting from the middle point to the right. Then adds up these two parts and return. 
    
    T(n) = 2*T(n/2) + O(n)
    this program runs in O(nlogn) time
         */

        int maxsum = subArray(nums, 0, nums.length - 1);
        return maxsum;
    }

    private int subArray(int[] A, int left, int right) {
        if (left == right) {
            //base case
            return A[left];
        }
        int mid = left + (right - left) / 2;
        int leftsum = subArray(A, left, mid); //left part of the subarray sum, condition 1
        int rightsum = subArray(A, mid + 1, right); //right part of the subarray sum, condition 2
        int middlesum = midSubArray(A, left, mid, right); //cross part of the subarray sum, condition 3

        //following if condition will return middlesum if lost the "=" under the conditon of leftsum = rightsum, which may be problematic if leftsum = rightsum < middlesum.
        //for example: [-1, -1, -2, -2]
        if (leftsum >= rightsum && leftsum >= middlesum) {
            return leftsum;
        }
        if (rightsum >= leftsum && rightsum >= middlesum) {
            return rightsum;
        }
        return middlesum;
    }

    private int midSubArray(int[] A, int left, int mid, int right) {
        int leftsum = Integer.MIN_VALUE;
        int rightsum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += A[i];
            if (leftsum < sum) {
                leftsum = sum;
            }
        }

        sum = 0;
        for (int j = mid + 1; j <= right; j++) {
            sum += A[j];
            if (rightsum < sum) {
                rightsum = sum;
            }
        }

        return leftsum + rightsum;
    }

    public static void main(String[] args) {
        MaxSubArray ms = new MaxSubArray();
        System.out.println(ms.maxSubArrayDivideNConquer(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
