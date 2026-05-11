package code.algo.sort;

import java.util.Arrays;
import java.util.Scanner;

public class SelectionSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            selectionSort(arr);
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 先遍历整个数组找到最小的元素放在索引0；再遍历剩下找到次小的元素放在索引1，以此类推。。。
     */
    private static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int k = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[k]) {
                    k = j;
                }
            }
            if (k != i) {
                int temp = arr[k];
                arr[k] = arr[i];
                arr[i] = temp;
            }
        }
    }
}