package top.gloryjie.learn.alorithm.sort;

/**
 * @author jie
 * @since 2019/9/2
 */
public class ChooseSort<E extends Comparable> implements Sort<E> {

    /**
     * 每次遍历都选择最小的元素放在前面
     * 不断的选择剩余元素中的最小者
     */
    @SuppressWarnings("unchecked")
    @Override
    public E[] sort(E[] elements) {
        for (int i=0;i<elements.length;i++){
            int minIndex = i;
            for (int j=i+1;j<elements.length;j++){
                if (elements[j].compareTo(elements[minIndex]) < 0){
                    minIndex = j;
                }
            }
            if (minIndex != i){
                swap(elements, i, minIndex);
            }
        }
        return elements;
    }
}
