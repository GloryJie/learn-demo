package top.gloryjie.learn.alorithm.sort;

/**
 * @author jie
 * @since 2019/9/1
 */
public interface Sort<E extends Comparable> {

    E[] sort(E[] elements);


    default void swap(E[] elements, int i, int j){
        E temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

}
