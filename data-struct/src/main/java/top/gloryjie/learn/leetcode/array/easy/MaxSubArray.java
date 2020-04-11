package top.gloryjie.learn.leetcode.array.easy;

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
            // 贪心所在,取当前位置的最大值
            // 若num[i] > currNum + nums[i], 表示之前的和加上自身反而小于自身,所以肯定取最大的一个,即自身
            currNum = Math.max(nums[i], currNum + nums[i]);

            // 将当前位置最大和 同 迄今最大和进行比较
            maxNum = Math.max(maxNum, currNum);
        }
        return maxNum;
    }


    /**
     * 动态规划求解
     * 关键点在于: 为什么之前求和的值(currSum) <= 0时,替换成当前位置的元素?
     * 数组组成有:
     * 1.假如全是负数，那就是找最大值即可，因为负数越加越小。Math.max(maxNum, currSum);即可求得最大值
     * 2.如果有正数，则肯定从正数开始计算和，不然前面有负值，和肯定变小了，所以从正数开始。
     * 3.当和小于零时，无论下个元素是什么,都会使得相加后反而变小.所以这个区间就告一段落了，而这个区间是否为最大值,已经记录在maxNum中.
     * 然后从下一个正数重新开始计算(也就是又回到 2 了)。
     *
     * @param nums
     * @return
     */
    public int solutionThree(int[] nums) {
        int maxNum = nums[0];
        int currSum = 0;

        for (int element : nums) {
            if (currSum > 0) {
                currSum += element;
            } else {
                currSum = element;
            }
            // 比较每一步的最大值
            maxNum = Math.max(maxNum, currSum);
        }
        return maxNum;
    }


    /**
     * TODO: 2020/1/19  还不理解
     * 分治算法
     *
     * @param nums
     * @return
     */
    public int solutionFour(int[] nums) {
        return dcHelper(nums, 0, nums.length - 1);
    }


    /**
     * 以partition为分界线, 计算含partition在内的最大子序列和
     * 左: 以partition为起点, 向左遍历, currNum记录每次的相加和, leftSum记录最大和
     * 右: 以partition + 1为起点, 向右遍历, currNum记录每次的相加和, rightSum记录最大和
     *
     * @param nums
     * @param left
     * @param right
     * @param partition
     * @return
     */
    private int dcCrossSum(int[] nums, int left, int right, int partition) {
        if (left == right) {
            return nums[left];
        }

        // 从边界值(包含边界值)开始往左边计算, 求左半部分最大值
        int leftSum = Integer.MIN_VALUE;
        int currSum = 0;
        for (int i = partition; i > left - 1; i--) {
            currSum += nums[i];
            leftSum = Math.max(leftSum, currSum);
        }

        // 从边界值(不含边界值)开始往右边开始计算, 求右半部分最大子序列值
        int rightSum = Integer.MIN_VALUE;
        currSum = 0;
        for (int i = partition + 1; i < right + 1; i++) {
            currSum += nums[i];
            rightSum = Math.max(rightSum, currSum);
        }

        return leftSum + rightSum;
    }


    /**
     * 将nums进行划分成子问题求解
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    private int dcHelper(int[] nums, int left, int right) {
        if (left == right) return nums[left];

        int partition = (left + right) / 2;

        // 左半部分求解(含partition), 左半部分所得最大和不一定含partition
        int leftSum = dcHelper(nums, left, partition);
        // 右半部分求解(不含partition)
        int rightSum = dcHelper(nums, partition + 1, right);
        // 求包含partition的子序列最大和
        int crossSum = dcCrossSum(nums, left, right, partition);

        // 比较左半部分, 右半部分, 包含partition
        return Math.max(Math.max(leftSum, rightSum), crossSum);
    }


    public static void main(String[] args) throws Exception {
        int[] testExample = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        MaxSubArray maxSubArray = new MaxSubArray();


        int result = maxSubArray.solutionFour(testExample);

        System.out.println(result);
    }
}
