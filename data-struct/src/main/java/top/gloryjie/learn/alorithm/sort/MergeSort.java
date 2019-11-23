package top.gloryjie.learn.alorithm.sort;

import javax.security.auth.login.LoginException;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 归并排序
 *
 * @author jie
 * @since 2019/9/15
 */
public class MergeSort<E extends Comparable> implements Sort<E> {

    // 辅助
    private E[] auxiliaryArray;


    @SuppressWarnings("unchecked")
    @Override
    public E[] sort(E[] elements) {
        auxiliaryArray = (E[]) Array.newInstance(elements[0].getClass(), elements.length);
        sort(elements, 0, elements.length -1);
        return elements;
//        return simpleAchieve(elements);
    }


    /**
     * 原地归并
     * @param elements
     * @param low
     * @param high
     */
    private void sort(E[] elements, int low, int high) {
        if (high <= low) return;
        int mid =low + (high - low) / 2;
        sort(elements, low, mid);
        sort(elements, mid + 1, high);
        merge(elements, low, mid, high);
    }


    @SuppressWarnings("unchecked")
    private void merge(E[] elements, int low, int mid, int high) {
        // i, j 分别为待归并两个数组的指针
        int i = low;
        int j = mid + 1;

        // 将两个待归并的数组元素复制到辅助数组中
        for (int k = low; k <= high; k++) {
            auxiliaryArray[k] = elements[k];
        }

        // 归并两个数组, 通过i,j指针的移动,将辅助数组的元素复制到原数组中
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                // 当 i > mid, 说明i所在的数组已经归并完成
                elements[k] = auxiliaryArray[j++];
            } else if (j > high) {
                // 当 j > high, 说明j所在的数组已经归并完成
                elements[k] = auxiliaryArray[i++];
            } else if (auxiliaryArray[i].compareTo(auxiliaryArray[j]) < 0) {
                elements[k] = auxiliaryArray[i++];
            } else {
                elements[k] = auxiliaryArray[j++];
            }
        }

    }


    /**
     * 简单的实现,每次归并都会生成新的数组
     *
     * @param elements
     * @return
     */
    private E[] simpleAchieve(E[] elements) {
        // 递归调用结束条件
        if (elements.length <= 1) return elements;
        int mid = elements.length / 2;

        // 排左半边
        E[] a = simpleAchieve(Arrays.copyOfRange(elements, 0, mid));
        // 排右半边
        E[] b = simpleAchieve(Arrays.copyOfRange(elements, mid, elements.length));
        // 合并两个已经有序的数组
        return merge(a, b);
    }


    /**
     * 简单的归并实现, 创建一个数组来存储排序结果
     *
     * @param a
     * @param b
     * @return
     */
    @SuppressWarnings("unchecked")
    private E[] merge(E[] a, E[] b) {
        // 创建一个新的数组存放排序结果
        E[] result = (E[]) Array.newInstance(a[0].getClass(), a.length + b.length);

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < a.length && j < b.length) {
            if (a[i].compareTo(b[j]) <= 0) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        while (i < a.length) {
            result[k++] = a[i++];
        }

        while (j < b.length) {
            result[k++] = b[j++];
        }

        return result;
    }
}
