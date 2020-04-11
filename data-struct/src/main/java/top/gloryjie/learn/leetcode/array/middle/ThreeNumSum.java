package top.gloryjie.learn.leetcode.array.middle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 *
 * @author jie
 * @since 2020/3/29
 */
public class ThreeNumSum {

    /**
     * @param nums
     * @return
     */
    public List<List<Integer>> solution(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) {
            return result;
        }

        // 排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) continue;
            int target = -nums[i];
            // 左指针为偏小值
            int left = i + 1;
            // 右指针为偏大值
            int right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] == target) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    // 左右两边分表找到和当前不相同的位置
                    while (left < nums.length && nums[left] == nums[left - 1]) left++;
                    while (right > left && nums[right] == nums[right + 1]) right--;
                } else if (nums[left] + nums[right] > target) {
                    // 说明目标值偏大, 则需要偏大值指针往左移, 稍微降低一点
                    right--;
                } else {
                    // 说明目标值偏小, 则需要偏大指针右移, 稍微提高一点
                    left++;
                }
            }
        }
        return result;
    }


    /**
     * 16 最接近的三数之和
     * https://leetcode-cn.com/problems/3sum-closest/
     *
     * @param nums
     * @return
     */
    public int threeSumCloset(int[] nums, int target) {
        // 排序
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            // 左指针为偏小值
            int left = i + 1;
            // 右指针为偏大值
            int right = nums.length - 1;
            while (left < right) {
                int currSum = nums[i] + nums[left] + nums[right];

                if (Math.abs(currSum - target) < Math.abs(result - target)) {
                    result = currSum;
                }
                if (currSum > target) {
                    right--;
                } else if (currSum < target) {
                    left++;
                } else {
                    return currSum;
                }
            }
        }
        return result;
    }


    @Test
    public void testSolutionOne() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
//        int[] nums = {-2, 0, 1, 1, 2};
//        int[] nums = {-4, -4, -4, 8, 8};
//        int[] nums = {0,0,0};
//        int[] nums = {-1,0,1,0};
//        int[] nums = {1,-1,-1,0};

        List<List<Integer>> solution = this.solution(nums);
        System.out.println(solution.toString());
    }

    @Test
    public void testThreeSumCloset() {
        int[] nums = {-1, 2, 1, -4};
        int target = 1;

        int result = this.threeSumCloset(nums, target);

        System.out.println(result);
    }
}
