package code.interview;

/**
 * 474. 一和零
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * <p>
 * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
 * <p>
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出：4
 * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
 * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
 * 示例 2：
 * <p>
 * 输入：strs = ["10", "0", "1"], m = 1, n = 1
 * 输出：2
 * 解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i] 仅由 '0' 和 '1' 组成
 * 1 <= m, n <= 100
 */
public class OneAndZero {
    public static void main(String[] args) {
        String[] strs = new String[]{"10", "0001", "111001", "1", "0"};
        int m = 5;
        int n = 3;
        System.out.println(findMaxForm(strs, m, n));
    }

    private static int findMaxForm(String[] strs, int m, int n) {
        // dp[i][j]: 最多使用 i 个 0 和 j 个 1，能得到的最大子集大小
        int[][] dp = new int[m + 1][n + 1];

        for (String str : strs) {
            // 统计当前字符串中 0 和 1 的个数
            int ones = 0;
            int zeros = 0;
            for (int i = 0; i < str.length(); i++) {
                if ('1' == str.charAt(i)) {
                    ones++;
                } else {
                    zeros++;
                }
            }

            // 01背包：每个字符串只能用一次，所以倒序遍历避免重复选择
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    // dp[i - zeros][j - ones] + 1: 使用当前字符串后的子集大小
                    // dp[i][j]: 不使用当前字符串时的子集大小
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }
        return dp[m][n];
    }
}
