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
        binaryTreePathsForSum(node, new ArrayList<>(), result, 21);
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
            path = path + root.value;
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
            System.out.print(root.value + "->");
            bianliTreeLeft(root.left);
            bianliTreeLeft(root.right);
        }
    }

    public void bianliTreeRight(TreeNode root) {

        if (root != null) {

            System.out.print(root.value + "->");
            bianliTreeRight(root.right);
            bianliTreeRight(root.left);


        }
    }

    public void bianliTreeMid(TreeNode root) {

        if (root != null) {

            bianliTreeMid(root.left);
            System.out.print(root.value + "->");
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
            sb.append(root.value);
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
     * 二叉树的路径和 ，存放在一个集合中["1->2->5", "1->3->4"]
     *
     * @param root   第一个Note
     * @param path   "1->2"
     * @param result 全路径的集合
     */
    public void binaryTreePathsForSum(TreeNode root, List<Integer> path, List<List<Integer>> result, int sum) {

        if (root != null) {
            path.add(root.value);
            if (root.right == null && root.left == null) {
                // 当right 和 left 都是null的时候，证明已经到底了
                // 开始算path的集合里面的相加

//                int sumTemp = 0;
//                for (int i = 0; i < path.size(); i++) {
//                    sumTemp += path.get(i);
//                }
//                if (sumTemp == sum) {
                // 比如 先到  10-5-4， 左边遍历完事了，遍历右边改10-5-6了，
                result.add(new ArrayList(path));// 得用一个 新的list保存，否则都是path了，地址值没变
                // 所以path 要移除 4
//                }
                path.remove(path.size() - 1);


//
            } else {
                // 进入下一节点
                binaryTreePathsForSum(root.left, path, result, sum);
                binaryTreePathsForSum(root.right, path, result, sum);
                // 证明左右都遍历完事了，改遍历 上个节点的了
                // 比如 10-5-4  10-5-6 都完事了
                // 改遍历  10-15->的了
                path.remove(path.size() - 1);
            }

        }


    }


    public int getTreeDepth(TreeNode root) {
        if (root == null) return 0;
        // 先算出来左节点的深度，+1 相当于本节点也算一个
        return Math.max(getTreeDepth(root.left), getTreeDepth(root.right)) + 1;

//        if(root == null){
//            return 0;
//        }
//        int left = getTreeDepth(root.left);
//        int right = getTreeDepth(root.right);
//
//        return Math.max(left+1, right+1);
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

        if (left == null || right == null || left.value != right.value) {
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
     *
     *       1
     *   2          3
     * 4   5    6     7
     * <p>
     * 从上到下打印二叉树
     *
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
            sb.append(treeNode.value);
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


}