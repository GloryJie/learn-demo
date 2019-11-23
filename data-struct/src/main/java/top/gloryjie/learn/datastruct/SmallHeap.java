package top.gloryjie.learn.datastruct;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆
 *
 * @author jie
 * @since 2019/8/29
 */
public class SmallHeap<E extends Comparable<E>> {

    private List<E> array = new ArrayList<>();

    public void add(E element) {
        if (array.isEmpty()) {
            array.add(element);
        }else{
            array.add(element);
            siftUp(size()-1, element);
        }


    }

    private void siftUp(int index, E element){
        while (index > 0){
            int parentIndex = parentIndex(index);
            // 当前元素大于父节点,停止
            if (element.compareTo(array.get(parentIndex)) > 0){
              break;
            }
            // 当前元素小于父节点, 交换元素
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void siftDown(int index, E element){
        while (index < size()){
            int left = leftChild(index);
        }
    }


    public int size(){
        return array.size();
    }

    private void swap(int a, int b){
        E temp = array.get(a);
        array.set(a,array.get(b));
        array.set(b, temp);
    }


    private int parentIndex(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index){
        return index * 2 + 1;
    }

    private int rightChild(int index){
        return index * 2 + 2;
    }


}
