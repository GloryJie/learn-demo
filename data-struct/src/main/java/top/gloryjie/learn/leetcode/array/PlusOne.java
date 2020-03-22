package top.gloryjie.learn.leetcode.array;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/plus-one/
 * 数字加1
 *
 * @author jie
 * @since 2020/2/4
 */
public class PlusOne {

    public int[] solutionOne(int[] digits) {
        return solutionOneHelper(digits, digits.length - 1);
    }


    /**
     * 使用递归进行处理
     *
     * @param digits
     * @param right  需要加1 的位置
     * @return
     */
    public int[] solutionOneHelper(int[] digits, int right) {
        if (right < 0) {
            int[] temp = new int[digits.length + 1];
            temp[0] = 1;
            return temp;
        }
        if (digits[right] < 9) {
            digits[right] += 1;
            return digits;
        } else {
            digits[right] = 0;
            // 递归处理
            return solutionOneHelper(digits, right - 1);
        }
    }


    /**
     * 通过循环解决,十分巧妙
     *
     * @param digits
     * @return
     */
    public int[] solutionTwo(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            // 巧妙之处: 求该位上的余数, 若为0,则表示需要进位
            digits[i] %= 10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        // 若至此, 则表示之前的数组全为9
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }


    public static void main(String[] args) {
        int[] testCase = {9, 9, 9};

        PlusOne plusOne = new PlusOne();

//        System.out.println(Arrays.toString(plusOne.solutionOne(testCase)));

        System.out.println(Arrays.toString(plusOne.solutionTwo(testCase)));

    }

}
