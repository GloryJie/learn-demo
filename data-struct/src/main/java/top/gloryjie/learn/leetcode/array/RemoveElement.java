package top.gloryjie.learn.leetcode.array;

import java.util.Arrays;

/**
 * Description ...
 *
 * @author Jie-R 1620
 * @since 1.0
 */
public class RemoveElement {

    /**
     * 使用快慢指针
     *
     * @param nums
     * @param val
     * @return
     */
    public int wayOne(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    /**
     * 使用头尾指针
     *
     * @param nums
     * @param val
     * @return
     */
    public int wayTow(int[] nums, int val) {
        int i = 0;
        int n = nums.length;
        while (i < n) {
            if (nums[i] == val) {
                nums[i] = nums[--n];
            } else {
                i++;
            }
        }
        return i;
    }


    public static void main(String[] args) {
        int[] nums = {1, 1, 2};

        RemoveElement solution = new RemoveElement();

        final int result = solution.wayTow(nums, 1);

        System.out.println(Arrays.toString(nums));

        System.out.println(result);

    }
}
