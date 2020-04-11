package top.gloryjie.learn.leetcode.array.middle;

import org.junit.Assert;
import org.junit.Test;

/**
 * 11. 盛最多水的容器
 * https://leetcode-cn.com/problems/container-with-most-water/
 *
 * @author jie
 * @since 2020/3/24
 */
public class MaxArea {

    /**
     * 官方题解
     *
     * @param height
     * @return
     */
    public int solutionOne(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            maxArea = Math.max(maxArea, (right - left) * Math.min(height[left], height[right]));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }


    @Test
    public void testSolutionOne() {
        int[] heightArray = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int result = this.solutionOne(heightArray);

        System.out.println(result);
        Assert.assertEquals(49, result);
    }

}
