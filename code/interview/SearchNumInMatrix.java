package code.interview;

import java.util.Random;
import java.util.Scanner;

/**
 * @descreption: 在横向有序、纵向有序的二维数组里查找目标数是否存在
 */
public class SearchNumInMatrix {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] sortedMatrix = generateSortedMatrix(n, m);
        printMatrix(sortedMatrix);
        while (in.hasNext()) {
            int target = in.nextInt();
            int x = m - 1;
            int y = 0;
            boolean result = false;
            while (x >= 0 && y <= n - 1) {
                int cur = sortedMatrix[y][x];
                if (cur > target) {
                    x--;
                } else if (cur == target) {
                    result = true;
                    break;
                } else {
                    y++;
                }
            }
            System.out.println(result);
            if (result) {
                System.out.printf("(%d,%d)\n", y+1, x+1);
            }
        }
    }

    private static int[][] generateSortedMatrix(int n, int m) {
        if (n <= 0 || m <= 0) {
            return new int[0][0];
        }

        int total = n * m;
        int[] flat = new int[total];
        Random rand = new Random();

        // 随机起始值，例如 0~100
        int start = rand.nextInt(101);
        flat[0] = start;

        // 生成严格递增的一维数组，每次增加 1~10 的随机正数
        for (int i = 1; i < total; i++) {
            int increment = rand.nextInt(10) + 1; // 1 ~ 10
            flat[i] = flat[i - 1] + increment;
        }

        // 填充二维数组（行优先）
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = flat[i * m + j];
            }
        }
        return matrix;
    }

    // 打印二维数组
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%4d ", val);
            }
            System.out.println();
        }
    }
}
