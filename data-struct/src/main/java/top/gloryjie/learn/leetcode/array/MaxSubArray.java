package top.gloryjie.learn.leetcode.array;

/**
 * @author jie
 * @since 2020/1/18
 */
public class MaxSubArray {

    /**
     * 暴力求解, n^3 ,时间不通过, leetcode第200个测试用例不通过
     *
     * @param nums
     * @return
     */
    public int solutionOne(int[] nums) {
        if (nums == null || nums.length < 1) {
            throw new IllegalArgumentException();
        }
        int maxResult = nums[0];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int tempResult = 0;
                for (int k = i; k <= j; k++) {
                    tempResult += nums[k];
                }
                maxResult = Math.max(tempResult, maxResult);
            }
        }
        return maxResult;
    }


    /**
     * 贪心算法求解
     * currNum 当前位置的最大和
     * maxNum迄今为止最大和
     */
    public int solutionTwo(int[] nums) {
        int currNum = nums[0];
        int maxNum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // 贪心所在,只考虑当前位置.不需要考虑后面的元素
            // 若num[i] > currNum + nums[i], 表示当前元素大于前面所有之和.则将之前的丢弃
            currNum = Math.max(nums[i], currNum + nums[i]);
            // 将当前位置最大和 同 迄今最大和进行比较
            maxNum = Math.max(maxNum, currNum);
        }
        return maxNum;
    }

    /**
     * 分治算法
     */
    static class DivierConquer {


        public int crossSum(int[] nums, int left, int right, int partition) {
            if (left == right) {
                return nums[left];
            }

            int leftSum = Integer.MIN_VALUE;
            int currSum = 0;
            for (int i = partition; i > left - 1; i--) {
                currSum += nums[i];
                leftSum = Math.max(leftSum, currSum);
            }

            int rightSum = Integer.MIN_VALUE;
            currSum = 0;
            for (int i = partition + 1; i < right + 1; i++) {
                currSum += nums[i];
                rightSum = Math.max(rightSum, currSum);
            }

            return leftSum + rightSum;
        }


        public int helper(int[] nums, int left, int right) {
            if (left == right) return nums[left];

            int partition = (left + right) / 2;

            int leftSum = helper(nums, left, partition);
            int rightSum = helper(nums, partition + 1, right);
            int crossSum = crossSum(nums, left, right, partition);

            return Math.max(Math.max(leftSum, rightSum), crossSum);
        }


        public int maxSubArray(int[] nums) {
            return helper(nums, 0, nums.length - 1);
        }
    }


    public static void main(String[] args) throws Exception {
        int[] testExample = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        MaxSubArray maxSubArray = new MaxSubArray();


        int result = maxSubArray.solutionTwo(testExample);

        System.out.println(result);

    }
}
