package code.algo.sort;

import java.util.Arrays;
import java.util.Scanner;

public class QuickSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            quickSort(arr, 0, arr.length - 1);
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 选一个基准数，把基准数移到数组某一位置，保证左边都比基准数小，右边都比基准数大，再分别操作左边数组和右边数组，以此递归
     */
    private static void quickSort(int[] arr, int left, int right) {
        // 子数组长度为 1 时终止递归
        if (left >= right)
            return;
        // 哨兵划分
        int pivot = partition(arr, left, right);
        // 递归左子数组、右子数组
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }

    /* 元素交换 */
    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /* 选取三个候选元素的中位数 */
    private static int medianThree(int[] nums, int left, int mid, int right) {
        int l = nums[left], m = nums[mid], r = nums[right];
        if ((l <= m && m <= r) || (r <= m && m <= l))
            return mid; // m 在 l 和 r 之间
        if ((m <= l && l <= r) || (r <= l && l <= m))
            return left; // l 在 m 和 r 之间
        return right;
    }

    /* 哨兵划分（三数取中值） */
    private static int partition(int[] nums, int left, int right) {
        // 选取三个候选元素的中位数
        int med = medianThree(nums, left, (left + right) / 2, right);
        // 将中位数交换至数组最左端
        swap(nums, left, med);
        // 以 nums[left] 为基准数
        int i = left, j = right;
        while (i < j) {
            while (i < j && nums[j] >= nums[left])
                j--;          // 从右向左找首个小于基准数的元素
            while (i < j && nums[i] <= nums[left])
                i++;          // 从左向右找首个大于基准数的元素
            swap(nums, i, j); // 交换这两个元素
        }
        swap(nums, i, left);  // 将基准数交换至两子数组的分界线
        return i;             // 返回基准数的索引
    }

}
