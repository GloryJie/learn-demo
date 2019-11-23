package top.gloryjie.learn.alorithm.sort;

/**
 * @author jie
 * @since 2019/9/4
 */
public class ShellSort<E extends Comparable> implements Sort<E> {

    @SuppressWarnings("unchecked")
    @Override
    public E[] sort(E[] elements) {

        int step = 1;

        // 步长计算方式, 1, 4, 9
        while (step < elements.length / 3)
            step = step * 3 + 1;

        // 根据步长step来进行分组
        for (; step > 0; step = step / 3) {
            // i 标识第几组, step正好是第一组待排序的1号元素(插入排序认为0号元素有序)
            for (int i = step; i < elements.length; i++) {
                // k标识组内的元素位置, 对组内元素进行插入排序
                for (int k = i; k >= step; k -= step) {
                    if (elements[k].compareTo(elements[k - step]) < 0) {
                        swap(elements, k, k - step);
                    }
                }

            }
        }

        return elements;
    }

}
