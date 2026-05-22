package code.algo.dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 爬楼梯问题：给定一个共有n阶的楼梯，你每步可以上1阶或者2阶，请问有多少种方案可以爬到楼顶？
 */
public class ClimbingStairs {
    public static void main(String[] args) {
        int n = 3;
        System.out.println(climbingStairsBacktrack(n));   //回溯
        System.out.println(climbingStairsDFS(n));        //暴力搜索
        System.out.println(climbingStairsDFSMem(n));    //记忆化搜索
        System.out.println(climbingStairsDP(n));        //动态规划
        System.out.println(climbingStairsDPComp(n));        //climbingStairsDPComp
    }

    /* 爬楼梯：回溯 */
    private static int climbingStairsBacktrack(int n) {
        List<Integer> choices = Arrays.asList(1, 2); // 可选择向上爬 1 阶或 2 阶
        int state = 0; // 从第 0 阶开始爬
        List<Integer> res = new ArrayList<>();
        res.add(0); // 使用 res[0] 记录方案数量
        backtrack(choices, state, n, res);
        return res.get(0);
    }

    private static void backtrack(List<Integer> choices, int state, int n, List<Integer> res) {
        // 当爬到第 n 阶时，方案数量加 1
        if (state == n) {
            res.set(0, res.get(0) + 1);
        }
        // 遍历所有选择
        for (int i = 0; i < choices.size(); i++) {
            // 剪枝：不允许越过第 n 阶
            int choice = choices.get(i);
            if (state + choice > n) {
                break;
            }
            // 尝试：做出选择，更新状态
            backtrack(choices, state + choice, n, res);
            // 回退
        }
    }


    /* 搜索 */
    private static int dfs(int i) {
        // 已知 dp[1] 和 dp[2] ，返回之
        if (i == 1 || i == 2)
            return i;
        // dp[i] = dp[i-1] + dp[i-2]
        return dfs(i - 1) + dfs(i - 2);
    }

    /* 爬楼梯：搜索 */
    private static int climbingStairsDFS(int n) {
//        由于每轮只能上 $1$ 阶或 $2$ 阶，因此当我们站在第 $i$ 阶楼梯上时，上一轮只可能站在第 $i - 1$ 阶或第 $i - 2$ 阶上。换句话说，我们只能从第 $i -1$ 阶或第 $i - 2$ 阶迈向第 $i$ 阶。
//
//        由此便可得出一个重要推论：**爬到第 $i - 1$ 阶的方案数加上爬到第 $i - 2$ 阶的方案数就等于爬到第 $i$ 阶的方案数**。公式如下：
//
//        $$
//        dp[i] = dp[i-1] + dp[i-2]
//        $$
        return dfs(n);
    }


    /* 爬楼梯：记忆化搜索 */
    private static int climbingStairsDFSMem(int n) {
        // mem[i] 记录爬到第 i 阶的方案总数，-1 代表无记录
        int[] mem = new int[n + 1];
        Arrays.fill(mem, -1);
        return dfs(n, mem);
    }

    private static int dfs(int n, int[] mem) {
        if (n == 1 || n == 2)
            return n;
        // 若存在记录 dp[i] ，则直接返回之
        if (mem[n] != -1)
            return mem[n];
        // dp[i] = dp[i-1] + dp[i-2]
        int count = dfs(n - 1, mem) + dfs(n - 2, mem);
        // 记录 dp[i]
        mem[n] = count;
        return count;
    }

    /* 爬楼梯：动态规划 */
    private static int climbingStairsDP(int n) {
        if (n == 1 || n == 2)
            return n;
        // 初始化 dp 表，用于存储子问题的解
        int[] dp = new int[n + 1];
        // 初始状态：预设最小子问题的解
        dp[1] = 1;
        dp[2] = 2;
        // 状态转移：从较小子问题逐步求解较大子问题
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }


    /* 爬楼梯：空间优化后的动态规划 */
    private static int climbingStairsDPComp(int n) {
        if (n == 1 || n == 2)
            return n;
        int a = 1, b = 2;
        for (int i = 3; i <= n; i++) {
            int tmp = b;
            b = a + b;
            a = tmp;
        }
        return b;
    }
}
