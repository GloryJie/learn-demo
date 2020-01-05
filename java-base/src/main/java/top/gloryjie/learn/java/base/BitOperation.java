package top.gloryjie.learn.java.base;

import org.junit.Test;

/**
 * 位运算
 *
 * @author jie
 * @since 2019/12/12
 */
public class BitOperation {


    @Test
    public void shiftOperation() {
        System.out.println("*********右移**************");
        System.out.println(5 >> 2);
        System.out.println(Integer.toBinaryString(5 >> 2));
        System.out.println(-5 >> 2);
        System.out.println(Integer.toBinaryString(-5 >> 2));

        System.out.println("*********左移**************");
        System.out.println(5 << 2);
        System.out.println(Integer.toBinaryString(5 << 2));
        System.out.println(-5 << 2);
        System.out.println(Integer.toBinaryString(-5 << 2));
    }

    @Test
    public void unsignedShiftOperation() {
        System.out.println("*********无符号右移**************");
        System.out.println(5 >>> 2);
        System.out.println(Integer.toBinaryString(5 >>> 2));
        System.out.println(-5 >>> 2);
        System.out.println(Integer.toBinaryString(-5 >>> 2));
    }

    @Test
    public void andOperation() {
        System.out.println(5 & 3);
        System.out.println(5 & 31);
        System.out.println(-5 & -3);
        System.out.println(-5 & 3);
        System.out.println(-5 & 31);
    }

    @Test
    public void orOperation() {
        System.out.println(5 | 3);
        System.out.println(5 | 31);
        System.out.println(-5 | -3);
        System.out.println(-5 | 3);
        System.out.println(-5 | 31);
    }

    @Test
    public void andOrOperation() {
        System.out.println(5 ^ 3);
    }

    @Test
    public void nonOperation() {
        System.out.println(~5);
    }
}
