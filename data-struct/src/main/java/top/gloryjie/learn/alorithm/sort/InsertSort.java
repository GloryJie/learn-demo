package top.gloryjie.learn.alorithm.sort;

/**
 * 插入排序
 *
 * @author jie
 * @since 2019/9/3
 */
public class InsertSort<E extends Comparable> implements Sort<E> {

    /**
     * 从已经有序的列表内插入元素
     * 找到第一个比待插入元素小的元素位置插入
     */
    @SuppressWarnings("unchecked")
    @Override
    public E[] sort(E[] elements) {
        for (int i = 1; i < elements.length; i++) {
            // i的位置为目标元素
            for (int j = i; j > 0; j--) {
                // 找到第一个 <= 目标元素即停
                if (elements[j].compareTo(elements[j - 1]) < 0) {
                    // 当前元素比前面大, 通过交换往后移
                    swap(elements, j - 1, j);
                } else {
                    break;
                }
            }
        }
        return elements;
    }
}
