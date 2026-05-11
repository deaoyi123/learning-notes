package code.algo.sort;

import java.util.Arrays;
import java.util.Scanner;

public class HeapSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            heapSort(arr);
            System.out.println(Arrays.toString(arr));
        }
    }

    private static void heapSort(int[] arr) {

    }
}
