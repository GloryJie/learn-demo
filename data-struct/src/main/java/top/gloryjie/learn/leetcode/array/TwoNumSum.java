package top.gloryjie.learn.leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * https://leetcode-cn.com/problems/two-sum/
 *
 * @author jie
 * @since 2020/1/6
 */
public class TwoNumSum {

    /**
     * 方式一, 使用两层循环暴力求解
     */
    public int[] wayOne(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((nums[i] + nums[j]) == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /**
     * 方式二,空间换时间
     * 使用散列表map来存放遍历过的值, key -> 值, value -> 数组下标
     * 通过target - nums[i] 得出目标值,可在map直接寻得
     * 此方法为
     */
    public int[] wayTwo(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            // 直接寻找另一半的目标值
            int anotherHalfValue = target - nums[i];
            // 此时目标值为从完整表中寻找
            if (map.containsKey(anotherHalfValue) && map.get(anotherHalfValue) != i) {
                return new int[]{i, map.get(anotherHalfValue)};
            }
        }
        return new int[0];
    }

    /**
     * 方式三,空间换时间
     * 上述方式二队nums进行两次遍历, 第一次遍历存放所有记录, 第二季根据记录去寻找目标值
     * 而此方式为边记录,边寻找目标值,只进行一次遍历
     */
    public int[] wayThree(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 直接寻找另一半目标值
            int anotherHalfValue = target - nums[i];
            // 目标值为从"历史"遍历中寻找
            if (map.get(anotherHalfValue) != null) {
                // map.get(anotherHalfValue)为历史值,所以在前
                return new int[]{map.get(anotherHalfValue), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }


    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};

        int target = 9;

        TwoNumSum sum = new TwoNumSum();

//        int[] result = sum.wayTwo(nums, target);

//        int[] result = sum.wayTwo(nums, target);

        int[] result = sum.wayThree(nums, target);

        System.out.println(Arrays.toString(result));
    }


}
