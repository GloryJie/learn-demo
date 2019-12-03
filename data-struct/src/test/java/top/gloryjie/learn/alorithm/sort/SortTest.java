package top.gloryjie.learn.alorithm.sort;

import org.apache.commons.lang3.RandomUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author jie
 * @since 2019/9/1
 */
public class SortTest {


    private Integer[] elements = new Integer[10];

    @Before
    public void setUp() throws Exception {
        IntStream.range(0, 10).forEach(item-> elements[item] = RandomUtils.nextInt(0,1000));
    }

    @After
    public void before(){
        System.out.println(Arrays.toString(elements));
    }

    @Test
    public void bubbleSortTest() {
        Sort<Integer> sortAlgorithm = new BubbleSort<>();
        sortAlgorithm.sort(elements);

    }

    @Test
    public void chooseSortTest(){
        Sort<Integer> sortAlgorithm = new ChooseSort<>();
        sortAlgorithm.sort(elements);
    }

    @Test
    public void insertSortTest(){
        Sort<Integer> sortAlgorithm = new InsertSort<>();
        sortAlgorithm.sort(elements);
    }

    @Test
    public void shellSortTest(){
        Sort<Integer> sortAlgorithm = new ShellSort<>();
        sortAlgorithm.sort(elements);
    }


    @Test
    public void mergeSortTest(){
        Sort<Integer> sortAlgorithm = new MergeSort<>();
        sortAlgorithm.sort(elements);
    }

    @Test
    public void quickSortTest() {
        Sort<Integer> sortAlgorithm = new QuickSort<>();
        elements[5] = elements[8];
        sortAlgorithm.sort(elements);
    }


}