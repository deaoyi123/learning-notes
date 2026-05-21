package code.algo.divide_and_conquer;

import java.util.*;

/**
 * 给定一棵二叉树的前序遍历 preorder 和中序遍历 inorder ，请从中构建二叉树，返回二叉树的根节点。假设二叉树中没有值重复的节点
 * <p>前序遍历：[根节点 | 左子树 | 右子树]</p>
 * <p>中序遍历：[左子树 | 根节点 | 右子树]</p>
 * <p>后序遍历：[左子树 | 右子树 | 根节点]</p>
 */
public class BuildBinaryTree {
    public static void main(String[] args) {
        int[] preorder = new int[]{3, 9, 2, 1, 7};
        int[] inorder = new int[]{9, 3, 1, 2, 7};
        Map<Integer, Integer> valueIndexMap = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            valueIndexMap.put(inorder[i], i);
        }
        TreeNode root = dfs(preorder, valueIndexMap, 0, 0, inorder.length - 1);
        printTree(root);
        if (root != null) {
            System.out.println(root.levelOrder());
            System.out.println(root.preOrder());
            System.out.println(root.inOrder());
            System.out.println(root.postOrder());
        }

    }

    private static TreeNode dfs(int[] preorder, Map<Integer, Integer> inorderValueIndexMap, int rootIndex, int l, int r) {
        if (l > r) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[rootIndex]);
        Integer inorderRootIndex = inorderValueIndexMap.get(root.data);
        // 子问题：构建左子树
        root.left = dfs(preorder, inorderValueIndexMap, rootIndex + 1, l, inorderRootIndex - 1);
        // 子问题：构建右子树
        root.right = dfs(preorder, inorderValueIndexMap, rootIndex + 1 + inorderRootIndex - l, inorderRootIndex + 1, r);
        // 返回根节点
        return root;
    }

    private static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.data = data;
        }

        List<Integer> levelOrder() {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(this);
            List<Integer> r = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode treeNode = queue.poll();
                r.add(treeNode.data);
                if (treeNode.left != null) {
                    queue.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.add(treeNode.right);
                }
            }
            return r;
        }

        List<Integer> preOrder() {
            List<Integer> r = new ArrayList<>();
            preDfs(this, r);
            return r;
        }

        private void preDfs(TreeNode root, List<Integer> list) {
            if (root == null) {
                return;
            }
            list.add(root.data);
            preDfs(root.left, list);
            preDfs(root.right, list);
        }

        List<Integer> inOrder() {
            List<Integer> r = new ArrayList<>();
            inDfs(this, r);
            return r;
        }

        private void inDfs(TreeNode root, List<Integer> list) {
            if (root == null) {
                return;
            }
            inDfs(root.left, list);
            list.add(root.data);
            inDfs(root.right, list);
        }

        List<Integer> postOrder() {
            List<Integer> r = new ArrayList<>();
            postDfs(this, r);
            return r;
        }

        private void postDfs(TreeNode root, List<Integer> list) {
            if (root == null) {
                return;
            }
            postDfs(root.left, list);
            postDfs(root.right, list);
            list.add(root.data);
        }
    }

    private static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("空树");
            return;
        }
        int depth = getDepth(root);
        int width = 4 * (int) Math.pow(2, depth - 1);
        String[][] canvas = new String[2 * depth][width];
        for (String[] row : canvas) Arrays.fill(row, " ");

        drawNode(canvas, root, 0, 0, width);

        StringBuilder sb = new StringBuilder();
        for (String[] row : canvas) {
            for (String c : row) sb.append(c);
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    private static void drawNode(String[][] canvas, TreeNode node, int level, int left, int right) {
        if (node == null) return;
        int col = (left + right) / 2;
        canvas[level][col] = String.valueOf(node.data);

        if (level + 2 < canvas.length) {
            if (node.left != null) {
                canvas[level + 1][col - 1] = "/";
                drawNode(canvas, node.left, level + 2, left, col);
            }
            if (node.right != null) {
                canvas[level + 1][col + 1] = "\\";
                drawNode(canvas, node.right, level + 2, col, right);
            }
        }
    }

    private static int getDepth(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(getDepth(node.left), getDepth(node.right));
    }
}
