package top.gloryjie.learn.leetcode.array.easy;

/**
 * Description ...
 *
 * @author Jie-R 1620
 * @since 1.0
 */
public class SearchInsert {


    public int solutionOne(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        if (nums[right] < target) {
            return nums.length;
        }
        while (left <= right) {
            int middle = (right + left) / 2;
            if (target == nums[middle]) {
                return middle;
            } else if (target > nums[middle]) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return left;
    }


    public int solutionTwo(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        if (nums[right] < target) {
            return nums.length;
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }


    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};

        SearchInsert solution = new SearchInsert();

        int result = solution.solutionTwo(nums, 2);

        System.out.println(result);
    }

}
