package code.algo.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据国际象棋的规则，皇后可以攻击与同处一行、一列或一条斜线上的棋子。给定n个皇后和一个n*n大小的棋盘，寻找使得所有皇后之间无法相互攻击的摆放方案。
 */
public class NQueens {
    public static void main(String[] args) {
        int n = 4;
        List<List<List<String>>> lists = nQueens(n);
        for (int i = 0; i < lists.size(); i++) {
            System.out.println("方案" + (i + 1) + ":");
            List<List<String>> scheme = lists.get(i);
            for (List<String> list : scheme) {
                for (String s : list) {
                    System.out.print(" " + s);
                }
                System.out.print("\n");
            }
        }
    }

    /* 求解 n 皇后 */
    private static List<List<List<String>>> nQueens(int n) {
        // 初始化 n*n 大小的棋盘，其中 'Q' 代表皇后，'#' 代表空位
        List<List<String>> state = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add("#");
            }
            state.add(row);
        }
        boolean[] cols = new boolean[n]; // 记录列是否有皇后
        boolean[] diags1 = new boolean[2 * n - 1]; // 记录主对角线上是否有皇后
        boolean[] diags2 = new boolean[2 * n - 1]; // 记录次对角线上是否有皇后
        List<List<List<String>>> res = new ArrayList<>();

        backtrack(0, n, state, res, cols, diags1, diags2);

        return res;
    }

    /**
     * 请注意，$n$ 维方阵中 $row - col$ 的范围是 $[-n + 1, n - 1]$ ，$row + col$ 的范围是 $[0, 2n - 2]$ ，所以主对角线和次对角线的数量都为 $2n - 1$ ，即数组 `diags1` 和 `diags2` 的长度都为 $2n - 1$ 。
     */
    private static void backtrack(int row, int n, List<List<String>> state, List<List<List<String>>> res, boolean[] cols, boolean[] diags1, boolean[] diags2) {
        // 当放置完所有行时，记录解
        if (row == n) {
            List<List<String>> copyState = new ArrayList<>();
            for (List<String> sRow : state) {
                copyState.add(new ArrayList<>(sRow));
            }
            res.add(copyState);
            return;
        }

        // 遍历所有列
        for (int col = 0; col < n; col++) {
            // 剪枝：不允许该格子所在列上存在皇后
            if (cols[col]) {
                continue;
            }
            // 计算该格子对应的主对角线和次对角线
            // 剪枝：不允许该格子所在主对角线上存在皇后
            int diag1 = row - col + n - 1;  //row - col 的值可能是负数，但数组下标不能为负。所以要偏移n-1
            if (diags1[diag1]) {
                continue;
            }
            // 剪枝：不允许该格子所在次对角线上存在皇后
            int diag2 = row + col;
            if (diags2[diag2]) {
                continue;
            }
            // 尝试：将皇后放置在该格子
            cols[col] = diags1[diag1] = diags2[diag2] = true;
            state.get(row).set(col, "Q");
            // 放置下一行
            backtrack(row + 1, n, state, res, cols, diags1, diags2);
            // 回退：将该格子恢复为空位
            cols[col] = diags1[diag1] = diags2[diag2] = false;
            state.get(row).set(col, "#");
        }
    }
}
