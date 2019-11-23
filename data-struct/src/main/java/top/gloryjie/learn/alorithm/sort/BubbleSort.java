package top.gloryjie.learn.alorithm.sort;

/**
 * 冒泡排序
 *
 * @author jie
 * @since 2019/9/1
 */
public class BubbleSort<E extends Comparable> implements Sort<E> {


    /**
     * 顾名思义, 大的元素不断的往右边移动
     * 每一层遍历, 都会将大的元素往右挪, 并且会得出当前遍历所得最大元素放在最右边
     *
     */
    @SuppressWarnings("unchecked")
    @Override
    public E[] sort(E[] elements) {
        // 优化点: 在上一次遍历过程中,若不存在交换操作则可断定已经有序
        boolean swapFlag = true;
        for (int i = elements.length - 1; i >= 0 && swapFlag; i--) {
            swapFlag = false;
            for (int j = 0; j < i; j++) {
                if (elements[j].compareTo(elements[j + 1]) > 0) {
                    swap(elements, j, j + 1);
                    swapFlag = true;
                }
            }
        }
        return elements;
    }

}
