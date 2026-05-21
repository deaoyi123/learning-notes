package code.algo.divide_and_conquer;

import java.util.List;

/**
 * 给定三根柱子，记为 A、B 和 C 。起始状态下，柱子 A 上套着
 * 个圆盘，它们从上到下按照从小到大的顺序排列。我们的任务是要把这
 * 个圆盘移到柱子 C 上，并保持它们的原有顺序不变（如图 12-10 所示）。在移动圆盘的过程中，需要遵守以下规则。
 * <p>
 * 圆盘只能从一根柱子顶部拿出，从另一根柱子顶部放入。
 * 每次只能移动一个圆盘。
 * 小圆盘必须时刻位于大圆盘之上。
 */
public class Hanota {

    void solveHanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        int n = A.size();
        dfs(n, A, B, C);
    }

    void move(List<Integer> src, List<Integer> tar) {
        if (src == null || src.isEmpty()) {
            return;
        }
        Integer remove = src.remove(src.size() - 1);
        tar.add(remove);
    }

    /**
     * <p>1、把src里的最上面n-1个盘子，借助tar移到buf上</p>
     * <p>2、把src里的最下面1个盘子，移到tar上</p>
     * <p>3、把buf里的n-1个盘子，借助src移到tar上</p>
     * <p>4、一直拆解递归，直到拆解成移动1盘子</p>
     */
    void dfs(int n, List<Integer> src, List<Integer> buf, List<Integer> tar) {
        if (n == 0) {
            return;
        }
        if (n == 1) {
            move(src, tar);
            return;
        }
        dfs(n - 1, src, tar, buf);
        move(src, tar);
        dfs(n - 1, buf, src, tar);
    }
}
