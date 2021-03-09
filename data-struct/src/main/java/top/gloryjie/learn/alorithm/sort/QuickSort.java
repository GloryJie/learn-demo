package top.gloryjie.learn.alorithm.sort;

/**
 * @author jie
 * @since 2019/12/1
 */
public class QuickSort<E extends Comparable> implements Sort<E> {

    @Override
    public E[] sort(E[] elements) {
        sort(elements, 0, elements.length - 1);
        return elements;
    }


    private void sort(E[] elements, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivotIndex = partition(elements, low, high);
        sort(elements, low, pivotIndex - 1);
        sort(elements, pivotIndex + 1, high);
    }

    /**
     * 分区
     *
     * @param elements
     * @param low
     * @param high
     * @return pivotIndex
     */
    private int partition(E[] elements, int low, int high) {
        int left = low;
        int right = high;
        E pivotItem = elements[low];
        while (left < right) {
            while (less(elements[left], pivotItem)) {
                left++;
            }
            while (less(pivotItem, elements[right])) {
                right--;
            }
            if (left < right) {
                swap(elements, left, right);
            }
        }
        return left;
    }
}
