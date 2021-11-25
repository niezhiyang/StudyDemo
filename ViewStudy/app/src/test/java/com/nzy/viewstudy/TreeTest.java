package com.nzy.viewstudy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author niezhiyang
 * since 2020/10/14
 */
public class TreeTest {
    @Test
    public void reverseTree() {
        /**
         *          10
         *      5        15
         *    4   6    14  16
         */
        TreeNode node = new TreeNode(10);
        TreeNode nodeL = new TreeNode(5);
        TreeNode nodeR = new TreeNode(15);
        node.left = nodeL;
        node.right = nodeR;


        TreeNode nodeLL = new TreeNode(4);
        TreeNode nodeLR = new TreeNode(6);

        nodeL.left = nodeLL;
        nodeL.right = nodeLR;


        TreeNode nodeRL = new TreeNode(14);
        TreeNode nodeRR = new TreeNode(16);

        nodeR.left = nodeRL;
        nodeR.right = nodeRR;

//        nodeLL.right = new TreeNode(2);


//        System.out.println(node1);
//
//        ListNode res = reverse(node1);

        List<String> path1 = new ArrayList<String>();


        binaryTreePaths(node, "", path1);


        System.out.println("打印路径：" + Arrays.toString(path1.toArray()));

        System.out.println("从上到下打印路径：" + getDepth(node));

//        revertTree(node);
//        List<String> path3 = new ArrayList<String>();
//        binaryTreePaths(node, "", path3);
//        System.out.println("反转2：" + Arrays.toString(path3.toArray()));
        System.out.println("深度：" + getTreeDepth(node));

        System.out.println("-------遍历开始-------");
        bianliTreeLeft(node);
        System.out.println("");
        bianliTreeRight(node);
        System.out.println("");
        bianliTreeMid(node);
        System.out.println("");
        System.out.println("-------遍历结束-------");

        List<String> path2 = new ArrayList<String>();
        binaryTreePaths2(node, "", path2);
        System.out.println("打印路径：" + Arrays.toString(path2.toArray()));


        List<List<Integer>> result = new ArrayList<>();
        binaryTreePathsForSum(node, new ArrayList<>(), result);
        System.out.println("打印路径：" + Arrays.toString(result.toArray()));


        System.out.println("是否是对称：" + isDuichengTree(node.left, node.right));

    }


    /**
     * 二叉树的全路径 ，存放在一个集合中["1->2->5", "1->3"]
     *
     * @param root  第一个Note
     * @param path  "1->2"
     * @param paths 全路径的集合
     */
    public void binaryTreePaths(TreeNode root, String path, List<String> paths) {

        if (root != null) {
            path = path + root.val;
            if (root.right == null && root.left == null) {
                // 当right 和 left 都是null的时候，证明已经到底了
                paths.add(path);
            } else {
                // 进入下一节点
                path = path + "->";
                binaryTreePaths(root.left, path, paths);
                binaryTreePaths(root.right, path, paths);
            }

        }


    }

    public void bianliTreeLeft(TreeNode root) {

        if (root != null) {
            System.out.print(root.val + "->");
            bianliTreeLeft(root.left);
            bianliTreeLeft(root.right);
        }
    }

    public void bianliTreeRight(TreeNode root) {

        if (root != null) {

            bianliTreeRight(root.left);
            bianliTreeRight(root.right);
            System.out.print(root.val + "->");


        }
    }

    public void bianliTreeMid(TreeNode root) {

        if (root != null) {

            bianliTreeMid(root.left);
            System.out.print(root.val + "->");
            bianliTreeMid(root.right);
        }
    }

    /**
     * 二叉树的全路径 ，存放在一个集合中["1->2->5", "1->3"]
     *
     * @param root  第一个Note
     * @param path  "1->2"
     * @param paths 全路径的集合
     *              <p>
     *              binaryTreePaths2和binaryTreePathsForSum不同的实现，其实都是一样的
     *              一个是每次都是一个新的集合，另一个是一直用以前的集合，然后删除最后一个
     */
    public void binaryTreePaths2(TreeNode root, String path, List<String> paths) {

        if (root != null) {
            StringBuffer sb = new StringBuffer(path);
            sb.append(root.val);
            if (root.right == null && root.left == null) {
                // 当right 和 left 都是null的时候，证明已经到底了
                paths.add(sb.toString());
            } else {
                // 进入下一节点
//                path = ;
                sb.append(path + "->");
                binaryTreePaths(root.left, sb.toString(), paths);
                binaryTreePaths(root.right, sb.toString(), paths);
            }

        }


    }

    /**
     * 10
     * 5        15
     * 4   6    14  16
     * 二叉树的路径和 ，存放在一个集合中["1->2->5", "1->3->4"]
     *
     * @param root   第一个Note
     * @param path   "1->2"
     * @param result 全路径的集合
     */
    public void binaryTreePathsForSum(TreeNode root, List<Integer> path, List<List<Integer>> result) {

        if (root != null) {
            path.add(root.val);
            if (root.right == null && root.left == null) {
                // 比如 先到  10-5-4， 左边遍历完事了，遍历右边改10-5-6了，
                result.add(new ArrayList(path));// 得用一个 新的list保存，否则都是path了，地址值没变
                path.remove(path.size() - 1);
            } else {
                // 进入下一节点
                binaryTreePathsForSum(root.left, path, result);
                binaryTreePathsForSum(root.right, path, result);
                // // 比如 先到  10-5-4， 左边遍历完事了，遍历右边改10-5-6了，
                path.remove(path.size() - 1);
            }

        }


    }


    public int getTreeDepth(TreeNode root) {
        if (root == null) return 0;
        // 先算出来左节点的深度，+1 相当于本节点也算一个
        return Math.max(getTreeDepth(root.left), getTreeDepth(root.right)) + 1;
    }


    /**
     * 是否是对称树
     *
     * @return
     */
    public boolean isDuichengTree(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null || left.val != right.val) {
            return false;
        } else {
            return isDuichengTree(left.left, left.right) && isDuichengTree(right.left, right.right);
        }

    }


    /**
     * 反转二叉树
     *
     * @param root
     * @return
     */
    public TreeNode revertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tempLeft = root.left;
        TreeNode tempRight = root.right;
        root.right = tempLeft;
        root.left = tempRight;
        revertTree(root.left);
        revertTree(root.right);
        return root;


    }

    /**
     * 1
     * 2          3
     * 4   5    6     7
     * <p>
     * 从上到下打印二叉树
     * <p>
     * 这样打印 1-2-3-4-5-6-7
     * <p>
     * 思路：当打印1的时候，把 2，3 存起来放入队列。当打印2的时候，去除2.然后把 4.5 放进队列 此时 队列里面是 3.4.5
     */

    public String getDepth(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        StringBuffer sb = new StringBuffer();
        list.addLast(root);
        while (!list.isEmpty()) {
            TreeNode treeNode = list.pollFirst();
            sb.append(treeNode.val);
            sb.append("--->");
            if (treeNode.left != null) {
                list.addLast(treeNode.left);
            }

            if (treeNode.right != null) {
                list.addLast(treeNode.right);
            }
        }
        return sb.toString();

    }

    /**
     * 验证二叉搜索树
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }

    @Test
    public void reverseTree1() {
        /**
         *          10
         *      5        15
         *    4   6    14  16
         */
        TreeNode node = new TreeNode(10);
        TreeNode nodeL = new TreeNode(5);
        TreeNode nodeR = new TreeNode(15);
        node.left = nodeL;
        node.right = nodeR;


        TreeNode nodeLL = new TreeNode(4);
        TreeNode nodeLR = new TreeNode(6);

        nodeL.left = nodeLL;
        nodeL.right = nodeLR;


        TreeNode nodeRL = new TreeNode(14);
        TreeNode nodeRR = new TreeNode(16);

        nodeR.left = nodeRL;
        nodeR.right = nodeRR;
        System.out.println(levelOrder(node));
    }

    /**
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        if (root != null) {
            linkedList.add(root);
        }
        while (!linkedList.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            for (int i = linkedList.size() - 1; i >= 0; i--) {
                TreeNode first = linkedList.removeFirst();
                temp.add(first.val);
                if (first.left != null) {
                    linkedList.add(first.left);
                }
                if (first.right != null) {
                    linkedList.add(first.right);
                }

            }
            result.add(temp);
        }

        return result;

    }

    /**
     * Z字形打印
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderZ(TreeNode root) {
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        if (root != null) {
            linkedList.add(root);
        }
        while (!linkedList.isEmpty()) {
            // 偶数 添加到头部 ，奇数 添加到尾部
            LinkedList<Integer> temp = new LinkedList<>();
            for (int i = linkedList.size() - 1; i >= 0; i--) {
                TreeNode first = linkedList.removeFirst();
                if (result.size() % 2 == 0) {
                    temp.addLast(first.val);
                } else {
                    temp.addFirst(first.val);

                }

                if (first.left != null) {
                    linkedList.add(first.left);
                }
                if (first.right != null) {
                    linkedList.add(first.right);
                }

            }
            result.add(temp);
        }

        return result;

    }


    @Test
    public void Tesss() {
        /**
         *          1
         *      1        1
         *    1   2    3  4
         */
        TreeNode node = new TreeNode(1);
        TreeNode nodeL = new TreeNode(1);
        TreeNode nodeR = new TreeNode(1);
        node.left = nodeL;
        node.right = nodeR;


        TreeNode nodeLL = new TreeNode(1);
        TreeNode nodeLR = new TreeNode(2);

        nodeL.left = nodeLL;
        nodeL.right = nodeLR;


        TreeNode nodeRL = new TreeNode(3);
        TreeNode nodeRR = new TreeNode(4);

        nodeR.left = nodeRL;
        nodeR.right = nodeRR;
        isHave = false;
        hasPathSum(node, 5);
        System.out.println(isHave);

    }

    boolean isHave = false;

    public boolean hasPathSum(TreeNode root, int sum) {

        isHave(root, sum);

        return isHave;
    }

    public void isHave(TreeNode root, int sum) {
        if (root != null) {
            sum = sum - root.val;
            if (root.right == null && root.left == null) {
                if (sum == 0) {
                    isHave = true;
                }
            } else {
                isHave(root.left, sum);
                isHave(root.right, sum);

            }

        }
    }


    /**
     * 二叉树的路径和 ，存放在一个集合中["1->2->5", "1->3->4"]
     *
     * @param root   第一个Note
     * @param path   "1->2"
     * @param result 全路径的集合
     */
    public void hasPathSum(TreeNode root, List<Integer> path, List<List<Integer>> result) {


    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index1 = m - 1;
        int index2 = n - 1;
        // 从后往前赋值
        int tail = m + n - 1;
        while (index1 >= 0 || index2 >= 0) {
            // 如果又一个走到头了 就是 -1； 那直接赋值给另一个
            if (index1 == -1) {
                nums1[tail] = nums2[index2];
                index2--;
            } else if (index2 == -1) {
                nums1[tail] = nums1[index1];
                index1--;
            } else if (nums1[index1] > nums2[index2]) {
                nums1[tail] = nums1[index1];
                index1--;

            } else {
                nums1[tail] = nums2[index2];
                index2--;
            }
            tail--;


        }
    }

}