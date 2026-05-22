package code.algo.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 全排列问题是回溯算法的一个典型应用。它的定义是在给定一个集合（如一个数组或字符串）的情况下，找出其中元素的所有可能的排列。
 */
public class Permutations {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3};
        System.out.println(permutationsI(nums1));

        int[] nums2 = new int[]{1, 2, 1};
        System.out.println(permutationsII(nums2));
    }

    /**
     * 全排列 I：输入一个整数数组，其中不包含重复元素，返回所有可能的排列。
     */
    private static List<List<Integer>> permutationsI(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrackI(new ArrayList<>(), nums, new boolean[nums.length], res);
        return res;
    }

    private static void backtrackI(List<Integer> state, int[] choices, boolean[] selected, List<List<Integer>> res) {
        // 当状态长度等于元素数量时，记录解
        if (state.size() == choices.length) {
            res.add(new ArrayList<>(state));
            return;
        }
        // 遍历所有选择
        for (int i = 0; i < choices.length; i++) {
            // 剪枝：不允许重复选择元素
            if (selected[i]) {
                continue;
            }
            int choice = choices[i];
            // 尝试：做出选择，更新状态
            state.add(choice);
            selected[i] = true;
            // 进行下一轮选择
            backtrackI(state, choices, selected, res);
            // 回退：撤销选择，恢复到之前的状态
            selected[i] = false;
            state.remove(state.size() - 1);
        }
    }

    /**
     * 全排列 II：输入一个整数数组，数组中可能包含重复元素，返回所有不重复的排列。
     */
    private static List<List<Integer>> permutationsII(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrackII(new ArrayList<>(), nums, new boolean[nums.length], res);
        return res;
    }

    private static void backtrackII(List<Integer> state, int[] choices, boolean[] selected, List<List<Integer>> res) {
        if (state.size() == choices.length) {
            res.add(new ArrayList<>(state));
            return;
        }
        Set<Integer> duplicated = new HashSet<>();
        for (int i = 0; i < choices.length; i++) {
            int choice = choices[i];
            if (selected[i]){
                continue;
            }
            if (!duplicated.add(choice)){
                continue;
            }
            // 尝试：做出选择，更新状态
            state.add(choice);
            selected[i] = true;
            // 进行下一轮选择
            backtrackII(state, choices, selected, res);
            // 回退：撤销选择，恢复到之前的状态
            selected[i] = false;
            state.remove(state.size() - 1);
        }
    }
}
