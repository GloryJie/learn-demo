package top.gloryjie.learn.leetcode.array.easy;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/merge-sorted-array/
 * 合并两个有序数组
 *
 * @author jie
 * @since 2020/2/4
 */
public class MergeTwoSortArray {


    /**
     * 双指针
     * nums1数组作为输出结果的数组
     * 若nums2的元素需要插入到nums1,交换两数组对应指针的元素.
     * 此时,使用nums2来暂存nums1[index1],避免将index1后面的元素往后移动腾出位置插入
     * 但是,将nums1上的元素存放到nums2上,有可能会破坏nums2的有序性,所以需要维持nums2的有序性
     *
     * @param nums1 数组1, 用来存放最后结果
     * @param m     nums1的长度
     * @param nums2 数组2
     * @param n     数组2的长度
     */
    public void solutionOne(int[] nums1, int m, int[] nums2, int n) {
        int index1 = 0;
        int index2 = 0;
        while (index1 < m && index2 < n) {
            if (nums2[index2] < nums1[index1]) {
                //交互nums1 和 nums2 的元素
                int temp = nums1[index1];
                nums1[index1] = nums2[index2];
                nums2[index2] = temp;


                index1++;

                // 从新调整,避免nums1的元素破坏nums2的有序性
                adjustmentNums2(nums2, index2, n);
            } else {
                index1++;
            }
        }

        while (index2 < n) {
            nums1[index1++] = nums2[index2++];
        }
    }

    public void adjustmentNums2(int[] nums2, int index, int len) {
        for (int i = index; i < len - 1; i++) {
            if (nums2[i] > nums2[i + 1]) {
                int temp = nums2[i];
                nums2[i] = nums2[i + 1];
                nums2[i + 1] = temp;
            } else {
                break;
            }
        }
    }


    /**
     * 合并后再排序
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void solutionTwo(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }


    /**
     * 双指针(或者说三指针), 从前往后
     * 将nums1中的元素复制出来成新数组, 则将nums1Copy, nums2逐个
     * 剩余元素的长度计算需要留意
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void solutionThree(int[] nums1, int m, int[] nums2, int n) {
        int[] nums1Copy = new int[m];
        System.arraycopy(nums1, 0, nums1Copy, 0, m);

        // nums1指针, 即结果数据的指针,此时可以认为nums1位空数组
        int p = 0;
        // nums1Copy指针
        int p1 = 0;
        // nums2指针
        int p2 = 0;

        while (p1 < m && p2 < n) {
            if (nums1Copy[p1] < nums2[p2]) {
                nums1[p++] = nums1Copy[p1++];
            } else {
                nums1[p++] = nums2[p2++];
            }
        }

        if (p1 < m) {
            System.arraycopy(nums1Copy, p1, nums1, p1 + p2, m + n - p1 - p2);
        }
        if (p2 < n) {
            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
        }
    }


    /**
     * 双指针,从后往前
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void solutionFour(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;

        while (p1 >= 0 && p2 >= 0) {
            nums1[p--] = nums1[p1] < nums2[p2] ? nums2[p2--] : nums1[p1--];
        }

        // 若nums2为填完,则将其继续填充
        if (p2 > 0) {
            System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
        }
    }


    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;

        MergeTwoSortArray merge = new MergeTwoSortArray();

        merge.solutionFour(nums1, m, nums2, n);

        System.out.println(Arrays.toString(nums1));

    }


}
