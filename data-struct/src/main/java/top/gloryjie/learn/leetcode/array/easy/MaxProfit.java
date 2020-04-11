package top.gloryjie.learn.leetcode.array.easy;

import org.junit.Assert;
import org.junit.Test;

/**
 * 121. 买卖股票的最佳时机
 * 122. 买卖股票的最佳时机2
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 *
 * @author jie
 * @since 2020/3/23
 */
public class MaxProfit {

    /**
     * 121
     * 自实现, 思路类似于求最大子序和(贪心)
     * 最主要是 maxResult 是历次子序和比较中的最大值
     * 这个序列的特殊在于起始位置为序列最小值，只有后面的值大于起始位置的值，都属于这个序列。
     * 在数组中找出这样特点的数组，并在其中找出和起始位置的最大差值，就是当前序列的最佳收入。
     * 而使用历史最大值，记录各子序列的最大值即可。
     *
     * @param prices
     * @return
     */
    public int solutionOne(int[] prices) {
        int maxResult = Integer.MIN_VALUE;
        for (int i = 0, j = 1; j < prices.length; j++) {
            maxResult = Math.max(prices[j] - prices[i], maxResult);
            if (prices[j] < prices[i]) {
                i = j;
            }
        }
        return Math.max(maxResult, 0);
    }


    /**
     * 121
     * 官方题解,思路差不多,比one简洁
     *
     * @param prices
     * @return
     */
    public int solutionTwo(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }
        return maxProfit;
    }


    /**
     * 122自实现, 找到自增的子序列左右两端, 然后一次性求解差值
     *
     * @param prices
     * @return
     */
    public int solutionThree(int[] prices) {
        int count = 0;
        int i = 0;
        for (int j = 1; j < prices.length; j++) {
            if (prices[j] <= prices[j - 1]) {
                // 当前递增子序列已经结束, 计算差值. 若连续递减,此时 i=j-1, 即 count += 0
                count += (prices[j - 1] - prices[i]);
                // 此时从新开始标记递增子序列
                i = j;
            }
        }
        // 需要考虑最后出现一直递增的场景
        if (prices.length > 1 && prices[prices.length - 1] > prices[prices.length - 2]) {
            count += (prices[prices.length - 1] - prices[i]);
        }
        return count;
    }

    /**
     * 122官方题解, 贪心算法
     * 和three的实现区别在于这里每次找到递增都做差值, 而不是像three那样找到递增序列的最小和最大再做差值计算
     *
     * @param prices
     * @return
     */
    public int solutionFour(int[] prices) {
        int count = 0;
        for (int j = 1; j < prices.length; j++) {
            if (prices[j] > prices[j - 1]) {
                count += (prices[j] - prices[j - 1]);
            }
        }
        return count;
    }


    @Test
    public void testSolutionOne() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int result = this.solutionOne(prices);
        System.out.println(result);
        Assert.assertEquals(5, result);
    }

    @Test
    public void testSolutionTwo() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int result = this.solutionTwo(prices);
        System.out.println(result);
        Assert.assertEquals(5, result);
    }

    @Test
    public void testSolutionThree() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int result = this.solutionThree(prices);
        System.out.println(result);
        Assert.assertEquals(7, result);
    }

    @Test
    public void testSolutionFour() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int result = this.solutionFour(prices);
        System.out.println(result);
        Assert.assertEquals(7, result);
    }


}
