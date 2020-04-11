package top.gloryjie.learn.leetcode.array.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jie
 * @since 2020/3/22
 */
public class YangHuiTriangle {


    /**
     * 了解规律即可
     * 将几行数据右对齐, 会发现非第一和最后一位元素的规律: 当前元素位置为i, 此元素的值为上一行元素i和i-1的值
     *
     * @return 整个杨辉三角
     */
    public List<List<Integer>> solution(int numRows) {
        List<List<Integer>> result = new ArrayList<>();

        if (numRows <= 0) {
            return result;
        }
        // 第一行只用1
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        result.add(firstRow);

        for (int rowNum = 1; rowNum < numRows; rowNum++) {
            // rowNum为当前行号
            // 当前行的结果集
            List<Integer> currRow = new ArrayList<>();
            // 获取上一行的结果集
            List<Integer> prevRow = result.get(rowNum - 1);

            // 第一个元素是1
            currRow.add(1);

            // 当前行的中间元素为
            for (int j = 1; j < rowNum; j++) {
                currRow.add(prevRow.get(j - 1) + prevRow.get(j));
            }

            // 最后一个元素也是1
            currRow.add(1);

            result.add(currRow);
        }
        return result;
    }

    /**
     * 返回指定杨辉三角中指定行数的结果集, 其中rowIndex <= 33
     * 从左往右,利用上一行来计算当前行.
     * 计算当前位置j时,需要上一行的j和j-1的数据, 而j-1上的数据已经被覆盖, 所以需要一个变量暂存上一行的j-1数据
     * 在计算时, 上一行和当前行的数据并存
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {

        if (rowIndex <= 0) {
            return new ArrayList<>();
        }
        //
        List<Integer> currRow = new ArrayList<>();
        currRow.add(1);
        int pre = 1;

        for (int i = 1; i <= rowIndex; i++) {
            for (int j = 1; j < i; j++) {
                // 上一行j位置所在的值
                int temp = currRow.get(j);
                // 此时pre为上一行 j-1位置的值, 当前行j位置所在的值为 上一行j-1的值 + 上一行j的值
                // 此处用set,为修改值
                currRow.set(j, pre + temp);
                // 更新pre,下次遍历使用
                pre = temp;
            }
            // 补充添加最后一个元素, 此时才是完整的当前行数据集
            currRow.add(1);
        }

        return currRow;
    }


    /**
     * 返回指定杨辉三角中指定行数的结果集, 其中rowIndex <= 33
     * 从右往左, 利用上一行来计算当前行. 此时结果记录中仍存在j和j-1的数据,不会造成覆盖
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow2(int rowIndex) {

        List<Integer> currRow = new ArrayList<>();
        currRow.add(1);

        // i为行数, 且为当前行元素的数量
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i - 1; j > 0; j--) {
                currRow.add(j, currRow.get(j - 1) + currRow.get(j));
            }
            // 补充添加最后一个元素
            currRow.add(1);
        }

        return currRow;
    }

    public static void main(String[] args) {
        YangHuiTriangle triangle = new YangHuiTriangle();
        List<Integer> row = triangle.getRow(3);

        System.out.println(row);
    }
}
