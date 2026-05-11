package code.algo.sort;

import java.util.Arrays;
import java.util.Scanner;

public class InsertionSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            insertionSort(arr);
            System.out.println(Arrays.toString(arr));
        }
    }

    private static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int base = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > base) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = base;
        }
    }

}
