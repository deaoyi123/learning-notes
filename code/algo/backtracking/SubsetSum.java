package code.algo.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetSum {
    public static void main(String[] args) {
        //问题1：给定一个正整数数组 nums 和一个目标正整数 target ，请找出所有可能的组合，使得组合中的元素和等于 target 。给定数组无重复元素，每个元素可以被选取多次。请以列表形式返回这些组合，列表中不应包含重复组合。
        int[] nums1 = new int[]{3, 4, 5};
        int target1 = 9;
        System.out.println(subsetSumI(nums1, target1));
        System.out.println(subsetSumII(nums1, target1));

        //问题2：给定一个正整数数组 nums 和一个目标正整数 target ，请找出所有可能的组合，使得组合中的元素和等于 target 。给定数组可能包含重复元素，每个元素只可被选择一次。请以列表形式返回这些组合，列表中不应包含重复组合。
        int[] nums2 = new int[]{4, 4, 5};
        int target2 = 9;
        System.out.println(subsetSumIII(nums2, target2));
    }

    /**
     * 没有处理重复的组合，{4,5}\{5,4}
     */
    private static List<List<Integer>> subsetSumI(int[] choices, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int total = 0;
        backtrackI(new ArrayList<>(), choices, total, target, res);
        return res;
    }

    private static void backtrackI(List<Integer> state, int[] choices, int total, int target, List<List<Integer>> res) {
        if (total == target) {
            res.add(new ArrayList<>(state));
            return;
        }
        for (int i = 0; i < choices.length; i++) {
            // 剪枝：若子集和超过 target ，则跳过该选择
            if (total + choices[i] > target) {
                continue;
            }
            // 尝试：做出选择，更新元素和 total
            state.add(choices[i]);
            // 进行下一轮选择
            backtrackI(state, choices, total + choices[i], target, res);
            // 回退：撤销选择，恢复到之前的状态
            state.remove(state.size() - 1);
        }
    }

    /**
     * 处理重复的组合，{4,5}\{5,4}
     */
    private static List<List<Integer>> subsetSumII(int[] choices, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(choices);   //排序，使得每次选择不会选前面选过的
        backtrackII(new ArrayList<>(), choices, 0, target, res);
        return res;
    }

    private static void backtrackII(List<Integer> state, int[] choices, int start, int target, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(state));
            return;
        }
        // 遍历所有选择
        // 剪枝二：从 start 开始遍历，避免生成重复子集
        for (int i = start; i < choices.length; i++) {
            // 剪枝一：若子集和超过 target ，则直接结束循环
            // 这是因为数组已排序，后边元素更大，子集和一定超过 target
            if (target - choices[i] < 0) {
                break;
            }
            // 尝试：做出选择，更新 target, start
            state.add(choices[i]);
            // 进行下一轮选择
            backtrackII(state, choices, i, target - choices[i], res);
            // 回退：撤销选择，恢复到之前的状态
            state.remove(state.size() - 1);
        }
    }

    private static List<List<Integer>> subsetSumIII(int[] choices, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(choices);   //排序，使得每次选择不会选前面选过的
        backtrackIII(new ArrayList<>(), choices, 0, target, res);
        return res;
    }

    private static void backtrackIII(List<Integer> state, int[] choices, int start, int target, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(state));
            return;
        }
        // 遍历所有选择
        // 剪枝二：从 start 开始遍历，避免生成重复子集
        // 剪枝三：从 start 开始遍历，避免重复选择同一元素
        for (int i = start; i < choices.length; i++) {
            // 剪枝一：若子集和超过 target ，则直接结束循环
            // 这是因为数组已排序，后边元素更大，子集和一定超过 target
            if (target - choices[i] < 0) {
                break;
            }
            // 剪枝四：如果该元素与左边元素相等，说明该搜索分支重复，直接跳过
            if (i > start && choices[i] == choices[i - 1]) {
                continue;
            }
            // 尝试：做出选择，更新 target, start
            state.add(choices[i]);
            // 进行下一轮选择
            backtrackIII(state, choices, i + 1, target - choices[i], res);
            // 回退：撤销选择，恢复到之前的状态
            state.remove(state.size() - 1);
        }
    }

}
