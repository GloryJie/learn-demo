package top.gloryjie.learn.leetcode.array;

import java.util.Arrays;

/**
 * 删除有序数组的重复项
 *
 * @author Jie-R 1620
 * @since 1.0
 */
public class RemoveDuplicate {

    /**
     * 自实现,双指针, 分别是快慢指针
     * 第二层循环具有重复的遍历, 比较式不能使用 != , 而必须使用<, 例如[0,0,1,1,1,2,3]
     *
     * @param nums 有序数组
     * @return
     */
    public int solutionOne(int[] nums) {
        int count = 1;
        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            for (; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    nums[i + 1] = nums[j];
                    count++;
                    break;
                }
            }
            // 快指针已到末尾,则结束
            if (j >= (nums.length - 1)) {
                break;
            }
        }
        return count;
    }

    /**
     * 官方题解，简洁优雅的双指针,只需要一层循环
     *
     * @param nums
     * @return
     */
    public int solutionTwo(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }


    public static void main(String[] args) {
        // int[] nums = {0,0,1,1,1,2,2,3,3,4};

        int[] nums = {-1, 0, 0, 0, 0, 3, 3};

        RemoveDuplicate solution = new RemoveDuplicate();
        int result = solution.solutionOne(nums);

        System.out.println(Arrays.toString(nums));
        System.out.println(result);
    }


}
