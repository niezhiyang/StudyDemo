package com.nzy.suanfa;


import com.nzy.suanfa.model.ListNode;
import com.nzy.suanfa.model.TreeNode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * @author niezhiyang
 * since 12/3/21
 */
public class LeetCodeNote {

//给定一个数组 nums,编写一个函数将所有 0 移动到数组的末尾,同时保持非零元素的相对顺序。
//
// 示例:
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0]
//
// 说明:
//
//
// 必须在原数组上操作,不能拷贝额外的数组。
// 尽量减少操作次数。
//
// Related Topics 数组 双指针
// 👍 1326 👎 0


    ///------------------------ 1 ---------------------
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * https://leetcode.cn/problems/move-zeroes/
     * 给定一个数组 nums,编写一个函数将所有 0 移动到数组的末尾,同时保持非零元素的相对顺序。
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        //  奇数偶数也一样可以这样用
        // 方案一
        int left = 0; // 移动完成的末尾
        int right = 0; // 还没移动的开始
        while (right < nums.length) {
            if (nums[right] != 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;

            }
            right++;
        }

        // 方案二
        // 遍历,遇到不等0,就和 noZeroNum 交换,noZeroNum是已经排好序的末尾
        // 遍历完成之后 剩下的补0
        int noZeroNum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[noZeroNum] = nums[i];
                nums[i] = temp;
                noZeroNum++;
            }
        }
        for (int i = noZeroNum; i < nums.length; i++) {
            nums[i] = 0;
        }

    }

    /**
     * 一个数组 把奇数移动到偶数的前面,前面都是奇数,后面都是偶数,并且顺讯位置不变
     * 牛客网
     * https://www.nowcoder.com/questionTerminal/beb5aa231adc45b2a5dcc5b62c93f593?u_atoken=50210515-9ecb-4717-bb2f-5105515b6fd4&u_asession=01lBEXJ5iJK-xqW6oYZGYBehv9UE9_diPO1VLmeJeQOZZAATrvape1CnvPG9A52NwvX0KNBwm7Lovlpxjd_P_q4JsKWYrT3W_NKPr8w6oU7K-Li7-QNHLulm_9hi6RWM_7MJtBx3S14qt35vRMTfjp8mBkFo3NEHBv0PZUm6pbxQU&u_asig=05TrrStJKDQyRL6Pe0d0BBudB2njKw7Ild18MC7W8uajnCmX7k-lOaZub8O9ZeALTgWuIgbONlBHRXu1-B12kifP4LHPjX5907K-txzQcH3UmpMabM_J-UKBMf6nWHl-5wjPPn-XeGXDreuo9MsIM9WLZnSPGDnr5S6-RvOtHKEgP9JS7q8ZD7Xtz2Ly-b0kmuyAKRFSVJkkdwVUnyHAIJzToa8Vt0r92plzbIg9M640pZXxwQHSsLQNDfkbILggi41YpiKJZCArXmgv6vSk2RSO3h9VXwMyh6PgyDIVSG1W84e5kNRA7V7_g0dyd2LP3xxPV46rxeunwwnCjSohRo2NjjroiW10CAo3omzWtvqz3EekJQaPUEiizeJrvIM-UVmWspDxyAEEo4kbsryBKb9Q&u_aref=6mGzVj%2F4f%2BRG0%2BwoVXwsnwXRSu4%3D
     */
    @Test
    public void temp() {
        int[] arr = new int[]{1, 2, 4, 3, 6, 5};
        moveJiOuShu(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void moveJiOuShu(int[] nums) {
        int left = 0; // 移动完成的末尾
        int right = 0; // 还没移动的开始
        while (right < nums.length) {
            if (nums[right] % 2 != 0) {
                // 这里就不能 简单的交换了,需要先记录下来这个奇数
                // 然后把前面的往后移一位,然后给
                int temp = nums[right];
                for (int i = right; i > left; i--) {
                    nums[i] = nums[i - 1];
                }
                nums[left] = temp;
                left++;

            }
            right++;
        }
    }


    ///------------------------ 2 ---------------------

    /**
     * 盛最多水的容器
     * 11. 盛最多水的容器
     * https://leetcode.cn/problems/container-with-most-water/
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int max = (right - left) * Math.min(height[right], height[left]);
        while (right > left) {
            if (height[left] >= height[right]) {
                right--;
            } else {
                left++;
            }
            max = Math.max(max, (right - left) * Math.min(height[right], height[left]));
        }

        return max;
    }

    ///------------------------ 3 ---------------------

    /**
     * 爬楼梯
     * <p>
     * https://leetcode.cn/problems/climbing-stairs/
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int pre = 1;
        int now = 2;
        for (int i = 3; i <= n; i++) {
            int sum = pre + now;
            pre = now;
            now = sum;

        }
        return now;
    }

    /**
     * 删除排序链表中的重复元素 83
     * https://leetcode.cn/problems/remove-duplicates-from-sorted-list/
     */

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }

        return head;
    }


    ///------------------------ 4 ---------------------

    /**
     * 反转链表
     * https://leetcode.cn/problems/reverse-linked-list/
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode now = head;
        ListNode pre = null;
        while (now != null) {

            // 把next 指向前一个
            ListNode temp = now.next;
            now.next = pre;

            //交换
            pre = now;
            now = temp;

        }
        return pre;
    }

    ///------------------------ 5 ---------------------

    /**
     * 链表是否有环
     * https://leetcode.cn/problems/linked-list-cycle/
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void textList() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        ListNode node4 = new ListNode(4);
        node3.next = node4;
        node4.next = node2;
        detectCycle1(node1);


    }


    /**
     * https://leetcode.cn/problems/c32eOV/submissions/
     * 链表是否有环，有的话，返回链表开始入环的第一个节点，没有返回null
     *
     * @param head
     * @return
     */
    public ListNode detectCycle1(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                hasCycle = true;
                break;
            }
        }
        if (!hasCycle) {
            return null;
        }
        ListNode temp = head;
        while (slow != temp) {
            slow = slow.next;
            temp = temp.next;
        }
        return temp;


    }
    ///------------------------ 6 ---------------------

    /**
     * 两个链表的交点,
     * https://leetcode.cn/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lengthA = getLength(headA);
        int lengthB = getLength(headB);
        if (lengthA > lengthB) {
            int value = lengthA - lengthB;
            for (int i = 0; i < value; i++) {
                headA = headA.next;
            }
        } else {
            int value = lengthB - lengthA;
            for (int i = 0; i < value; i++) {
                headB = headB.next;
            }
        }

        while (headA != null) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    private int getLength(ListNode headA) {
        ListNode temp = headA;
        int length = 0;
        while (temp != null) {
            temp = temp.next;
            length++;
        }
        return length;
    }

///------------------------ 7 ---------------------

    /**
     * 链表倒数第K个节点
     * https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;

    }
    ///------------------------ 8 ---------------------

    /**
     * 合并两个有序链表
     * https://leetcode.cn/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/
     *
     * @param node1
     * @param node2
     * @return
     */


    public ListNode mergeTwoLists(ListNode node1, ListNode node2) {
        // 先创建一个新的节点
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (node1 != null && node2 != null) {
            if (node1.val <= node2.val) {
                prev.next = node1;
                node1 = node1.next;
            } else {
                prev.next = node2;
                node2 = node2.next;
            }
            prev = prev.next;
        }

        // 合并后 node1 和 node2 最多只有一个还未被合并完,我们直接将链表末尾指向未合并完的链表即可
        if (node1 == null) {
            prev.next = node2;
        } else {
            prev.next = node1;
        }

        return prehead.next;
    }


    ///------------------------ 9 ---------------------

    /**
     * 链表两两交换
     * https://leetcode.cn/problems/swap-nodes-in-pairs/
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //1->2->3->4
        // temp : 2->3->4
        ListNode temp = head.next;
        // head: 1->4->3
        head.next = swapPairs(temp.next);

        // temp: 2->1->4->3
        temp.next = head;

        return temp;


    }

    /**
     * 25. K 个一组翻转链表
     * https://leetcode.cn/problems/reverse-nodes-in-k-group/
     *
     * @return
     */
    @Test
    public void testKList() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        ListNode node4 = new ListNode(4);
        node3.next = node4;
        ListNode node5 = new ListNode(5);
        node4.next = node5;
        ListNode node6 = new ListNode(6);
        node5.next = node6;
        ListNode node7 = new ListNode(7);
        node6.next = node7;
        reverseKGroup(node1, 3);
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        //递归终止条件,当head为null时中止递归
        if (head == null) {
            return null;
        }
        //根据k寻找待翻转链表的尾
        ListNode end = head;
        for (int i = 0; i < k - 1; i++) {
            end = end.next;
            if (end == null) {
                return head;
            }
        }
        //保存好下次翻转的链表的头 next = 4-5-6-7
        ListNode nextListNode = end.next;

        //翻转[start , end]范围中的链表,并返回头节点  newHead = 3-2-1（1就是head）
        ListNode newHead = reverseListNode(head, end);

        //此时head已经变成了链表的尾节点 head 就是 1,
        //本次翻转后的链表的尾节点连接上下一个待翻转链表的头节点,递归实现
        // 因为上面1就是head ,所以 直接操作head 就行了
        head.next = reverseKGroup(nextListNode, k);
        // 1-6-5-4-7
        // 此时 newHead 是 3-2-1 1下面是谁呢,就是head.next

        return newHead;
    }

    private ListNode reverseListNode(ListNode start, ListNode end) {
        ListNode tmp = null;
        ListNode temp = start;
        while (tmp != end) {
            ListNode next = temp.next;
            temp.next = tmp;
            tmp = temp;
            temp = next;
        }
        return tmp;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    public String printListNode(ListNode node) {
        StringBuffer sb = new StringBuffer();
        while (node != null) {
            sb.append(node.val);
            sb.append(" -> ");
            node = node.next;

        }
        return sb.toString();
    }

    ///------------------------ 10 ---------------------
    // 删除节点
    // https://leetcode.cn/problems/delete-middle-node-lcci/
    public void deleteNode(ListNode node) {
        //既然不能先删除自己,那就把自己整容成儿子,再假装儿子养活孙子
        node.val = node.next.val;
        node.next = node.next.next;
    }

    // 给个头结点,然后删除某个节点val 是 val的
    // https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof/
    // 题目保证链表中节点的值互不相同
    public ListNode deleteNode(ListNode head, int val) {
        if (head.val == val) {
            // 如果是头结点,直接返回下一个就行
            return head.next;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val == val) {
                // 找到了 直接break,此时 cur就是当前需要删除的,pre,就是需要删除的前一个
                break;
            } else {
                pre = pre.next;
                cur = cur.next;
            }

        }
        // 把 pre 执行 需要删除的下一个即可
        if (cur != null) {
            pre.next = cur.next;
        }
        // 返回头结点
        return head;
    }

    /**
     * 给你一个链表,删除链表的倒数第 n 个结点,并且返回链表的头结点。
     * https://leetcode.cn/problems/SLwz0R/
     */

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode curr = head;
        ListNode pre = null;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        if (fast == null) {
            // 如果是头结点,直接返回下一个就行
            return head.next;
        }
        while (fast != null) {
            // 记录倒数第n个的前一个节点
            if (fast.next == null) {
                pre = curr;
            }
            curr = curr.next;
            fast = fast.next;
        }

        pre.next = curr.next;

        return head;

    }

    @Test
    public void testKList1() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        ListNode node4 = new ListNode(4);
        node3.next = node4;
        ListNode node5 = new ListNode(5);
        node4.next = node5;
        ListNode node6 = new ListNode(6);
        node5.next = node6;
        ListNode node7 = new ListNode(7);
        node6.next = node7;
        System.out.println(printListNode(node1));
        System.out.println(printListNode(removeNthFromEnd(node1, 3)));

    }


    ///------------------------ 11 ---------------------

    // 前序 根左右
    // 中 左根右
    // 后 左右根

    /**
     * 前序遍历 中序遍历 后续遍历
     * 二叉树遍历方式
     * -----1
     * --2      3
     * 4   5  6   7
     */
    @Test
    public void testOrderTree() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;


        ArrayList<Integer> pre = new ArrayList<>();
        preorder(node1, pre);
        System.out.println("前序：" + Arrays.toString(pre.toArray()));
        ArrayList<Integer> middle = new ArrayList<>();
        middleorder(node1, middle);
        System.out.println("中序：" + Arrays.toString(middle.toArray()));
        ArrayList<Integer> after = new ArrayList<>();
        afterorder(node1, after);
        System.out.println("后序：" + Arrays.toString(after.toArray()));
    }

    /**
     * 二叉树前序遍历
     * <p>
     * https://leetcode.cn/problems/binary-tree-preorder-traversal/
     *
     * @param root
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        middleorder(root, res);
        return res;

    }

    public void middleorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }

        middleorder(root.left, res);
        res.add(root.val);
        middleorder(root.right, res);
    }

    public void preorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
    }

    public void afterorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }

        afterorder(root.left, res);
        afterorder(root.right, res);
        res.add(root.val);
    }

    ///------------------------ 12 ---------------------

    /**
     * 层遍历二叉树,每一层放到数组中
     * https://leetcode.cn/problems/binary-tree-level-order-traversal/
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
            int size = linkedList.size();
            for (int i = 0; i < size; i++) {
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

    ///------------------------ 12 ---------------------

    /**
     * 层遍历二叉树,Z字形,每一层放到数组中
     * https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/
     * 二叉树的锯齿形层序遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {

        LinkedList<TreeNode> linkedList = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        if (root != null) {
            linkedList.add(root);
        }
        while (!linkedList.isEmpty()) {
            // 偶数 添加到头部 ,奇数 添加到尾部
            LinkedList<Integer> temp = new LinkedList<>();
            int size = linkedList.size();
            for (int i = 0; i < size; i++) {
                TreeNode first = linkedList.removeFirst();
                if (result.size() % 2 == 0) {
                    // 偶数 添加到头部 ,奇数 添加到尾部
                    temp.addLast(first.val);
                } else {
                    // 偶数 添加到头部 ,奇数 添加到尾部
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


    ///------------------------ 13 ---------------------

    /**
     * 层遍历二叉树,放到一个数组中
     *
     * @param root
     * @return
     */
    public int[] levelOrder1(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.removeFirst();
            list.add(temp.val);
            if (temp.left != null) {
                queue.addLast(temp.left);
            }
            if (temp.right != null) {
                queue.addLast(temp.right);

            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    ///------------------------ 14 ---------------------

    /**
     * 257 二叉树的所有路径  ["1->2->5","1->3"]
     * https://leetcode.cn/problems/binary-tree-paths/
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<String> result = new ArrayList<>();
        dfs(root, "", result);
        return result;
    }

    private void dfs(TreeNode root, String path, ArrayList<String> result) {
        if (root != null) {
            StringBuilder pathTemp = new StringBuilder(path);
            // String pathTemp = path + root.val;
            pathTemp.append(root.val);
            if (root.left == null && root.right == null) {
                result.add(pathTemp.toString());
            } else {
                // pathTemp = pathTemp + "->";
                pathTemp.append("->");
                dfs(root.right, pathTemp.toString(), result);
                dfs(root.left, pathTemp.toString(), result);
            }


        }
    }

    ///------------------------ 15 ---------------------

    /**
     * https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
     * 二叉树中和为某一值的路径
     * <p>
     * 先把所有的路径放到一个集合中,然后遍历集合
     * 1
     * 2      3
     * 4   5  6  3
     *
     * @return
     */
    @Test
    public void testTargeet() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        pathTarget(node1, 7);
    }

    public List<List<Integer>> pathTarget(TreeNode root, int target) {
        List<List<Integer>> reslut = new ArrayList<>();
        dfsPathTarget(root, target, new LinkedList<Integer>(), reslut);
        return reslut;
    }

    private void dfsPathTarget(TreeNode root, int target, LinkedList<Integer> integers, List<List<Integer>> reslut) {
        if (root != null) {
            integers.add(root.val);
            target = target - root.val;
            if (root.left == null && root.right == null && target == 0) {
                reslut.add(new ArrayList<>(integers));
            }
            dfsPathTarget(root.left, target, integers, reslut);
            dfsPathTarget(root.right, target, integers, reslut);
            // 最后移除
            integers.removeLast();
        }
    }


    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        getPath(root, result, new ArrayList<Integer>());

        List<List<Integer>> now = new ArrayList<>();

        for (int i = 0; i < result.size(); i++) {
            List<Integer> list = result.get(i);
            int temp = 0;
            for (int j = 0; j < list.size(); j++) {
                temp += list.get(j);
                if (target == temp && j == list.size() - 1) {
                    now.add(list);
                }
            }
        }

        return now;
    }

    private void getPath(TreeNode root, List<List<Integer>> result, ArrayList<Integer> path) {
        if (root != null) {
            ArrayList<Integer> temp = new ArrayList<>(path);
            temp.add(root.val);

            if (root.left == null && root.right == null) {
                result.add(temp);
            } else {
                getPath(root.left, result, temp);
                getPath(root.right, result, temp);
            }
        }
    }


    /**
     * https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
     * 二叉树中和为某一值的路径*
     */

    public List<List<Integer>> pathSum1(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        getPath1(result, target, new ArrayList<Integer>(), root);
        return result;
    }

    public void getPath1(List<List<Integer>> result, int target, ArrayList<Integer> path, TreeNode root) {
        if (root != null) {
            ArrayList<Integer> temp = new ArrayList<>(path);
            temp.add(root.val);
            target = target - root.val;
            System.out.println(Arrays.toString(temp.toArray()) + "---" + target);
            if (root.left == null && root.right == null) {
                if (target == 0) {
                    result.add(temp);
                }
            } else {
                getPath1(result, target, temp, root.left);
                getPath1(result, target, temp, root.right);

            }
        }
    }
    /**
     * https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
     * 二叉树中和为某一值的路径*
     */


    ///------------------------ 16 ---------------------

    /**
     * 是否是二叉搜索树
     * 给你一个二叉树的根节点 root ,判断其是否是一个有效的二叉搜索树。
     * <p>
     * 有效 二叉搜索树定义如下：
     * <p>
     * 节点的左子树只包含 小于 当前节点的数。
     * 节点的右子树只包含 大于 当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/validate-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权,非商业转载请注明出处。
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

    /**
     * https://leetcode.cn/problems/ping-heng-er-cha-shu-lcof/description/
     * isBalanced
     * 输入一棵二叉树的根节点,判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1,那么它就是一棵平衡二叉树
     */

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int left = getDeep(root.left);
        int right = getDeep(root.right);
        return Math.abs(right - left) <= 1 && isBalanced(root.left) && isBalanced(root.right);

    }

    public int getDeep(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getDeep(node.left), getDeep(node.right)) + 1;
    }
    ///------------------------ 17 ---------------------

    /**
     * 树的深度
     *
     * @param root
     * @return
     */
    public int getTreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getTreeDepth(root.left), getTreeDepth(root.right)) + 1;

    }

    /**
     * https://leetcode.cn/problems/diameter-of-binary-tree/
     * 543. 二叉树的直径
     */

    int maxd = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        depth1(root);
        return maxd;
    }

    public int depth1(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int Left = depth1(node.left);
        int Right = depth1(node.right);
        maxd = Math.max(Left + Right, maxd);//将每个节点最大直径(左子树深度+右子树深度)当前最大值比较并取大者
        return Math.max(Left, Right) + 1;//返回节点深度
    }

    ///------------------------ 18 ---------------------
    private boolean isHave = false;

    /**
     * 112. 路径总和
     * https://leetcode.cn/problems/path-sum/description/
     * 是否含有一个路径 的sum 是某个值
     *
     * @param root
     * @param sum
     * @return
     */
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

    ///------------------------ 19 ---------------------

    /**
     * LCR 144. 翻转二叉树
     * https://leetcode.cn/problems/er-cha-shu-de-jing-xiang-lcof/
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root != null) {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            invertTree(root.left);
            invertTree(root.right);
        }
        return root;
    }

    ///------------------------ 20 ---------------------

    /**
     * 是否是对称二叉树
     * 给定一个二叉树,检查它是否是镜像对称的。
     * https://leetcode.cn/problems/dui-cheng-de-er-cha-shu-lcof/
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return check(root.left, root.right);
    }

    private boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        return left.val == right.val && check(left.left, right.right) && check(left.right, right.left);
    }

    ///------------------------ 21 ---------------------

    /**
     * 两数之和
     * https://leetcode.cn/problems/two-sum/
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int result = target - nums[i];
            if (map.containsKey(result)) {
                return new int[]{i, map.get(result)};
            }
            map.put(nums[i], i);
        }
        return new int[]{};

    }

    ///------------------------ 22 ---------------------

    /**
     * 删除数组重复元素
     * 给你一个有序数组 nums ,请你 原地 删除重复出现的元素,使每个元素 只出现一次 ,返回删除后数组的新长度。
     * <p>
     * 不要使用额外的数组空间,你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/remove-duplicates-from-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权,非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int slow = 0, fast = 1;
        while (fast < nums.length) {
            // 因为是有序数组,所以 slow 代表可以代表不重复的个数

            if (nums[slow] != nums[fast]) {
                // 如果不相等 就让慢的走,代表 不重复的个数
                slow++;
                // 赋值给慢的
                nums[slow] = nums[fast];

            }
            // 无论如何，也要走快的
            fast++;
        }

        return slow + 1;
    }

    ///------------------------ 23 ---------------------

    /**
     * https://leetcode.cn/problems/jump-game/
     * 给定一个非负整数数组 nums ,你最初位于数组的 第一个下标 。
     * <p>
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * <p>
     * 判断你是否能够到达最后一个下标。
     * <p>
     * 输入：nums = [2,3,1,1,4]
     * 输出：true
     * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int k = 0;
        //前K个元素能够跳到的最远距离
        for (int i = 0; i <= k; i++) {
            //第i个元素能够跳到的最远距离
            int temp = i + nums[i];
            //更新最远距离
            k = Math.max(k, temp);
            //如果最远距离已经大于或等于最后一个元素的下标,则说明能跳过去,退出. 减少循环
            if (k >= nums.length - 1) {
                return true;
            }
        }
        //最远距离k不再改变,且没有到末尾元素
        return false;
    }

    ///------------------------ 24 ---------------------

    /**
     * 有效的括号
     * 给定一个只包括 '(',')','{','}','[',']' 的字符串 s ,判断字符串是否有效。
     * https://leetcode.cn/problems/valid-parentheses/
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 每个右括号都有一个对应的相同类型的左括号。
     *
     * @param s
     * @return
     */

    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar == '(' || aChar == '{' || aChar == '[') {
                stack.push(aChar);
            } else {
                if (!stack.isEmpty()) {
                    if (aChar == ')') {
                        Character pop = stack.pop();
                        if (pop != '(') {
                            return false;
                        }
                    } else if (aChar == '}') {
                        Character pop = stack.pop();
                        if (pop != '{') {
                            return false;
                        }
                    } else if (aChar == ']') {
                        Character pop = stack.pop();
                        if (pop != '[') {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }

        }

        return stack.isEmpty();

    }

    ///------------------------ 25 ---------------------

    /**
     * https://leetcode.cn/problems/generate-parentheses/
     * 数字 n 代表生成括号的对数,请你设计一个函数,用于能够生成所有可能的并且 有效的 括号组合。
     * 括号生成 n对 （）
     */
    @Test
    public void test() {
        generateParenthesis(3);
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        createString(result, 0, 0, n, "");
        return result;
    }

    private void createString(List<String> result, int left, int right, int num, String temp) {
        if (left == right && right == num) {
            result.add(temp);
        }
        if (left < num) {
            createString(result, left + 1, right, num, temp + "(");
        }
        if (right < left) {
            createString(result, left, right + 1, num, temp + ")");
        }


    }

    ///------------------------ 26 ---------------------

    /**
     * 给你一个字符串数组,请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     * https://leetcode.cn/problems/sfvd7V/description/
     * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortString = new String(chars);
            List<String> orDefault = map.getOrDefault(sortString, new ArrayList<>());
            orDefault.add(str);
            map.put(sortString, orDefault);
        }
        return new ArrayList(map.values());
    }

    /**
     * B是否是A的子树
     *
     * @param A
     * @param B
     * @return https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof/solution/
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        // 若A与B其中一个为空,立即返回false
        if (A == null || B == null) {
            return false;
        }
        // B为A的子结构有3种情况,满足任意一种即可:
        // 1.B的子结构起点为A的根节点,此时结果为recur(A,B)
        // 2.B的子结构起点隐藏在A的左子树中,而不是直接为A的根节点,此时结果为isSubStructure(A.left, B)
        // 3.B的子结构起点隐藏在A的右子树中,此时结果为isSubStructure(A.right, B)
        return recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    /*
    判断B是否为A的子结构,其中B子结构的起点为A的根节点
    */
    private boolean recur(TreeNode A, TreeNode B) {
        // 若B走完了,说明查找完毕,B为A的子结构
        if (B == null) {
            return true;
        }
        // 若B不为空并且A为空或者A与B的值不相等,直接可以判断B不是A的子结构
        if (A == null) {
            return false;
        }
        // 当A与B当前节点值相等,若要判断B为A的子结构
        // 还需要判断B的左子树是否为A左子树的子结构 && B的右子树是否为A右子树的子结构
        // 若两者都满足就说明B是A的子结构,并且该子结构以A根节点为起点
        return A.val == B.val && recur(A.left, B.left) && recur(A.right, B.right);
    }


    ///------------------------ 27 ---------------------

    /**
     * 有效的字母异位词
     * https://leetcode.cn/problems/dKk3P7/solutions/1151409/you-xiao-de-bian-wei-ci-by-leetcode-solu-xzi0/
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        // 放到map里面,
        // 另一个 --
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            map.put(aChar, map.getOrDefault(aChar, 0) + 1);
        }

        char[] charsT = t.toCharArray();

        for (int i = 0; i < charsT.length; i++) {
            char aChar = charsT[i];
            map.put(aChar, map.getOrDefault(aChar, 0) - 1);
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() != 0) {
                return false;
            }
        }
        return true;
    }

    ///------------------------ 28 ---------------------

    /**
     * 不同路径
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * <p>
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * https://leetcode.cn/problems/unique-paths/
     */

    public int uniquePaths(int m, int n) {
        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            result[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            result[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                result[i][j] = result[i - 1][j] + result[i][j - 1];
            }
        }

        return result[m - 1][n - 1];

    }

    ///------------------------ 29 ---------------------

    /**
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     * 切记,第一行 第一列赋值的时候,如果有障碍物 ,直接跳出循环
     * 不同路径 II
     * https://leetcode.cn/problems/unique-paths-ii/
     */

    @Test
    public void getNast() {
        int[][] temp = new int[][]{{1, 0}};
        uniquePathsWithObstacles(temp);
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        // 切记 不要这样写,因为 假如 第一个是障碍物,那么后面的都是0
//        for (int j = 0; j < n; j++) {
//            if (obstacleGrid[0][j] == 0) {
//                dp[0][j] = 1;
//            }
//        }

        // 当 是障碍物的时 就跳出循环,不在往下执行
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        // 当 是障碍物的时 就跳出循环,不在往下执行
        for (int j = 0; j < n; j++) {
            if (obstacleGrid[0][j] == 0) {
                dp[0][j] = 1;
            } else {
                break;
            }

        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }

            }
        }

        return dp[m - 1][n - 1];
    }

    ///------------------------ 30 ---------------------

    /**
     * 最小路径和
     * 给定一个包含非负整数的 m x n 网格 grid ,请找出一条从左上角到右下角的路径,使得路径上的数字总和为最小。
     * dp[m][n] = Math.min(dp[m-1][n],dp[m][n])+1
     * <p>
     * https://leetcode.cn/problems/minimum-path-sum/
     */

    public int minPathSum(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[m - 1][n - 1];
    }

    ///------------------------ 30 ---------------------

    /**
     * 120：三角形最小路径和
     * https://leetcode.cn/problems/triangle/
     * <p>
     * dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        // 创建一个二维数组
        int[][] dp = new int[n][n];
        // 最上面的
        dp[0][0] = triangle.get(0)
                .get(0);
        for (int i = 1; i < n; ++i) {
            // 第一列赋值
            dp[i][0] = dp[i - 1][0] + triangle.get(i)
                    .get(0);
            for (int j = 1; j < i; ++j) {
                // 除了第一列 和 最后一列 都是这样的
                dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i)
                        .get(j);
            }
            // 最后一列 ,只能是
            dp[i][i] = dp[i - 1][i - 1] + triangle.get(i)
                    .get(i);
        }
        // 然后遍历 最后一行的 最小值即可
        int minTotal = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            minTotal = Math.min(minTotal, dp[n - 1][i]);
        }
        return minTotal;

    }

    ///------------------------ 31 ---------------------

    /**
     * 打家劫舍
     * dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
     * https://leetcode.cn/problems/house-robber/
     * 下面可以优化空间复杂度
     */
    public int rob(int[] nums) {
        // 先赋值第一个,因为下面for循环,不能从0开始
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int pre = nums[0];
        int cur = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int temp = cur;
            cur = Math.max(pre + nums[i], cur);
            pre = temp;
        }
        return cur;


    }

    public int rob1(int[] nums) {
        int[] dp = new int[nums.length];
        // 先赋值第一个,因为下面for循环,不能从0开始
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            // 主要是动态方程 i-2 就可以取 nums[i] , 要不就是i-1 不能取 nums[i]
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length - 1];


    }
    ///------------------------ 31 ---------------------

    /**
     * 打家劫舍 假如是圆形，也就是第一个 和 最后一个不能同时透
     * dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
     * https://leetcode.cn/problems/PzWKhm/description/
     */
    @Test
    public void testRob3() {
        System.out.println(rob3(new int[]{1, 2, 1, 1}));
    }

    public int rob3(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int rob1 = rob(Arrays.copyOfRange(nums, 0, nums.length - 1));
        int rob2 = rob(Arrays.copyOfRange(nums, 1, nums.length));
        return Math.max(rob1, rob2);
    }

    ///------------------------ 32 ---------------------

    /**
     * 最大子数组和
     * <p>
     * https://leetcode.cn/problems/maximum-subarray/
     * <p>
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     *
     *
     *
     *
     * <p>
     * 1，-2，5，-3，6
     * dp[0] = 1
     * dp[2] = 1-2 = -1
     * dp[3] = 5
     * dp[4] = 5-3=2
     * dp[5] = 2+6 = 8
     * dp[i] = Math.max(nums[i], dp[i - 1]);
     */

    public int maxSubArray(int[] nums) {

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        // 因为有负数,所以是其中的摸一个值
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            result = Math.max(result, dp[i]);
        }
        return result;

    }

    /**
     * 以 nums[i] 结尾的
     *
     * @param nums
     * @return
     */
    public int maxSubArray1(int[] nums) {
        int len = nums.length;
        // dp[i] 表示：以 nums[i] 结尾的连续子数组的最大和
        int[] dp = new int[len];
        dp[0] = nums[0];

        for (int i = 1; i < len; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
        }
        // 也可以在上面遍历的同时求出 res 的最大值，这里我们为了语义清晰分开写，大家可以自行选择
        int res = dp[0];
        for (int i = 1; i < len; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public int maxSubArray2(int[] nums) {
        // 初始化为int类型最小值
        int result = Integer.MIN_VALUE;
        // 0任何数等于任何数
        int tempTotal = 0;
        for (int i = 0; i < nums.length; i++) {
            tempTotal += nums[i];
            // 记录最大数值
            result = Math.max(tempTotal, result);
            if (tempTotal < 0) {
                // 如果和小于0,就重置为0，因为任何数加上一个负数一定小于当前数值
                tempTotal = 0;
            }
        }

        return result;
    }


    ///------------------------ 33 ---------------------

    /**
     * 实现 pow(x, n) ,即计算 x 的 n 次幂函数（即,xn）。
     * https://leetcode.cn/problems/powx-n/
     */
    public double myPow(double x, int n) {
        // 先变换 n 是负数的时候
        int N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        return fastPow(x, N);


    }

    private double fastPow(double x, int n) {
        // 这里的 n是正数
        if (n == 0) {
            return 1.0;
        }
        //拆分成 n/2个,相当于 2的二分,这样 复杂度是 log 2为底 N的对数
        int next = n / 2;
        double half = fastPow(x, next);
        // 如果是偶数,两个直接乘即可
        if (n % 2 == 0) {
            return half * half;
        } else {
            // 奇数 害的再乘以一个x
            return half * half * x;
        }

    }

    /**
     * 合并两个有序数组
     * https://leetcode.cn/problems/merge-sorted-array/
     * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，
     * 分别表示 nums1 和 nums2 中的元素数目。
     * <p>
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     * <p>
     * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，
     * 其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index1 = m - 1;
        int index2 = n - 1;
        int tail = m + n - 1;
        while (index1 >= 0 || index2 >= 0) {
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

    class MyStack {
        Queue<Integer> queue1;
        Queue<Integer> queue2;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            queue1 = new LinkedList<Integer>();
            queue2 = new LinkedList<Integer>();

        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            queue2.add(x);
            while (!queue1.isEmpty()) {
                queue2.add(queue1.poll());
            }
            Queue<Integer> temp = queue1;
            queue1 = queue2;
            queue2 = temp;

        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            return queue1.poll();
        }

        /**
         * Get the top element.
         */
        public int top() {
            return queue1.peek();
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return queue1.isEmpty();
        }
    }

    class MyQueue {

        private Stack<Integer> mStack1;
        private Stack<Integer> mStack2;

        public MyQueue() {
            mStack1 = new Stack<>();
            mStack2 = new Stack<>();
        }

        public void push(int x) {
            mStack1.push(x);

        }

        public int pop() {
            while (mStack2.empty()) {
                while (!mStack1.empty()) {
                    mStack2.push(mStack1.peek());
                    mStack1.pop();
                }
            }
            int val = mStack2.peek();
            mStack2.pop();
            //获取出队元素后,再将s2里面的元素放入s1里面。
            while (!mStack2.empty()) {
                mStack1.push(mStack2.pop());
            }
            return val;
        }

        public int peek() {
            while (mStack2.empty()) {
                while (!mStack1.empty()) {
                    mStack2.push(mStack1.peek());
                    mStack1.pop();
                }
            }
            int val = mStack2.peek();
            //获取出队元素后,再将s2里面的元素放入s1里面。
            while (!mStack2.empty()) {
                mStack1.push(mStack2.pop());
            }
            return val;
        }

        public boolean empty() {
            return mStack1.isEmpty();
        }
    }

    /**
     * 大数相加
     * https://leetcode.cn/problems/add-strings/description/
     * 示例 1：
     * <p>
     * 输入：num1 = "11", num2 = "123"
     * 输出："134"
     *
     * @param num1
     * @param num2
     * @return
     */

    public String add(String num1, String num2) {
        StringBuilder res = new StringBuilder("");
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        while (i >= 0 || j >= 0) {
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int tmp = n1 + n2 + carry;
            carry = tmp / 10;
            res.append(tmp % 10);
            i--;
            j--;
        }
        if (carry == 1) res.append(1);
        return res.reverse()
                .toString();
    }

    ///------------------------ 34 ---------------------
    // ## 把m个同样的苹果放在n个同样的盘子里,允许有的盘子空着不放,问有多少种不同的分法？(注：5,1,1和1,1,5是同一种分法）
//            - 当苹果是0 或者 盘子是1 的时候认为一种
//- 当盘子 大于 苹果个数的时候,直接 fun(m,m)
//- 当盘子 小于等于 苹果个数的时候,可以认为,有一个是0 再 加上都不是0  fun(m-n,n) m-n 证明每个盘子放了一个
    public int getResult(int m, int n) {//m个苹果放在n个盘子中共有几种方法
        if (m == 0 || n == 1)
            return 1;
        if (n > m)
            return getResult(m, m);
        else
            return getResult(m, n - 1) + getResult(m - n, n);
    }


    ///------------------------ 35 ---------------------

    /**
     * https://leetcode.cn/problems/add-two-numbers/
     * 给你两个非空 的链表,表示两个非负的整数。它们每位数字都是按照逆序的方式存储的,并且每个节点只能存储一位数字。
     * <p>
     * 请你将两个数相加,并以相同形式返回一个表示和的链表。
     * <p>
     * 你可以假设除了数字 0 之外,这两个数都不会以 0开头。
     *
     * @param l1
     * @param l2
     * @return 暴力, 遍历 链表 取出来相加
     * 各位*1 十位 * 10,百为 *100 取出来之后 ,然后相加,然后再弄成链表
     * 有可能会超过了 long 和 int 的最大大小,
     * 可以认为是两个大数相加
     * <p>
     * <p>
     * 大于10 需要进到下一位
     * <p>
     * 时间复杂度 Math.max(m,n)
     * 空间复杂度 创建了一个新的链表 Math.max(m,n)
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // 原链表的两个遍历指针
        ListNode p = l1;
        ListNode q = l2;

        // 结果链表的头结点head ,新节点
        ListNode result = new ListNode(-1);
        // 结果链表的遍历指针,代表当前操作的节点
        ListNode curr = result;
        // 进位
        int carry = 0;
        // 长度不够 补 0,或者是 剩下的给到 curr
        while (p != null || q != null) {
            // 如果是null 证明到头了,直接用 0
            int x = 0;
            if (p != null) {
                x = p.val;
            } else {
                x = 0;
            }

            int y = 0;
            if (q != null) {
                y = q.val;
            } else {
                y = 0;
            }

            // 对应位置的节点数值相加
            int sum = x + y + carry;

            // 得到 十位 ,进位的
            carry = sum / 10;
            // 得到 个位
            int num = sum % 10;
            // 创建新节点 , 插入到尾部
            ListNode temp = new ListNode(num);
            curr.next = temp;
            // 移动到下一个节点
            curr = curr.next;

            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
        }

        // 处理最后的一个 是否有 进位
        if (carry != 0) {
            ListNode temp = new ListNode(carry);
            curr.next = temp;
        }
        return result.next;

    }

    /**
     * 415. 字符串相加
     * 给定两个字符串形式的非负整数num1 和num2，计算它们的和并同样以字符串形式返回。
     * <p>
     * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger），也不能直接将输入的字符串转换为整数形式。
     * <p>
     * 给定两个字符串形式的非负整数 num1 和num2 ,计算它们的和并同样以字符串形式返回。
     *
     * @return
     */
    @Test
    public void tesss() {
        System.out.println(addStrings("11", "123"));
        ;
    }

    public String addStrings(String num1, String num2) {
        int length1 = num1.length() - 1;
        int length2 = num2.length() - 1;
        int ret = 0;// 进位
        StringBuilder result = new StringBuilder();
        while (length1 >= 0 || length2 >= 0) {
            int value1 = 0;
            if (length1 <= -1) {
                value1 = 0;
            } else {
                value1 = num1.charAt(length1) - '0';
            }

            int value2 = 0;
            if (length2 <= -1) {
                value2 = 0;
            } else {
                value2 = num2.charAt(length2) - '0';
            }

            int sum = ret + value1 + value2;
            ret = sum / 10;
            int nowValue = sum % 10;
            result.append(nowValue);
            length2--;
            length1--;

        }
        if (ret == 1) {
            result.append(1);
        }
        return result.reverse()
                .toString();

    }

    /**
     * https://leetcode.cn/problems/add-binary/
     * 二进制求和
     *
     * @param a
     * @param b 给你两个二进制字符串,返回它们的和（用二进制表示）。
     *          <p>
     *          输入为 非空 字符串且只包含数字 1 和 0。
     * @return
     */
    public String addBinary(String a, String b) {
        StringBuffer ans = new StringBuffer();

        int n = Math.max(a.length(), b.length());
        int carry = 0;
        for (int i = 0; i < n; ++i) {
            int tempA = 0;
            if (i < a.length()) {
                tempA = (a.charAt(a.length() - 1 - i) - '0');
            }
            int tempB = 0;
            if (i < b.length()) {
                tempB = (b.charAt(b.length() - 1 - i) - '0');
            }
            carry = carry + tempA + tempB;

            if (carry == 2) {
                carry = 1;
                ans.append(0);
            } else if (carry == 3) {
                carry = 1;
                ans.append(1);
            } else {
                ans.append(carry);
                carry = 0;
            }
        }

        if (carry > 0) {
            ans.append('1');
        }
        ans.reverse();

        return ans.toString();

    }


    ///------------------------ 36 ---------------------

    /**
     * https://leetcode.cn/problems/reverse-integer/
     * 整数反转
     * <p>
     * 给你一个 32 位的有符号整数 x ,返回将 x 中的数字部分反转后的结果。
     * <p>
     * 如果反转后整数超过 32 位的有符号整数的范围[−231, 231− 1] ,就返回 0。
     * <p>
     * 假设环境不允许存储 64 位整数（有符号或无符号）。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/reverse-integer
     * <p>
     * 1. 收尾互换
     * 2. 直接while(x/10!=0)
     */
    public int reverse(int x) {
        if (x == Integer.MIN_VALUE || x == Integer.MAX_VALUE) {
            // 整数类型的最小值的绝对值 比 最大值的绝对值 大1
            return 0;
        }
        int sign = 1;// 符号
        // 转成正数
        if (x < 0) {
            sign = -1;
            x = -x;
        }
        // 1.整数转字符串,再转字符数组
        String str = String.valueOf(x);
        char[] chars = str.toCharArray();
// 2.交换首位(start)和末位(end)数字
// 3.循环操作:依次交换第二(start++)和倒数第二个(end--)
        int start = 0, end = chars.length - 1;
        while (start < end) { // 反转完成的标志:start >= end
// 交换两端等距离的元素
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
// 4.将原数组转成字符串,再转成整数输出
        long value = Long.valueOf(String.valueOf(chars));
        boolean b = value > Integer.MAX_VALUE || value < Integer.MIN_VALUE;
        int result = b ? 0 : (int) value;

        return result * sign;

    }

    @Test
    public void getRe() {
        reverse1(123);
        removeOuterParentheses("(()())(())");
    }

    public int reverse1(int x) {
        if (x == Integer.MIN_VALUE || x == Integer.MAX_VALUE) {
            // 整数类型的最小值的绝对值 比 最大值的绝对值 大1
            return 0;
        }
        int sign = 1;// 符号
        // 转成正数
        if (x < 0) {
            sign = -1;
            x = -x;
        }

        int result = 0;
        while (x / 10 != 0) {
            // 当补位 最后一位之前 判断 如果溢出 ,就返回0 就行了,补充之前 肯定不会移除
            int temp = x % 10;
            // 将每一位数字计算累加,将上次结果*10 + 新数字
            result = result * 10 + temp;
            x = x / 10;

        }

        // 最后一位 给补到这里时候 先用long 做接收如果超出int的值 直接是0；
        if (x != 0) {
            // 先用long 补充
            long res = result;
            res = res * 10 + x;
            // res 有可能超出范围
            if (res < Integer.MIN_VALUE || res > Integer.MAX_VALUE) {
                // 整数类型的最小值的绝对值 比 最大值的绝对值 大1
                result = 0;
            } else {
                result = (int) res;
            }
        }

        return result * sign;
    }

    ///------------------------ 37 ---------------------

    /**
     * 删除最外层的括号
     * 例如,"","()","(())()"和"(()(()))"都是有效的括号字符串。
     * 如果有效字符串 s 非空,且不存在将其拆分为 s = A + B的方法,我们称其为原语（primitive）,其中A 和B都是非空有效括号字符串。
     * <p>
     * 给出一个非空有效字符串 s,考虑将其进行原语化分解,使得：s = P_1 + P_2 + ... + P_k,其中P_i是有效括号字符串原语。
     * <p>
     * 对 s 进行原语化分解,删除分解中每个原语字符串的最外层括号,返回 s 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/remove-outermost-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权,非商业转载请注明出处。
     */
    ////////////////////--------用左括号 有括号 ++的形式,如果左右相等,则截取--------/////////////////////
    public String removeOuterParentheses(String s) {
        // 左括号数量 等于 有括号数量时,拼接
        StringBuilder result = new StringBuilder();
        // 左括号数量
        int left = 0;
        // 有括号数量
        int right = 0;

        int start = 0;

        for (int i = 0; i < s.length(); i++) {
            // 如果左括号 左边++
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            // 如果 两个相等的话
            if (left == right) {
                // 起始值 就是+1
                result.append(s.substring(start + 1, i));
                // 记录起始值
                start = i + 1;
            }
        }
        return result.toString();
    }

    ////////////////////-------- 用栈的形式 --------/////////////////////
    public String removeOuterParentheses1(String s) {
        // 左括号数量 等于 有括号数量时,拼接
        StringBuilder reslut = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            // 如果左括号 左边++
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(ch);
            } else {
                stack.pop();
            }

            // 如果 栈是空的 就
            if (stack.isEmpty()) {
                // 起始值 就是+1
                reslut.append(s.substring(start + 1, i));
                // 记录起始值
                start = i + 1;
            }
        }
        return reslut.toString();
    }

    ///------------------------ 38 ---------------------

    /**
     * 给定一个字符串 s ,请你找出其中不含有重复字符的 最长子串 的长度。
     * 无重复字符的最长子串
     * 滑块
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc",所以其长度为 3。
     * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
     */

    @Test
    public void getRe11() {
        lengthOfLongestSubstring("abacabcbb");
    }

    // 其他的一些滑动窗口
    // https://leetcode.cn/problems/longest-substring-without-repeating-characters/solution/hua-dong-chuang-kou-by-powcai/
    public int lengthOfLongestSubstring(String s) {

        // value 存 出现的索引
        HashMap<Character, Integer> map = new HashMap<>();
        int right = 0;
        int left = 0;
        int maxLength = 0;
        String reslut = "";
        while (right < s.length()) {
            char c = s.charAt(right);
            Integer index = map.getOrDefault(c, -1);

            if (index == -1) {
                map.put(c, right);
                // 记住要加1 你就认为 一个 a ,所以这里得加1
                if (right - left + 1 > maxLength) {
                    maxLength = right - left + 1;
                    reslut = s.substring(left, right + 1);
                    System.out.println(reslut);
                }
                right++;

            } else {
                // 直接遍历前面的数据 移除到
                for (int i = left; i <= index; i++) {
                    map.remove(s.charAt(i));
                }
                left = index + 1;
            }
        }
        return maxLength;
    }

    ///------------------------ 39 ---------------------

    /**
     * 翻转字符串里的单词
     * https://leetcode.cn/problems/reverse-words-in-a-string/
     * 给你一个字符串 s ,逐个翻转字符串中的所有 单词 。
     * <p>
     * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
     * <p>
     * 请你返回一个翻转 s 中单词顺序并用单个空格相连的字符串。
     * <p>
     * 说明：
     * <p>
     * 输入字符串 s 可以在前面、后面或者单词间包含多余的空格。
     * 翻转后单词间应当仅用一个空格分隔。
     * 翻转后的字符串中不应包含额外的空格。
     * <p>
     * 输入：s = "the sky is blue"
     * 输出："blue is sky the"
     * <p>
     * 输入：s = " hello world "
     * 输出："world hello"
     * 解释：输入字符串可以在前面或者后面包含多余的空格,但是翻转后的字符不能包括。
     */
    // 1. 暴力 以空格分割
    // 2. 把字符串反转过来,然后把单词的 再反转
    @Test
    public void getT() {
        reverseWords("  hello world  ");
    }

    public String reverseWords(String s) {
        // 时间复杂度 是 n
        //
        String[] strings = s.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            String temp = strings[strings.length - 1 - i];
            // 有可能是空格 有可能是"" 空串
            if (!temp.equals(" ") && !temp.equals("")) {
                if (!result.toString()
                        .isEmpty()) {
                    result.append(" ");
                }
                result.append(temp);

            }
        }
        return result.toString();
    }

    public String reverseWords1(String s) {
        int index = 0;
        LinkedList<String> list = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        while (index < s.length()) {
            char c = s.charAt(index);
            if (c != ' ') {
                sb.append(c);
            } else {
                if (sb.length() != 0) {
                    // 到达了单次结尾
                    // 将单词添加到双Duang 队列的头部
                    list.addFirst(sb.toString());
                    // 清空
                    sb.setLength(0);
                }
            }
            index++;
        }

        if (sb.length() > 0) {
            list.addFirst(sb.toString());
        }

        return String.join(" ", list);
    }


    ///------------------------ 40 ---------------------

    /**
     * 排序矩阵查找
     * 给定M×N矩阵,每一行、每一列都按升序排列,请编写代码找出某元素。
     * [
     * [1,   4,  7, 11, 15],
     * [2,   5,  8, 12, 19],
     * [3,   6,  9, 16, 22],
     * [10, 13, 14, 17, 24],
     * [18, 21, 23, 26, 30]
     * ]
     * 给定 target=5,返回true。
     * <p>
     * 给定target=20,返回false。
     * https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
     */

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        // 从左下角开始遍历
        int rows = matrix.length, columns = matrix[0].length;
        int row = 0, column = columns - 1;
        while (row < rows && column >= 0) {
            int num = matrix[row][column];
            if (num == target) {
                return true;
            } else if (num > target) {
                column--;
            } else {
                row++;
            }
        }
        return false;
    }


    ///------------------------ 41 ---------------------

    /**
     * 多数元素
     * 给定一个大小为 n 的数组,找到其中的多数元素。多数元素是指在数组中出现次数 大于⌊ n/2 ⌋的元素。
     * <p>
     * 你可以假设数组是非空的,并且给定的数组总是存在多数元素。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/majority-element
     * 著作权归领扣网络所有。商业转载请联系官方授权,非商业转载请注明出处。
     */

    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer num = map.getOrDefault(nums[i], 0) + 1;
            if (num > nums.length / 2) {
                return nums[i];
            } else {
                map.put(nums[i], num);
            }
        }
        return -1;
    }


    ///------------------------ 43 ---------------------
    /**
     * 0，1 背包，要不选要不不选，只能选一次
     */

    /**
     * 给你一个可装载重量为 W 的背包和 N 个物品，每个物品有重量和价值两个属性。
     * 其中第 i 个物品的重量为 wt[i]，价值为 val[i]，
     * 现在让你用这个背包装物品，最多能装的价值是多少？
     */
    int knapsack(int W, int N, int[] wt, int[] val) {
        assert N == wt.length;
        // base case 已初始化
        int[][] dp = new int[N + 1][W + 1];
        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= W; w++) {
                if (w - wt[i - 1] < 0) {
                    // 这种情况下只能选择不装入背包
                    dp[i][w] = dp[i - 1][w];
                } else {
                    // 装入或者不装入背包，择优
                    dp[i][w] = Math.max(
                            dp[i - 1][w - wt[i - 1]] + val[i - 1],
                            dp[i - 1][w]
                    );
                }
            }
        }

        return dp[N][W];
    }

    ///------------------------ 42 ---------------------

    /**
     * 322 零钱兑换
     * https://leetcode.cn/problems/gaM7Ch/description/
     * 给你一个整数数组 coins ,表示不同面额的硬币；以及一个整数 amount ,表示总金额。
     * <p>
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额,返-1 。
     * <p>
     * 你可以认为每种硬币的数量是无限的。
     * <p>
     * 输入：coins = [1, 2, 5], amount = 11
     * 输出：3
     * 解释：11 = 5 + 5 + 1
     * <p>
     * dp[amount] =
     * 第一枚
     * 如果选择是  1 那么 dp(n) = dp(n-1)+1
     * 如果选择是  2 那么 dp[n] = dp(n-2)+1
     * 如果选择是  5 那么 dp[n] = dp(n-5)+1
     * 所以是 这三个的最小值
     * https://leetcode.cn/problems/gaM7Ch/description/
     */

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // // 注意：因为要比较的是最小值,这个不可能的值就得赋值成为一个最大值
        //为啥 dp 数组中的值都初始化为 amount + 1 呢，因为凑成 amount 金额的硬币数最多只可能等于
        // amount（全用 1 元面值的硬币），
        //        // 所以初始化为 amount + 1 就相当于初始化为正无穷，便于后续取最小值。
        //        // 为啥不直接初始化为 int 型的最大值 Integer.MAX_VALUE 呢？
        //        // 因为后面有 dp[i - coin] + 1，这就会导致整型溢出
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        // 理解 dp[0] = 0 的合理性,单独一枚硬币如果能够凑出面值,符合最优子结构
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                // 找 这几个里面dp[i]的最小值
                // 如果 i 大于 当前coin的数值 ,就可以进去
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
                }
            }
        }
        if (dp[amount] == Integer.MAX_VALUE - 1) {
            dp[amount] = -1;
        }
        return dp[amount];
    }

    ///------------------------ 86 ---------------------

    /**
     * 39. 组合总和 跟下面的 518. 零钱兑换 II 一样 ，只不过518 求的是个数，只不过 518 是动归，背包问题
     * https://leetcode.cn/problems/combination-sum/description/
     * 无重复元素的数组
     * 输入：candidates = [2,3,6,7], target = 7
     * 输出：[[2,2,3],[7]]
     * 解释：
     * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
     * 7 也是一个候选， 7 = 7 。
     * --------------------0
     * ----------2             3    第一次可以选择任意一个，第二次也可以选择任意一个
     * ----[ 2  ,   3]    [ 2 ,  3]
     * [2, 3] [2, 3]  [2, 3] [2, 3]
     * 仅有这两种组合。
     */

    @Test
    public void combinationSumTest() {
        combinationSum(new int[]{2, 3, 6, 7}, 7);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        dfsCombinationSum(candidates, 0, 0, target, new ArrayList<Integer>(), res);
        return res;
    }

    private void dfsCombinationSum(int[] candidates, int index, int tempSum, int target, ArrayList<Integer> path, List<List<Integer>> res) {
        if (tempSum == target) {
            res.add(new ArrayList<>(path));
            System.out.println(Arrays.toString(path.toArray()));

        } else {
            for (int i = index; i < candidates.length; i++) {
                if (tempSum + candidates[i] <= target) {
                    path.add(candidates[i]);
                    dfsCombinationSum(candidates, i, tempSum + candidates[i], target, path, res);
                    path.remove(path.size() - 1);
                }

            }

        }
    }

    ///------------------------ 87 ---------------------


    /**
     * 518. 零钱兑换 II
     * 请你计算并返回可以凑成总金额的硬币组合数 个数
     * 输入：amount = 5, coins = [1, 2, 5]
     * 输出：4
     * 解释：有四种方式可以凑成总金额：
     * 输入：coins = [1, 2, 5], amount = 11
     * 输出：3
     * 解释：11 = 5 + 5 + 1
     * dp[amount] =
     * 第一枚
     * 如果选择是  1 那么 dp(n) = dp(n-1)+1
     * 如果选择是  2 那么 dp[n] = dp(n-2)+1
     * 如果选择是  5 那么 dp[n] = dp(n-5)+1
     * dp[4] =
     */
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1; // base case
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j - coins[i] >= 0) {
                    dp[j] = dp[j] + dp[j - coins[i]];
                }
            }
        }


        return dp[amount];
    }

    /**
     * 加入 coins = {25,10,5,1} 的话 凑出来 41,因为  1 ,5,10,25 对应都是 向上都是5的倍数
     *
     * @return
     */
    @Test
    public void getMon() {
        int[] array = {25, 10, 5, 1};
        System.out.println(coinChange1(array, 50));
        ;
    }

    public int coinChange1(int[] coins, int amount) {
        int reslut = 0;
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < coins.length; i++) {
            if (amount - coins[i] >= 0) {
                amount = amount - coins[i];
                reslut++;

                arrayList.add(coins[i]);
                System.out.println(coins[i]);
                i--;
            }
        }
        return reslut;
    }


    ///------------------------ 44 ---------------------

    /**
     * 颜色分类
     * 给定一个包含红色、白色和蓝色,一共n 个元素的数组,原地对它们进行排序,使得相同颜色的元素相邻,并按照红色、白色、蓝色顺序排列。
     * <p>
     * 此题中,我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/sort-colors
     * 著作权归领扣网络所有。商业转载请联系官方授权,非商业转载请注明出处。
     * <p>
     * 输入：nums = [2,0,2,1,1,0]
     * 输出：[0,0,1,1,2,2]
     */

    public void sortColors(int[] nums) {

        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (nums[right] == 0) {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
                left++;
            }
            right++;
        }

        right = left;
        while (right < nums.length) {
            if (nums[right] == 1) {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
                left++;
            }
            right++;
        }


    }

    ///------------------------ 45 ---------------------

    /**
     * 字符串压缩
     * 字符串压缩。利用字符重复出现的次数,编写一种方法,实现基本的字符串压缩功能。
     * 比如,字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短,则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/compress-string-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权,非商业转载请注明出处。
     */
    public String compressString(String S) {
        StringBuilder result = new StringBuilder();

        int index = 0;
        if (S.length() == 1 || S.length() == 0) {
            return S;
        }
        char temp = S.charAt(0);
        int num = 0;
        while (index < S.length()) {
            if (temp == S.charAt(index)) {
                index++;
                num++;
            } else {
                result.append(temp)
                        .append(num);
                temp = S.charAt(index);
                num = 0;

            }

        }
        result.append(temp)
                .append(num);
        if (result.length() >= S.length()) {
            return S;
        }
        return result.toString();
    }


    ///------------------------ 46 ---------------------

    /**
     * 给你一个字符数组 chars ,请使用下述算法压缩：
     * <p>
     * 从一个空字符串 s 开始。对于 chars 中的每组 连续重复字符 ：
     * <p>
     * 如果这一组长度为 1 ,则将字符追加到 s 中。
     * 否则,需要向 s 追加字符,后跟这一组的长度。
     * 输入：chars = ["a","a","b","b","c","c","c"]
     * 输出：返回 6 ,输入数组的前 6 个字符应该是：["a","2","b","2","c","3"]
     * <p>
     * <p>
     * 链接：https://leetcode.cn/problems/string-compression
     */
    @Test
    public void getCom() {
        char[] demo = {'a', 'a'};
        compress(demo);
    }

    public int compress(char[] chars) {
        StringBuilder result = new StringBuilder();
        if (chars.length == 1 || chars.length == 0) {
            return chars.length;
        }
        int index = 0;
        int num = 0;
        char temp = chars[0];
        while (index < chars.length) {
            if (temp == chars[index]) {
                index++;
                num++;
            } else {
                result.append(temp);
                if (num != 1) {
                    result.append(num);
                }
                temp = chars[index];
                num = 0;
            }
        }
        result.append(temp);
        if (num != 1) {
            result.append(num);
        }
        int length = chars.length;
        if (result.length() <= chars.length) {
            for (int i = 0; i < result.length(); i++) {
                chars[i] = result.toString()
                        .toCharArray()[i];
            }
            length = result.length();
        }
        System.out.println(Arrays.toString(chars));
        return length;
    }

    ///------------------------ 47 ---------------------

    /**
     * https://leetcode.cn/problems/longest-palindromic-substring/
     * 5. 最长回文子串
     * 给你一个字符串 s,找到 s 中最长的回文子串。
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     *
     * @return
     */
    @Test
    public void subSortTest() {
        int[] array = {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
        System.out.println(longestPalindrome("array"));
    }

    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            // 以 s[i] 为中心的最长回文子串
            String s1 = palindrome(s, i, i);
            // 以 s[i] 和 s[i+1] 为中心的最长回文子串
            String s2 = palindrome(s, i, i + 1);
            // res = longest(res, s1, s2)
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }
        return res;
    }

    String palindrome(String s, int l, int r) {
        // 防止索引越界
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            // 向两边展开
            l--;
            r++;
        }
        // 返回以 s[l] 和 s[r] 为中心的最长回文串
        return s.substring(l + 1, r);
    }


    ///------------------------ 48 ---------------------

    /**
     * 买卖股票的最佳时机 II ,贪心
     * 给定一个数组 prices ,其中prices[i] 是一支给定股票第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * <p>
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     * 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii
     * 6
     * 5
     * <p>
     * 4                4
     * <p>
     * 3             3
     * 1
     */
    public int maxProfit(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {  // 卖出有利可图
                ans += (prices[i] - prices[i - 1]);
            }
        }

        return ans;
    }
    ///------------------------ 49 ---------------------

    /**
     * 剑指 Offer 63. 股票的最大利润
     * 假设把某股票的价格按照时间先后顺序存储在数组中,请问买卖该股票一次可能获得的最大利润是多少？
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * 解释: 在第 2 天（股票价格 = 1）的时候买入,在第 5 天（股票价格 = 6）的时候卖出,最大利润 = 6-1 = 5 。
     * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
     * <p>
     * 链接：https://leetcode.cn/problems/gu-piao-de-zui-da-li-run-lcof
     * <p>
     * 假设 找到每一天的卖股票 找打最大值
     * <p>
     * 循环,记录当前的最小值,以及可赚的最大值
     */

    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length == 0 || prices.length == 1) {
            return 0;
        }
        // 前面扫描过的最小价格
        int min = prices[0];
        // 前面扫描过的 最大利润是多少
        int maxMoney = 0;
        // 扫描所有的价格
        for (int i = 1; i < prices.length; i++) {
            // 如果 比当前最小值 要大,算吃来当前的利润,然后和 前面最大的利润作比较
            // 如果小于的话,就把最小值 替换成
            if (prices[i] - min >= 0) {
                maxMoney = Math.max(maxMoney, prices[i] - min);
            } else {
                min = prices[i];
            }
        }
        return maxMoney;

    }

    public int maxProfit2(int[] prices) {
        // 相间之后 连续的子序列和
        // dp[i] = Math.max(dp[i-1]+(prices[i]-prices[i-1]),prices[i]-prices[i-1])
        int[] dp = new int[prices.length];
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i] = Math.max(dp[i - 1] + (prices[i] - prices[i - 1]), prices[i] - prices[i - 1]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }
    ///------------------------ 50 ---------------------

    /**
     * 最长连续递增序列
     * 给定一个未经排序的整数数组,找到最长且 连续递增的子序列,并返回该序列的长度。
     * 输入：nums = [1,3,5,4,7]
     * 输出：3
     * 解释：最长连续递增序列是 [1,3,5], 长度为3。
     * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的,因为 5 和 7 在原数组里被 4 隔开。
     * <p>
     * 链接：https://leetcode.cn/problems/longest-continuous-increasing-subsequence
     * <p>
     * dp[i] = Math.max(dp[i-1],)
     */
    @Test
    public void getLcis() {
        int[] array = {2, 2, 2, 2, 2};
        findLengthOfLCIS(array);
    }

    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        int maxResult = 1;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] > 0) {
                count++;
                maxResult = Math.max(count, maxResult);
            } else {
                count = 1;
            }
        }

        return maxResult;

    }
    ///------------------------ 50 ---------------------

    /**
     * 最长递增子序列
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 不是连续的哦
     * 输入：nums = [10,9,2,5,3,7,101,18]
     * dp[0] = 10 // 1
     * dp[1] = 9  // 1
     * dp[2] = 2  // 1
     * dp[3] = [2，5] 0-2小于5的最大值 + 1 // 2
     * dp[4] = [2,3] 0-3小于3的最大值 +1  // 2
     * dp[5] = [2,3,7]或者是[2,5,7] 0-4小于7的最大值 +1 //3
     * dp[6] = [2,3,7,101]或者是[2,5,7,101] 0-5中小于101最大值 +1 // 4
     * dp[7] = [2,3,7,18] 或者是[2,5,7,18] 0-6中小于18的最大值 +1 // 4
     * 输出：4
     * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
     * <p>
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // dp[i] 是 以i为结尾的 最大递增子序列
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int result = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }

            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }


    ///------------------------ 50 ---------------------

    /**
     * 全排列
     * https://leetcode.cn/problems/permutations/
     * 给定一个不含重复数字的数组 nums ,返回其 所有可能的全排列 。你可以 按任意顺序 返回答案
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     * https://leetcode.cn/problems/VvJkup/description/
     *
     * @return
     */
    @Test
    public void getPermute() {
        int[] demo = {1, 2, 3};
        permute(demo);
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dps(new ArrayList<Integer>(), result, nums);
        return result;

    }

    private void dps(ArrayList<Integer> path, List<List<Integer>> result, int[] nums) {
        if (nums.length == path.size()) {
            result.add(path);
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (!path.contains(nums[i])) {
                    ArrayList<Integer> temp = new ArrayList<>(path);
                    temp.add(nums[i]);
                    dps(temp, result, nums);
                }

            }
        }
    }

    /**
     * https://leetcode.cn/problems/maximum-product-subarray/
     * 给你一个整数数组 nums ,请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字）,并返回该子数组所对应的乘积。
     * 因为有负数,所以记录起来最大值 和 最小值
     */
    public int maxProduct(int[] nums) {
        //由于存在负数，那么会导致最大的变最小的，最小的变最大的。因此还需要维护当前最小值imin
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;//阶段最大值 阶段最小值
        for (int i = 0; i < nums.length; i++) {
            //当遇到负数的时候进行交换，因为阶段最小*负数就变阶段最大了，反之同理
            if (nums[i] < 0) {
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            //在这里用乘积和元素本身比较的意思是：
            //对于最小值来说，最小值是本身则说明这个元素值比前面连续子数组的最小值还小。相当于重置了阶段最小值的起始位置
            imax = Math.max(imax * nums[i], nums[i]);
            imin = Math.min(imin * nums[i], nums[i]);

            //对比阶段最大值和结果最大值
            max = Math.max(max, imax);
        }
        return max;
    }


    ///------------------------ 51 ---------------------

    /**
     * 三数之和
     *
     * @param nums
     * @return 给你一个包含 n 个整数的数组nums,判断nums中是否存在三个元素 a,b,c ,使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     * <p>
     * 链接：https://leetcode.cn/problems/3sum
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        //排序
        Arrays.sort(nums);
        //双指针
        int len = nums.length;
        for (int i = 0; i < len; ++i) {
            if (nums[i] > 0) return lists;

            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int curr = nums[i];
            int L = i + 1, R = len - 1;
            while (L < R) {
                int tmp = curr + nums[L] + nums[R];
                if (tmp == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(curr);
                    list.add(nums[L]);
                    list.add(nums[R]);
                    lists.add(list);
                    // 去重
                    while (L < R && nums[L + 1] == nums[L]) {
                        ++L;
                    }
                    // 去重
                    while (L < R && nums[R - 1] == nums[R]) {
                        --R;
                    }
                    ++L;
                    --R;
                } else if (tmp < 0) {
                    // 因为是递增的,所以这里
                    ++L;
                } else {
                    --R;
                }
            }
        }
        return lists;
    }


    ///------------------------ 52 ---------------------

    /**
     * 给你一个整数 x ,如果 x 是一个回文整数,返回 true ；否则,返回 false 。
     * <p>
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如,121 是回文,而 123 不是。
     * <p>
     * 链接：https://leetcode.cn/problems/palindrome-number
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        char[] chars = String.valueOf(x)
                .toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (right > left) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    ///------------------------ 53 ---------------------

    /**
     * 旋转链表 K次
     * https://leetcode.cn/problems/rotate-list/
     *
     * @return
     */
    @Test
    public void getRotateRight() {
        ListNode head = new ListNode(1);
        ListNode head1 = new ListNode(2);
        head.next = head1;
        ListNode head2 = new ListNode(3);
        head1.next = head2;
        ListNode head3 = new ListNode(4);
        head2.next = head3;
        ListNode head4 = new ListNode(5);
        head3.next = head4;
        rotateRight(head, 2);

    }

    public ListNode rotateRight(ListNode head, int k) {

        if (head == null || head.next == null) {
            return head;
        }
        // 得到length  并且 设置成 环形链表
        int length = getListaNodeLength(head);
        // 精简 k
        int m = k % length;

        // 找到 倒数第 m个节点
        ListNode fast = head;
        ListNode pre = null;
        for (int i = 0; i < length; i++) {
            if (i == length - m) {
                // 找到这个节点的前一个,然后设置next 为 null 即可
                break;
            }
            pre = fast;
            fast = fast.next;
        }
        pre.next = null;
        // 返回这个 fast 节点即可
        return fast;


    }

    private int getListaNodeLength(ListNode head) {
        ListNode temp = head;
        int length = 0;
        ListNode tail = null;
        while (temp != null) {
            length++;
            if (temp.next == null) {
                tail = temp;
            }
            temp = temp.next;

        }
        tail.next = head;
        return length;
    }
    ///------------------------ 54 ---------------------

    /**
     * 给你一个数组 nums和一个值 val,你需要 原地 移除所有数值等于val的元素,并返回移除后数组的新长度。
     * <p>
     * 不要使用额外的数组空间,你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * <p>
     * https://leetcode.cn/problems/remove-element/
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (nums[right] != val) {
                nums[left] = nums[right];
                left++;
            }
            right++;
        }
        return left;
    }

    ///------------------------ 55 ---------------------

    /**
     * 最长连续序列
     * 输入：nums = [100,4,200,1,3,2]
     * 输出：4
     * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
     * 给定一个未排序的整数数组 nums ,找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     * <p>
     * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题
     * <p>
     * https://leetcode.cn/problems/longest-consecutive-sequence/
     */

    public int longestConsecutive1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int result = 1;
        int temp = 1;
        int pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] == pre + 1) {
                temp++;
                result = Math.max(result, temp);
                pre = nums[i];
            } else {
                temp = 1;
                pre = nums[i];
            }
        }
        return result;
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;

    }


    @Test
    public void getSing() {
        int[] demo = {2, 2, 1};
        singleNumber(demo);
    }

    public int singleNumber(int[] nums) {

        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                set.remove(nums[i]);
            } else {
                set.add(nums[i]);
            }
        }
        Iterator<Integer> iterator = set.iterator();
        return set.iterator()
                .next();

    }
    ///------------------------ 57 ---------------------

    /**
     * 左旋转字符串
     * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如,输入字符串"abcdefg"和数字2,该函数将返回左旋转两位得到的结果"cdefgab"。
     * <p>
     * <p>
     * <p>
     * 链接：https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof
     */

    public String reverseLeftWords(String s, int n) {
        n = n % s.length();
        char[] c = s.toCharArray();

        StringBuilder result = new StringBuilder();
        for (int i = n; i < s.length() + n; i++) {
            if (i > s.length() - 1) {
                result.append(s.charAt(i - s.length()));
            } else {
                result.append(s.charAt(i));
            }

        }
        return result.toString();
    }

    @Test
    public void lowest() {
        TreeNode node7 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4);


        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node0 = new TreeNode(0);
        TreeNode node8 = new TreeNode(8);


        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);


        TreeNode node3 = new TreeNode(3);
        node3.left = node5;
        node3.right = node1;

        node5.left = node6;
        node5.right = node2;
        node1.left = node0;
        node1.right = node8;

        node2.left = node7;
        node2.right = node4;


        System.out.println("-----------" + lowestCommonAncestor(node3, node6, node4).val);
        ;


    }
    ///------------------------ 55 ---------------------

    /**
     * https://leetcode.cn/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    //给定一个二叉树, 找到该树中两个指定节点的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            //只要当前根节点是p和q中的任意一个,就返回（因为不能比这个更深了,再深p和q中的一个就没了）
            return root;
        }
        //根节点不是p和q中的任意一个,那么就继续分别往左子树和右子树找p和q
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //p和q都没找到,那就没有
        if (left == null && right == null) {
            return null;
        }
        //左子树没有p也没有q,就返回右子树的结果
        if (left == null) {
            return right;
        }
        //右子树没有p也没有q就返回左子树的结果
        if (right == null) {
            return left;
        }
        //左右子树都找到p和q了,那就说明p和q分别在左右两个子树上,所以此时的最近公共祖先就是root
        return root;
    }


    ///------------------------ 56 ---------------------

    /**
     * https://leetcode.cn/problems/spiral-matrix/
     * 螺旋矩阵 54
     * 顺时针打印矩阵
     *
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {

        if (matrix.length == 0) {
            return new int[]{};
        }
        ArrayList<Integer> res = new ArrayList<>();
        int left = 0;                       //左边界
        int right = matrix[0].length - 1;    //右边界
        int top = 0;                       //上边界
        int bootom = matrix.length - 1;       //下边界
        while (true) {
            //从左往右
            //列在变,列为循环值
            //从左往右的下一步是往下走,上边界内缩,故++t
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            // 因为已经从从左往右 了,所以 top 得 减1
            top++;
            if (top > bootom) break;
            //从上往下,行在变
            //从上往下的下一步是从右往左,右边界收缩,--r
            for (int j = top; j <= bootom; j++) {
                res.add(matrix[j][right]);
            }
            right--;
            if (right < left) break;
            //从右向左,列在变
            //从右往左的下一步是从下往上,下边界收缩,--b
            for (int i = right; i >= left; i--) {
                res.add(matrix[bootom][i]);
            }
            bootom--;
            if (bootom < top) break;
            //从下到上,行在变
            //从下到上的下一步是从左到右,左边界收缩,++l
            for (int i = bootom; i >= top; i--) {
                res.add(matrix[i][left]);
            }
            left++;
            if (left > right) break;
        }
        int[] result = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
        return result;
    }


    ///------------------------ 57 ---------------------
    /**
     * 二叉搜索树的第k大节点
     * https://leetcode.cn/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
     * 前序遍历 就是递增的, “根、左、右”
     * 中序遍历             左根右
     * 后序遍历             左右根
     * 中序遍历 倒叙,即 right 在 前面
     * 后续
     */
    int res, k;

    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }

    void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.right);
        if (k == 0) return;
        if (--k == 0) res = root.val;
        dfs(root.left);
    }


    ///------------------------ 58 ---------------------

    /**
     * 给定二叉搜索树（BST）的根节点 root 和一个整数值val。
     * <p>
     * 你需要在 BST 中找到节点值等于 val 的节点。 返回以该节点为根的子树。 如果节点不存在,则返回 null 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/search-in-a-binary-search-tree
     * https://leetcode.cn/problems/search-in-a-binary-search-tree/
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (val == root.val) {
            return root;
        }
        return searchBST(val < root.val ? root.left : root.right, val);
    }

    ///------------------------ 59 ---------------------

    /**
     * 209. 长度最小的子数组
     * https://leetcode.cn/problems/minimum-size-subarray-sum/
     *
     * @param s
     * @param nums
     * @return 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组
     * 时间复杂度：O(n)
     * 其中 nnn 是数组的长度。指针  最多各移动 n 次。
     * <p>
     * 空间复杂度：O(1)
     */
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int sum = 0;
        while (right < n) {
            // 右指针移动 直到 sum >=s 再进入 里面的循环,然后左--
            sum = sum + nums[right];
            while (sum >= s) {
                ans = Math.min(ans, right - left + 1);
                sum = sum - nums[left];
                left++;
            }
            right++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }


    ///------------------------ 60 ---------------------

    /**
     * 旋转 升序数组之后查找
     * 1. 二分之后 肯定有一个是有顺序的,另一个 无序的
     *
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target) {
        int length = nums.length;
        if (length == 0) {
            return -1;
        }
        if (length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0, right = length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 若果left right 就是的话直接返回
            if (nums[left] == target) {
                return left;
            }
            if (nums[right] == target) {
                return right;
            }
            // 左边是升序
            // [5,6,1],[2,3,4]
            if (nums[0] < nums[mid]) {
                if (nums[0] < target && target < nums[mid]) {
                    // 目标值在左侧
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // 右边是升序
                if (nums[mid] < target && target < nums[length - 1]) {
                    // 目标值在右侧
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    ///------------------------ 61 ---------------------
    /**
     * 剑指 Offer 07. 重建二叉树
     * https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof/
     * https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof/solution/mian-shi-ti-07-zhong-jian-er-cha-shu-di-gui-fa-qin/
     * 输入某二叉树的前序遍历和中序遍历的结果,请构建该二叉树并返回其根节点。
     * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
     * 105. 从前序与中序遍历序列构造二叉树
     *
     *
     *
     *
     *               1
     *           2       5
     *         3    | 6   7
     * 比如  前序遍历 1,[2,3,4],[5,6,7]      根据 下面的 左节点 长度,可以拿到 234 左节点的前序
     *      中序遍历  [3,2,4],1,[6,5,7] 前面是 根的左节点, 后面是根的右节点      324 是 左节点的中序
     */
    /**
     * 根据前序遍历数组的 [preL, preR] 和 中序遍历数组的 [inL, inR] 重新组建二叉树
     *
     * @return 构建的新二叉树的根结点
     */
    @Test
    public void testdeduceTree() {
        deduceTree(new int[]{1, 2, 3, 4, 5, 6, 7}, new int[]{3, 2, 4, 1, 6, 5, 7});
    }

    private TreeNode deduceTree(int[] preorder, int[] inorder) {
        // 时间 N的平方 ，递归了N次，里面又有个for循环，所以是N方 ，空间 n
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        // 从in找左右
        int rootIndex = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (preorder[0] == inorder[i]) {
                rootIndex = i;
                break;
            }
        }
        int[] leftPre = Arrays.copyOfRange(preorder, 1, rootIndex + 1);
        int[] rightPre = Arrays.copyOfRange(preorder, rootIndex + 1, preorder.length);

        int[] leftIn = Arrays.copyOfRange(inorder, 0, rootIndex);
        int[] rightIn = Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length);
        System.out.println(rootIndex + "---" + leftIn.length + 1);
        TreeNode left = deduceTree(leftPre, leftIn);
        TreeNode right = deduceTree(rightPre, rightIn);
        root.left = left;
        root.right = right;
        return root;
    }

    private TreeNode buildTree(int preL, int preR,
                               int inL, int inR) {
        if (preL > preR || inL > inR) {
            return null;
        }
        // 构建的新二叉树的根结点一定是前序遍历数组的第 1 个元素
        int pivot = preorder[preL];
        TreeNode root = new TreeNode(pivot);

        int pivotIndex = dic.get(pivot);

        // 这一步得画草稿,计算边界的取值,必要时需要解方程,并不难
        root.left = buildTree(preL + 1, preL + (pivotIndex - inL), inL, pivotIndex - 1);
        root.right = buildTree(preL + (pivotIndex - inL) + 1, preR, pivotIndex + 1, inR);
        return root;
    }

    ;//保留的先序遍历,方便递归时依据索引查看先序遍历的值
    int[] preorder;
    //标记中序遍历
    HashMap<Integer, Integer> dic = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        for (int i = 0; i < inorder.length; i++) {
            //将中序遍历的值及索引放在map中,方便递归时获取左子树与右子树的数量及其根的索引
            dic.put(inorder[i], i);
        }
        //三个索引分别为
        //当前根的的索引
        //递归树的左边界,即数组左边界
        //递归树的右边界,即数组右边界
        return buildTree(0, preorder.length - 1, 0, inorder.length - 1);
//        return recur(0, 0, inorder.length - 1);
    }


    TreeNode recur(int root, int left, int right) {
        // 相等的话就是自己
        if (left > right) return null;                          // 递归终止
        //获取root节点
        TreeNode node = new TreeNode(preorder[root]);          // 建立根节点
        //获取在中序遍历中根节点所在索引,以方便获取左子树的数量
        int i = dic.get(preorder[root]);                       // 划分根节点、左子树、右子树
        //左子树的根的索引为先序中的根节点+1
        //递归左子树的左边界为原来的中序left
        //递归左子树的右边界为中序中的根节点索引-1
        node.left = recur(root + 1, left, i - 1);              // 开启左子树递归
        //右子树的根的索引为先序中的 当前根位置 + 左子树的数量 + 1
        //递归右子树的左边界为中序中当前根节点+1
        //递归右子树的右边界为中序中原来右子树的边界
        node.right = recur(root + (i - left) + 1, i + 1, right); // 开启右子树递归
        return node;                                           // 回溯返回根节点
    }

    ///------------------------ 62 ---------------------

    /**
     * lru
     * https://leetcode.cn/problems/lru-cache/solution/lruhuan-cun-ji-zhi-by-leetcode-solution/
     */
    public class LRUCache {
        class DLinkedNode {
            int key;
            int value;
            DLinkedNode prev;
            DLinkedNode next;

            public DLinkedNode() {
            }

            public DLinkedNode(int _key, int _value) {
                key = _key;
                value = _value;
            }
        }

        private HashMap<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
        private int size;
        private int capacity;
        private DLinkedNode head, tail;

        public LRUCache(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            // 使用伪头部和伪尾部节点
            head = new DLinkedNode();
            tail = new DLinkedNode();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                return -1;
            }
            // 如果 key 存在,先通过哈希表定位,再移到头部
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                // 如果 key 不存在,创建一个新的节点
                DLinkedNode newNode = new DLinkedNode(key, value);
                // 添加进哈希表
                cache.put(key, newNode);
                // 添加至双向链表的头部
                addToHead(newNode);
                ++size;
                if (size > capacity) {
                    // 如果超出容量,删除双向链表的尾部节点
                    DLinkedNode tail = removeTail();
                    // 删除哈希表中对应的项
                    cache.remove(tail.key);
                    --size;
                }
            } else {
                // 如果 key 存在,先通过哈希表定位,再修改 value,并移到头部
                node.value = value;
                moveToHead(node);
            }
        }

        // 把 node 放到 head的后面 1<-->2     把3   1<-->3<--->2
        private void addToHead(DLinkedNode node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        private void removeNode(DLinkedNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void moveToHead(DLinkedNode node) {
            removeNode(node);
            addToHead(node);
        }

        private DLinkedNode removeTail() {
            DLinkedNode res = tail.prev;
            removeNode(res);
            return res;
        }
    }

    ///------------------------ 63 ---------------------

    /**
     * 岛屿数量
     * https://leetcode.cn/problems/number-of-islands/
     *
     * @param grid 输入：grid = [
     *             ["1","1","1","1","0"],
     *             ["1","1","0","1","0"],
     *             ["1","1","0","0","0"],
     *             ["0","0","0","0","0"]
     *             ]
     *             输出：1
     * @return
     */
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 如果其实位置是 1,往下走
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;


    }

    void dfs(char[][] grid, int row, int column) {
        // 判断 base case
        if (!inArea(grid, row, column)) {
            return;
        }
        // 如果这个格子不是岛屿,直接返回
        if (grid[row][column] != '1') {
            return;
        }
        // 将格子标记为「已遍历过」
        grid[row][column] = '2';

        // 访问上、下、左、右四个相邻结点
        dfs(grid, row - 1, column);
        dfs(grid, row + 1, column);
        dfs(grid, row, column - 1);
        dfs(grid, row, column + 1);
    }

    /**
     * 岛屿最大面积
     * https://leetcode.cn/problems/max-area-of-island/
     *
     * @param grid
     * @return
     */
    int numSum = 0;

    int maxNum = 0;

    public int maxAreaOfIsland(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 如果其实位置是 1,往下走
                if (grid[i][j] == '1') {
                    dfs1(grid, i, j);
                    maxNum = Math.max(maxNum, numSum);
                    numSum = 0;
                }
            }
        }
        return count;


    }

    void dfs1(char[][] grid, int row, int column) {
        // 判断 base case
        if (!inArea(grid, row, column)) {
            return;
        }
        // 如果这个格子不是岛屿,直接返回
        if (grid[row][column] != '1') {
            return;
        }
        // 将格子标记为「已遍历过」
        grid[row][column] = '2';
        numSum++;

        // 访问上、下、左、右四个相邻结点
        dfs1(grid, row - 1, column);
        dfs1(grid, row + 1, column);
        dfs1(grid, row, column - 1);
        dfs1(grid, row, column + 1);
    }

    // 判断坐标 (r, c) 是否在网格中
    boolean inArea(char[][] grid, int row, int column) {
        return 0 <= row && row < grid.length
                && 0 <= column && column < grid[0].length;
    }


    @Test
    public void temp1() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        ListNode node4 = new ListNode(4);
        node3.next = node4;
        ListNode node5 = new ListNode(5);
        node4.next = node5;
        ListNode node6 = new ListNode(6);
        node5.next = node6;
        System.out.println(printListNode(node1));
        System.out.println(printListNode(reverseBetween(node1, 2, 5)));
    }
    ///------------------------ 63 ---------------------

    /**
     * 只反转中间的一段
     * 给你单链表的头指针 head 和两个整数left 和 right ,其中left <= right
     * 。请你反转从位置 left 到位置 right 的链表节点,返回 反转后的链表
     * 输入：head = [1,2,3,4,5], left = 2, right = 4
     * 输出：[1,4,3,2,5]
     *
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {

        // （1）初始化指针
        ListNode preHead = new ListNode(0);
        preHead.next = head;
        // [1,2,3,4,5] , left = 2, right = 4  ==》 [1,4,3,2,5]
        // 那么g是 1 ,p是 2

        // g是反转的前一个
        // p是要反转的第一个
        // （2）将 p 后面的元素删除,然后添加到 g 的后面。也即头插法。
        // 根据 m 和 n 重复步骤（2）
        ListNode g = preHead;
        ListNode p = preHead.next;

        // 将指针移到相应的位置
        for (int step = 0; step < left - 1; step++) {
            g = g.next;
            p = p.next;
        }

        // 头插法插入节点,也就是执行 right - left 次数
        for (int i = 0; i < right - left; i++) {
            //要移除的 比如测试 removed 是 3->4->5
            ListNode removed = p.next;
            // 删除 removed 节点
            p.next = p.next.next;
            // 再把这个节点插入到 g的后面
            removed.next = g.next;
            g.next = removed;
        }

        return preHead.next;
    }


    ///------------------------ 64 ---------------------

    /**
     * 给定一个二叉树的 根节点 root,想象自己站在它的右侧,按照从顶部到底部的顺序,返回从右侧所能看到的节点值。
     * 跟层次遍历一样,然后只是每一层的最后一个放到数组里面
     * https://leetcode.cn/problems/binary-tree-right-side-view/
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            linkedList.add(root);
        }
        while (!linkedList.isEmpty()) {
            int size = linkedList.size();
            for (int i = 0; i < size; i++) {
                TreeNode first = linkedList.removeFirst();
                if (first.left != null) {
                    linkedList.add(first.left);
                }
                if (first.right != null) {
                    linkedList.add(first.right);
                }
                if (i == size - 1) {
                    result.add(first.val);
                }

            }

        }

        return result;
    }

    ///------------------------ 65 ---------------------

    /**
     * https://leetcode.cn/problems/maximum-width-of-binary-tree/
     * 给你一棵二叉树的根节点 root ,返回树的 最大宽度 。
     * <p>
     * 树的 最大宽度 是所有层中最大的 宽度 。中间null也算
     * <p>
     * 当前结点如果是N,左节点就是2*N,右节点是 2*N+1
     * <p>
     * <p>
     * 下面就是二叉树的编号,比如下面的 比如  5 和  6 是null ,
     * 第一层最大是 1
     * 第二层最大是2,
     * 第三层 【4,null,null,7】= 7-4 +1= 4
     * \      1               2的0
     * \   2       3        2 1   3 1+1
     * 4   5   6   7     2*n 2n+1
     *
     * @param root
     * @return
     */
    public int widthOfBinaryTre1e(TreeNode root) {
        if (root == null) return 0;
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        // 这个用来记录编号,
        LinkedList<Integer> list = new LinkedList<>();
        linkedList.add(root);
        list.add(1);
        int res = 1;
        while (!linkedList.isEmpty()) {
            int size = linkedList.size();
            for (int i = 0; i < size; i++) {
                TreeNode first = linkedList.removeFirst();
                Integer curIndex = list.removeFirst();
                if (first.left != null) {
                    linkedList.add(first.left);
                    list.add(curIndex * 2);
                }
                if (first.right != null) {
                    linkedList.add(first.right);
                    list.add(curIndex * 2 + 1);
                }
            }
            // list 中 size 为 1 的情况下,宽度也为 1,没有必要计算。
            if (list.size() >= 2) {
                res = Math.max(res, list.getLast() - list.getFirst() + 1);
            }
        }

        return res;
    }

    ///------------------------ 66 ---------------------

    /**
     * 快速排序
     * 哨兵 j 在最右边
     * 哨兵 i 在最左边
     *
     * 1.在数组中选取一个基准（pivot） 任意元素,直接选第一个吧,
     * 2. 把所有的小于或者等于的数 分到数组左边
     * 3. 把 大于 基准的数 分到右边,可以从末端放
     * 4. 递归排序 privot 左边和右边的子数组
     *
     * 时间复杂度为 O(N*logN)；
     * 空间复杂度为 1
     */
    /**
     * 入口函数（递归方法）,算法的调用从这里开始。
     */
    @Test
    public void text() {
        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        quickSort(arr, 0, arr.length - 1);

    }

    public void quickSort(int[] arr, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }

        // 核心算法部分：分别介绍 双边指针（交换法）
        int pivotIndex = doublePointerSwap2(arr, startIndex, endIndex);

        // 用分界值下标区分出左右区间,进行递归调用
        quickSort(arr, startIndex, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, endIndex);

    }

    /**
     * 双边指针（交换法）
     * 思路：
     * 记录分界值 pivot,创建左右指针（记录下标）。
     * （分界值选择方式有：首元素,随机选取,三数取中法）
     * <p>
     * 首先从右向左找出比pivot小的数据,
     * 然后从左向右找出比pivot大的数据,
     * 左右指针数据交换,进入下次循环。
     * <p>
     * 结束循环后将当前指针数据与分界值互换,
     * 返回当前指针下标（即分界值下标）
     */
    private int doublePointerSwap2(int[] arr, int startIndex, int endIndex) {
        // 默认基准是 第一个
        int pivot = arr[startIndex];
        int leftPoint = startIndex;
        int rightPoint = endIndex;

        while (leftPoint < rightPoint) {
            // 从右向左找出比pivot小的数据
            while (leftPoint < rightPoint) {
                if (arr[rightPoint] > pivot) {
                    rightPoint--;
                } else {
                    break;
                }

            }
            // 从左向右找出比pivot大的数据
            while (leftPoint < rightPoint) {
                if (arr[leftPoint] <= pivot) {
                    leftPoint++;
                } else {
                    break;
                }

            }
            // 没有过界则交换
            if (leftPoint < rightPoint) {
                int temp = arr[leftPoint];
                arr[leftPoint] = arr[rightPoint];
                arr[rightPoint] = temp;
            }
        }
        // 最终将分界值与当前指针数据交换
        arr[startIndex] = arr[rightPoint];
        arr[rightPoint] = pivot;
        // 返回分界值所在下标
        return rightPoint;
    }

    ///------------------------ 65 ---------------------

    /**
     * 时间复杂度：O(N)，
     * 空间复杂度：O(1)
     * 数组中的第K个最大元素
     * 比如排完序之后，第k大的就是 nums[length-k]
     * 3，2，1，4，5  k=1 那么就是5
     * 第一次的基准是在 3 抠出来，那么就是 2，1，3，4，5 // 基准是2。
     */
    public int findKthLargest(int[] nums, int k) {
        int targetIndex = nums.length - k;
        int length = nums.length;
        int left = 0;
        int right = length - 1;
        while (right >= left) {
            int pivot = doublePointerSwap2(nums, left, right);
            if (pivot == targetIndex) {
                // 如果当前的 pivot 就等于 这个targetIndex 那么就是找到了
                return nums[targetIndex];
            } else if (pivot < targetIndex) {
                // 如果此时的pivot < targetIndex,从右边找，因为右边都是大于 nums[pivot]的
                left = pivot + 1;

            } else {
                right = pivot - 1;
            }
        }
        return -1;
    }

    // 优先级队列
    public int findKthLargest1(int[] nums, int k) {
        // PriorityQueue 优先级队列， 比如添加 3->1->2， 在里面存储的我是1->2->3,  1,先出来 peek 是拿，poll是弹出
        int len = nums.length;
        // 使用一个含有 k 个元素的最小堆，PriorityQueue 底层是动态数组，为了防止数组扩容产生消耗，可以先指定数组的长度
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // Java 里没有 heapify ，因此我们逐个将前 k 个元素添加到 minHeap 里
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }

        for (int i = k; i < len; i++) {
            // 看一眼，不拿出，因为有可能没有必要替换
            Integer topElement = minHeap.peek();
            // 只要当前遍历的元素比堆顶元素大，堆顶弹出，遍历的元素进去
            if (nums[i] > topElement) {
                // Java 没有 replace()，所以得先 poll() 出来，然后再放回去
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
        return minHeap.peek();
    }

    public int findKthLargest11(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            heap.add(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.peek();
    }


    ///------------------------ 67 ---------------------

    /**
     * 时间复杂度：O(nlogn)，其中 n 是链表的长度。
     * <p>
     * 空间复杂度：O(logn)，其中 n 是链表的长度。空间复杂度主要取决于递归调用的栈空间
     * <p>
     * 148. 排序链表
     * -1->5->3->4->0->6
     * 1。找到当前链表中点，并从中点将链表断开（以便在下次递归 cut 时，链表片段拥有正确边界）；
     * 如果长度是偶数的话，放到前一个 比如上面是 那么中间节点是 3。
     * 2。找到中点 slow 后，执行 slow.next = null 将链表切断
     * 3。对两个切开的链表继续排序
     */
    @Test
    public void sortList() {
        ListNode head = new ListNode(-1);
        ListNode node2 = new ListNode(5);
        head.next = node2;
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        ListNode node4 = new ListNode(4);
        node3.next = node4;
        ListNode node5 = new ListNode(0);
        node4.next = node5;
        ListNode node6 = new ListNode(6);
        node5.next = node6;
        sortList(head);


    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head.next;
        // 快慢指针找到 中间节点 ，如果是偶数，则中间节点在是
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // mid 就是 中间节点的下一个
        ListNode mid = slow.next;
        System.out.println("中间：" + slow.val);
        // 比如 -1->5->3->4->0->6，3是slow 切断下一个
        // 切断
        slow.next = null;
        // 排序左边
        ListNode list1 = sortList(head);
        // 排序右边
        ListNode list2 = sortList(mid);
        // 合并两个链表，这里最终会只有是最终是比较一个节点，慢慢的大了起来
        ListNode reslut = mergeTwoLists(list1, list2);

        return reslut;
    }

    // 合并两个有序的链表 ，
    // https://leetcode.cn/problems/merge-two-sorted-lists
    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }

    ///------------------------ 68 ---------------------

    /**
     * https://leetcode.cn/problems/merge-intervals/
     * [[1,3],[2,6],[8,10],[15,18]]
     * 合并成
     * [[1,6],[8,10],[15,18]]
     * 56。合并区间
     */
    @Test
    public void mergeRange() {
        int[] arr1 = new int[]{1, 3};
        int[] arr2 = new int[]{2, 6};
        int[] arr3 = new int[]{8, 10};
        int[] arr4 = new int[]{15, 18};
        int[][] demo = new int[][]{arr1, arr3, arr2, arr4};
        merge(demo);

    }

    public int[][] merge(int[][] intervals) {
        // 先按照区间起始位置排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] num1, int[] num2) {
                return num1[0] - num2[0];
            }
        });


        // 遍历区间
        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for (int[] interval : intervals) {

            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (idx == -1 || interval[0] > res[idx][1]) {
                // 放到下一个
                ++idx;
                res[idx] = interval;
            } else {
                // 反之将当前区间合并至结果数组的最后区间 更改 上一个的
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        for (int[] arr : res) {
            System.out.println(Arrays.toString(arr));
        }
        return Arrays.copyOf(res, idx + 1);
    }

    ///------------------------ 69 ---------------------

    /**
     * 删除排序链表中的重复元素 II
     * 给定一个已排序的链表的头 head
     *
     * @param head
     * @return 输入：head = [1,2,3,3,4,4,5]
     * 输出：[1,2,5]
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) return null;
        ListNode dummyHead = new ListNode(-101);
        ListNode pre = dummyHead;
        pre.next = head;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                int val = cur.val;
                while (cur != null && cur.val == val) {
                    cur = cur.next;
                }
                pre.next = cur;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return dummyHead.next;

    }

    ///------------------------ 70 ---------------------

    /**
     * 给你一个非负整数 x ，计算并返回 x 的 算术平方根
     * 输入：x = 8
     * 输出：2
     * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去
     * <p>
     * 时间复杂度：O(logx)，即为二分查找需要的次数。
     * <p>
     * 空间复杂度：O(1)。
     * 二分查找，
     *
     * @param x
     * @return
     */
    int result = -1;

    @Test
    public void textddd() {
        mySqrt1(0, 10, 10);
        System.out.println(result);
    }

    public int mySqrt(int x) {
        int left = 0, right = x, reslut = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if ((long) mid * mid <= x) {
                reslut = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return reslut;

    }


    public void mySqrt1(int left, int right, int x) {
        if (right < left) {
            return;
        }
        int mid = left + (right - left) / 2;
        if ((long) mid * mid <= x) {
            result = mid;
            mySqrt1(mid + 1, right, x);
        } else {
            mySqrt1(left, mid - 1, x);
        }
    }

    ///------------------------ 71 ---------------------

    /**
     * https://leetcode.cn/problems/longest-common-subsequence/
     * 1143. 最长公共子序列 , 顺序必须一样，并不是连续的
     * 输入：text1 = "abcde", text2 = "ace"
     * a b c d e
     * a 1 1 1 1 1
     * c 1 1 2 2 1
     * e 1 1 2 2 3
     * 输出：3
     * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
     * https://leetcode.cn/problems/longest-common-subsequence/solution/shi-pin-jiang-jie-shi-yong-dong-tai-gui-hua-qiu-ji/
     *
     * @param text1
     * @param text2 如果 text1.chatAt(i) == text2.chatAt(j)
     *              dp[i,j] = dp[i-1,j-1] + 1
     *              如果 text1.chatAt(i) != text2.chatAt(j)
     *              dp[i,j] = Math.max(dp[i-1,j],dp[i,j-1])
     * @return a
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int ans = -1;
        int text1Length = text1.length();
        int text2Length = text2.length();
        int dp[][] = new int[text1Length + 1][text2Length + 1];
        for (int i = 1; i < dp.length; i++) {
            char ch1 = text1.charAt(i - 1);
            for (int j = 1; j < dp[i].length; j++) {
                char ch2 = text2.charAt(j - 1);
                if (ch1 == ch2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
//  这下面两个是一样的 因为是不连续的所以 后面一定会比前面大，一只加上来的
        return dp[text1Length][text2Length];
//        return ans;
    }

    /**
     * 718. 最长重复子数组，且子数组在原数组中连续
     */
    public int findLength(int[] nums1, int[] nums2) {
        int ans = -1;
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    ans = Math.max(ans, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return ans;
        //这下面就不可以，这个因为是连续的，所以这里不会是下面的
//        return dp[nums1.length][nums2.length];


    }

    @Test
    public void compareV() {
        compareVersion("1.01", "1.001");

    }

    ///------------------------ 72 ---------------------

    /**
     * 165. 比较版本号
     * <p>
     * https://leetcode.cn/problems/compare-version-numbers/
     * <p>
     * 输入：version1 = "1.01", version2 = "1.001"
     * 输出：0
     * 解释：忽略前导零，"01" 和 "001" 都表示相同的整数 "1"
     * <p>
     * 输入：version1 = "0.1", version2 = "1.1"
     * 输出：-1
     * <p>
     * 输入：version1 = "1.1", version2 = "1.0"
     * 输出：1
     *
     * @param version1
     * @param version2
     * @return
     */
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        for (int i = 0; i < v1.length || i < v2.length; ++i) {
            int x = 0, y = 0;
            if (i < v1.length) {
                x = Integer.parseInt(v1[i]);
            }
            if (i < v2.length) {
                y = Integer.parseInt(v2[i]);
            }
            if (x > y) {
                return 1;
            }
            if (x < y) {
                return -1;
            }
        }
        return 0;
    }
    ///------------------------ 73 ---------------------

    /**
     * https://leetcode.cn/problems/minimum-window-substring/
     * 76. 最小覆盖子串
     * 输入：s = "ADOBECODEBANC", t = "ABC"
     * 输出："BANC"
     */
    public String minWindow(String test, String res) {

        // 把查找的字符串存到这个里面 key 是 chart ，value 是个数
        HashMap<Character, Integer> need = new HashMap<>();
        // 滑块 里面的 值
        HashMap<Character, Integer> window = new HashMap<>();

        // 先把 需要的存放在 need 中，并且存个数
        for (int i = 0; i < res.length(); i++) {
            char c = res.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        // 记录左右指针
        int left = 0;
        int right = -1;//先从-1 或者 0 开始，对应的 right++ 位置不一样

        int mineLenth = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = test.length();

        // 如果 右边边界 小于 大字符串的长度
        while (right < sLen) {
            // 右边边界++
            // 1.循环刚开始，那么直接移动右指针即可，不需要做多余判断
            right++;
            // 如果在right的chart 符合 要查找的串
            if (right < sLen && need.containsKey(test.charAt(right))) {
                // 添加到 滑块中
                window.put(test.charAt(right), window.getOrDefault(test.charAt(right), 0) + 1);
            }

            // 直到window符合要求的时候，就开始让left--
            // todo 1 是否匹配
            while (check(need, window) && left <= right) {

                // 算出来 ansL 和 ansR,计算结果,因为是最小的，所以都要比较一下
                // todo 2 计算结果
                if (right - left + 1 < mineLenth) {
                    mineLenth = right - left + 1;
                    ansL = left;
                    ansR = right + 1;
//                    System.out.println("---" + ansL + "---" + ansR);
                }

                // 如果 left 在 要查找的串中，就从滑块中 value 减去1
                if (need.containsKey(test.charAt(left))) {
                    window.put(test.charAt(left), window.getOrDefault(test.charAt(left), 0) - 1);
                }
                left++;// 在前面或者后面 取决于 初始化时 -1 还是 0
            }


        }
        return ansL == -1 ? "" : test.substring(ansL, ansR);
    }

    // 遍历 要查找的串，看看在 滑块中对应 的key的value ，是不是小于自己的个数，如果小于，证明滑块 不符合条件
    public boolean check(HashMap<Character, Integer> need, HashMap<Character, Integer> window) {
        Iterator iter = need.entrySet()
                .iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (window.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }

    ///------------------------ 73 ---------------------

    /**
     * 43. 字符串相乘
     * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
     *
     * @param num1
     * @param num2
     * @return 比如 1 2 3
     * 2 4
     * -----
     * 4 9 2
     * <p>
     * 1 2 3
     * 2
     * -----
     * 2 4 6 （0）
     * 然后 492 + 2460 变成两个大数相加
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        // 保存计算结果
        String res = "0";

        // num2 逐位与 num1 相乘
        // 第二个数
        for (int i = num2.length() - 1; i >= 0; i--) {
            // 乘以之后 往前进的值
            int add = 0;
            // 保存 num2 第i位数字与 num1 相乘的结果
            StringBuilder temp = new StringBuilder();
            // 补 0 根据是十位还是百位补充多少个0
            for (int j = 0; j < num2.length() - 1 - i; j++) {
                temp.append(0);
            }
            // chat转成int
            int n2 = num2.charAt(i) - '0';

            // num2 的第 i 位数字 n2 与 num1 相乘
            // 第一个数
            for (int j = num1.length() - 1; j >= 0; j--) {
                int n1 = num1.charAt(j) - '0';
                int product = n1 * n2 + add;
                temp.append(product % 10);
                add = product / 10;
            }
            // 不等于0 证明有进位 ，因为进位最多不超过1位的，所以这里直接加即可
            if (add != 0) {
                temp.append(add);
            }
            System.out.println(add);

            // 将当前结果与新计算的结果求和作为新的结果
            res = addStrings(res, temp.reverse()
                    .toString());
        }
        return res;
    }

    ///------------------------ 74 ---------------------

    /**
     * https://leetcode.cn/problems/min-stack/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-38/
     * 一个栈用来存储当前的数据，模拟栈
     * 一个栈用来存储，push进来的 比本栈上面小的值，那么这个栈由上往下 都是 递增的，所以最小的就是 这个栈peek的
     * <p>
     * 当pop的时候，pop出来的值和最小栈最上面的值一样的时候，把最小栈也pop
     * <p>
     * 最小栈
     */
    class MinStack {

        Stack<Integer> mMinStack;
        Stack<Integer> mCommonStack;

        public MinStack() {
            mMinStack = new Stack<>();
            mCommonStack = new Stack<>();
        }

        public void push(int val) {
            mCommonStack.push(val);
            if (mMinStack.isEmpty()) {
                mMinStack.push(val);
            } else {
                int peek = mMinStack.peek();
                if (peek >= val) {
                    mMinStack.push(val);
                }
            }

        }

        public void pop() {
            int pop = mCommonStack.pop();

            int top = mMinStack.peek();
            //等于的时候再出栈
            if (pop == top) {
                mMinStack.pop();
            }

        }

        public int top() {
            return mCommonStack.peek();
        }

        public int getMin() {
            return mMinStack.peek();
        }
    }

    ///------------------------ 75 ---------------------

    /**
     * 32. 最长有效括号
     * 输入：s = "(()"
     * 输出：2
     * 解释：最长有效括号子串是 "()"
     * <p>
     * 输入：s = "())"
     * 输出：2
     * 解释：最长有效括号子串是 "()"
     * <p>
     * 从左往右走
     * for循环 ，用 leftNum 表示 ( 个数，用 right 表示 ) 个数，
     * ----------当 leftNum == rightNum是 Math.max(res,2*left)
     * ----------当 rightNum > leftNum 的时候，证明已经不符合题意了，此时把 rightNum和leftNum = 0 ，从新开始数数
     * <p>
     * 上面的 不能找到 ((),
     * 所以同样的方式 从右往左走，找到最大值
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxlength;
    }

    public int longestValidParentheses1(String s) {
        int maxlength = 0;
        int index = 0;
        Stack<Character> stack = new Stack<>();
        while (index < s.length()) {
            if (s.charAt(index) == '(') {
                stack.push(s.charAt(index));
            } else {
                char pop = stack.pop();
                if (pop == ')') {
                    if (stack.isEmpty()) {

                    }
                }
            }
        }
        return maxlength;
    }

    ///------------------------ 76 ---------------------

    /**
     * 867. 转置矩阵,。主对角线反转矩阵，沿着 1 5 9 对折
     * 注意这里并不是正方形矩阵，所以用原地旋转 是不行的。所以重新创建一个数组
     * [1,2,3],
     * [4,5,6],
     * [7,8,9]
     * <p>
     * ==
     * <p>
     * 1 4 7
     * 2 5 8
     * 3 6 9
     * <p>
     * 1   2
     * 3   4
     * 5   6
     */
    public int[][] transpose(int[][] matrix) {
        int M = matrix.length;
        int N = matrix[0].length;
        int[][] res = new int[N][M];
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                res[j][i] = matrix[i][j];
            }
        }
        return res;


    }

    ///------------------------ 77 ---------------------


    /**
     * 48. 旋转图像
     * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
     * <p>
     * <p>
     * 1 2 3
     * 4 5 6
     * 7 8 9
     * <p>
     * 1。先沿着 159 反转，
     * <p>
     * 1 4 7
     * 2 5 8
     * 3 6 9
     * <p>
     * 2。再沿着 456 反转 这样就成了
     * <p>
     * 7 4 1
     * 8 5 2
     * 9 6 3
     */
    @Test
    public void rotateTest() {
        int[] arr1 = new int[]{1, 2, 3};
        int[] arr2 = new int[]{4, 5, 6};
        int[] arr3 = new int[]{7, 8, 9};
        int[][] demo = new int[][]{arr1, arr2, arr3};
        rotate(demo);
    }

    public void rotate(int[][] matrix) {
        int length = matrix.length;
        for (int[] demo : matrix) {
            System.out.println(Arrays.toString(demo));
        }
        System.out.println("-------");
        // 1。斜对角反转
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < i; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for (int[] demo : matrix) {
            System.out.println(Arrays.toString(demo));
        }

        System.out.println("-------");
        // 左右反转
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][length - 1 - j];
                matrix[i][length - 1 - j] = temp;

            }

        }
        for (int[] demo : matrix) {
            System.out.println(Arrays.toString(demo));
        }


    }


    ///------------------------ 78 ---------------------

    /**
     * 最大正方形 则最大的是 2*2
     * <p>
     * 0 1 1 0
     * 1 1 1 1
     * 0 1 0 1
     */
    public int maximalSquare(char[][] matrix) {
        // base condition
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return 0;

        int height = matrix.length;
        int width = matrix[0].length;
        int maxSide = 0;

        // 相当于已经预处理新增第一行、第一列均为0
        int[][] dp = new int[height + 1][width + 1];

        // dp[row + 1][col + 1] 是以  matrix[row][col] 这个为右下角的
        for (int row = 0; row < height + 1; row++) {
            for (int col = 0; col < width; col++) {
                if (matrix[row][col] == '1') {
                    dp[row + 1][col + 1] = Math.min(Math.min(dp[row + 1][col], dp[row][col + 1]), dp[row][col]) + 1;
                    maxSide = Math.max(maxSide, dp[row + 1][col + 1]);
                }
            }
        }
        return maxSide * maxSide;
    }


    ///------------------------ 79 ---------------------
    /**
     * 139. 单词拆分
     * dp[j] 表示第j个是否满足条件，如果第j个满足 则看 j-i 是否满足,如果j到i也满足，那么dp[i]就满足
     * 假如wordDict=["apple", "pen", "code"],s = "applepencode";
     * dp[8] = dp[5] + check("pen") s.subString(5,8)
     * dp[i] 表示字符串 s 前 i 个字符组成的字符串 s[0..i-1]
     * <p>
     * 两次for循环，第一层是 就是 i，第二层是 i 之前 看看是不是满足
     */
    public HashMap<String, Boolean> hash = new HashMap<>();

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];

        //方便check，构建一个哈希表
        for (String word : wordDict) {
            hash.put(word, true);
        }
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {

                if (dp[j] && check(s.substring(j, i))) {
                    //dp[j] 表示第j个是否满足条件，如果第j个满足 则看 j-i 是否满足,如果j到i也满足，那么dp[i]就满足
                    // 如果不满足这个条件继续往下找j
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    public boolean check(String s) {
        return hash.getOrDefault(s, false);
    }


    ///------------------------ 79 ---------------------

    /**
     * 这种做法不对。// 没通过
     * 剑指 Offer 36. 二叉搜索树与双向链表
     * 就是利用 Tree left 和 right 表示 双向链表的 前后指针
     * --- 1
     * - 2   3
     * <p>
     * 1 ⇆ 2 ⇆ 3 变成 1.right 是 2，2的left 是1
     * 根几点也需要和头节点连接起来， 比如 3.right = 1, 1.left = 3
     */
    public TreeNode treeToDoublyList(TreeNode root) {
        ArrayList<TreeNode> result = new ArrayList<>();
        traverseNode(root, result);
        for (int i = 0; i < result.size(); i++) {
            TreeNode node = result.get(i);
            if (i == 0) {
                node.left = result.get(result.size() - 1);
                node.right = result.get(i + 1);
            } else if (i == result.size() - 1) {
                node.right = result.get(0);
                node.left = result.get(i - 1);
            } else {
                node.right = result.get(i + 1);
                node.left = result.get(i - 1);

            }

        }
        return result.get(0);

    }

    public void traverseNode(TreeNode root, ArrayList<TreeNode> result) {
        if (root != null) {
            traverseNode(root.left, result);
            result.add(root);
            traverseNode(root.right, result);
        }
    }

    public static int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }
        int rowLength = matrix.length;
        int columnLength = matrix[0].length;

        int[] answer = new int[rowLength * columnLength];
        int count = rowLength + columnLength - 1;
        int m = 0;
        int n = 0;
        int answerIndex = 0;

        for (int i = 0; i < count; i++) {
            if (i % 2 == 0) {
                while (m >= 0 && n < columnLength) {
                    answer[answerIndex] = matrix[m][n];
                    answerIndex++;
                    m--;
                    n++;
                }
                if (n < columnLength) {
                    m++;
                } else {
                    m = m + 2;
                    n--;
                }
            } else {
                while (m < rowLength && n >= 0) {
                    answer[answerIndex] = matrix[m][n];
                    answerIndex++;
                    m++;
                    n--;
                }
                if (m < rowLength) {
                    n++;
                } else {
                    m--;
                    n = n + 2;
                }

            }
        }
        return answer;


    }


    ///------------------------ 80 ---------------------

    /**
     * 每日温度
     * 下一个更高温度出现在几天后
     * 输入: temperatures = [73,74,75,71,69,72,76,73]
     * ---------------坐标= [ 0, 1, 2, 3, 4, 5, 6, 7]
     * 输出: [1,1,4,2,1,1,0,0]
     * <p>
     * 单调递减栈
     * // 第一次 73[0]
     * // 压第二个 一看是 74 就不压进去 此时
     */
    public int[] dailyTemperatures(int[] temperatures) {
        // 用来记录坐标
        Stack<Integer> stack = new Stack<>();

        int[] result = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                result[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return result;

    }

    ///------------------------ 81 ---------------------

    /**
     * https://www.nowcoder.com/questionTerminal/16409dd00ab24a408ddd0c46e49ddcf8?answerType=1&f=discussion
     * <p>
     * 圆环上有 10 个点，编号 0~9 。从 0 出发，每次可以顺时针或逆时针走一格，请问一共走且仅走 n 步回到原点的方法有多少种。
     * 圆环回原点
     * [1]0-12共13个数构成一个环，从0出发，每次走1步，走n步回到0共有多少种走法（2020.09 字节跳动-广告-后端）
     * [2]0-8组成一个圆环，从0出发，每次可以逆时针和顺时针走，走n步能回到0有多少种情况（2020.09 字节跳动-people-后端）
     * [3]0~9的环，从0出发，N步后能否走回0。（2020.07 字节跳动-电商-后端）
     * [4]圆环回原点问题 (2020.08 字节跳动-飞书-后端)
     * <p>
     * 此题跟斐波拉契系数很像 第i步走到
     * dp[i][j] = dp[i-1][j-1] (i-1步走到j左边的方法数) + dp[i-1][j+1](i-1步走到j右边的方法数）
     * <p>
     * 数据范围： 由于答案可能会非常大，所以请对答案对 10^9+7
     */
    public int circle(int n) {

        int length = 10;
        int mod = (int) Math.pow(10, 9) + 7;
        int[][] dp = new int[n + 1][10];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < length; j++) {
                // 这里要求魔 length 有可能 ，j+1 会大于length
                dp[i][j] = (dp[i - 1][(j + 1) % length] + dp[i - 1][(j - 1 + length) % length]) % mod;
            }
        }
        return dp[n][0];
    }

    ///------------------------ 82 ---------------------

    /**
     * 删除二叉搜索树中的节点
     * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的key对应的节点，
     * 并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
     * <p>
     * - 如果目标节点大于当前节点值，则去右子树中删除；
     * - 如果目标节点小于当前节点值，则去左子树中删除；
     * - 如果目标节点就是当前节点，分为以下三种情况：
     * -- 其无左子：其右子顶替其位置，删除了该节点；
     * -- 其无右子：其左子顶替其位置，删除了该节点；
     * -- 其左右子节点都有：其左子树转移到其右子树的最左节点的左子树上，然后右子树顶替其位置，由此删除了该节点。
     *
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (key > root.val) {
            root.right = deleteNode(root.right, key); // 去右子树删除
        } else if (key < root.val) {
            root.left = deleteNode(root.left, key);  // 去左子树删除
        } else {  // 当前节点就是要删除的节点

            if (root.left == null) {
                // 情况1，欲删除节点无左子
                return root.right;
            } else if (root.right == null) {
                // 情况2，欲删除节点无右子
                return root.left;
            } else {
                // 情况3，欲删除节点左右子都有
                TreeNode node = root.right;
                while (node.left != null) {
                    // 寻找欲删除节点右子树的最左节点
                    node = node.left;
                }

                // 将欲删除节点的左子树成为其右子树的最左节点的左子树
                node.left = root.left;
                // 欲删除节点的右子顶替其位置，节点被删除
                root = root.right;
            }
        }
        return root;
    }

    ///------------------------ 83 ---------------------

    /**
     * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     * <p>
     * 114. 二叉树展开为链表
     */

    public void flatten(TreeNode root) {

        ArrayList<TreeNode> list = new ArrayList<>();
        dfsflatten(root, list);
        for (int i = 0; i < list.size(); i++) {
            TreeNode node = list.get(i);
            node.left = null;
            node.right = null;
            TreeNode right = null;
            if (i != list.size() - 1) {
                right = list.get(i + 1);
            }
            node.right = right;
        }
    }

    private void dfsflatten(TreeNode root, ArrayList<TreeNode> list) {
        if (root != null) {
            list.add(root);
            dfs(root.left);
            dfs(root.right);
        }
    }

    public void flatten1(TreeNode root) {
        if (root == null) {
            return;
        }
        //将根节点的左子树变成链表
        flatten1(root.left);
        //将根节点的右子树变成链表
        flatten1(root.right);
        TreeNode temp = root.right;
        //把树的右边换成左边的链表
        root.right = root.left;
        //记得要将左边置空
        root.left = null;
        //找到树的最右边的节点
        while (root.right != null) root = root.right;
        //把右边的链表接到刚才树的最右边的节点
        root.right = temp;
    }


    ///------------------------ 84 ---------------------

    /**
     * 124. 二叉树中的最大路径和
     * ---1
     * 2     3  则 2 ，1， 3 是最大值6
     * --：-1
     * -2     -3 则 -1 是最大值 -1
     */
    @Test
    public void maxPathSumTest() {
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        maxPathSum(node1);
    }

    int resultmaxPathSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfsmaxPathSum(root);
        return resultmaxPathSum;
    }

    // 函数功能：返回当前节点能为父亲提供的贡献，需要结合上面的图来看！
    private int dfsmaxPathSum(TreeNode root) {
        // 如果当前节点为叶子节点，那么对父亲贡献为 0
        if (root == null) return 0;
        // 如果不是叶子节点，计算当前节点的左右孩子对自身的贡献left和right
        int left = dfsmaxPathSum(root.left);
        int right = dfsmaxPathSum(root.right);
        // 更新最大值，就是当前节点的val 加上左右节点的贡献。
        resultmaxPathSum = Math.max(result, root.val + left + right);

        // 计算当前节点能为父亲提供的最大贡献，必须是把 val 加上！
        // 只能选一个分支，当前节点的最大值,上面已经把结果计算出来了
        int max = Math.max(root.val + left, root.val + right);
        // 如果当前节点小于0的话，直接返回0即可！
        System.out.println(max);
        return Math.max(max, 0);
    }

    ///------------------------ 85 ---------------------

    /**
     * 78. 子集
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     * 相当于二叉树，
     * ------------选1                    不选1
     * ------ 选2       不选2         选2        不选2
     * ----选3  不选3  选3   不选3   选3 不选3  选3   不选3
     * 时间复杂度：O(n 2 ^ n)
     * 空间复杂度：O(n)。临时数组 tt 的空间代价是 O(n)，递归时栈空间的代价为 O(n)。
     */
    @Test
    public void subsetsTest() {
        subsets(new int[]{1, 2, 3});
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> state = new ArrayList<>();
        back(res, state, nums, 0);
        return res;

    }

    public void back(List<List<Integer>> res, List<Integer> state, int[] nums, int n) {
        if (n == nums.length) {
            res.add(new ArrayList<>(state));
            for (int ddd : state) {
                System.out.print(ddd);
            }
            System.out.println("------- " + n);

        } else {
            // 选择对应的 前序遍历
            state.add(nums[n]);
            back(res, state, nums, n + 1);
            // 不选择对应的，相当于移除
            state.remove(state.size() - 1);
            back(res, state, nums, n + 1);

        }

    }


    ///------------------------ 87 ---------------------

    /**
     * 394. 字符串解码
     * 输入：s = "3[a]2[bc]"
     * 输出："aaabcbc"
     * 输入：s = "3[a2[c]]"
     * 输出："accaccacc"
     * 输入：s = "2[abc]3[cd]ef"
     * 输出："abcabccdcdcdef"
     */
    @Test
    public void testdecodeString() {
        System.out.println(decodeString("10[a2[d]]"));
    }

    public String decodeString(String s) {
        StringBuffer ans = new StringBuffer();
        Stack<Integer> multiStack = new Stack<>();
        Stack<StringBuffer> ansStack = new Stack<>();
        int multi = 0;
        for (char c : s.toCharArray()) {
            if ('0' <= c && '9' >= c) {
                // 是十进制数字 乘以10代表跟上一次的
                multi = multi * 10 + (c - '0');
            } else if (c == '[') {
                // 把 ans 进栈
                ansStack.add(ans);
                // 把 数字 进栈
                multiStack.add(multi);
                // 重新new 个
                ans = new StringBuffer();
                // 表示 数字的开始
                multi = 0;
            } else if (c == ']') {
                // 表示 是 ],然后就 把ans pop 出来
                StringBuffer ansTmp = ansStack.pop();
                // 把数字pop出来
                int tmp = multiStack.pop();
                // 根据数字 拼接
                for (int i = 0; i < tmp; i++) {
                    ansTmp.append(ans);
                }
                // 把ans 给 ansTmp ，因为[] 有可能嵌套
                ans = ansTmp;
            } else {
                // 如果是字母，直接 拼接到里面
                ans.append(c);

            }
        }
        return ans.toString();
    }
    //------------------------ 88 ---------------------

    /**
     * 14. 最长公共前缀
     * 输入：strs = ["flower","flow","flight"]
     * 输出："fl"
     */
    @Test
    public void testlongestCommonPrefix() {
        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        int length = strs[0].length();
        int count = strs.length;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < count; j++) {
                if (i < strs[j].length() && strs[j].charAt(i) == c) {
                    if (j == strs.length - 1) {
                        res.append(c);
                    }

                } else {
                    return res.toString();
                }
            }
        }
        return res.toString();

    }

    /**
     * 二分查找
     * 给定一个n个元素有序的（升序）整型数组nums 和一个目标值target ,写一个函数搜索nums中的 target,如果目标值存在返回下标,否则返回 -1。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/binary-search
     * 著作权归领扣网络所有。商业转载请联系官方授权,非商业转载请注明出处。
     */

    public int search(int[] nums, int target) {

        return searchRe(0, nums.length - 1, target, nums);
    }

    private int searchRe(int start, int end, int target, int[] nums) {
        if (end < start) {
            return -1;
        }

        int mid = (end + start) / 2;

        if (target < nums[mid]) {
            return searchRe(start, mid - 1, target, nums);
        } else if (target > nums[mid]) {
            return searchRe(mid + 1, end, target, nums);
        } else {
            return mid;
        }

    }
    //------------------------ 89 ---------------------

    /**
     * 162. 寻找峰值
     * 峰值元素是指其值严格大于左右相邻值的元素。肯定存在一个或者多个，找到一个即可
     * 你可以假设 nums[-1] = nums[n] = -∞
     * 输入：nums = [1,2,3,1]
     * 输出：index = 2
     * 解释：3 是峰值元素，你的函数应该返回其索引 2。
     * 三种情况
     * 单调递增
     * 单调递减
     * 先升后减
     */
    @Test
    public void testfindPeakElement() {
        System.out.println(findPeakElement(new int[]{5, 4, 3, 2, 1}));
    }

    public int findPeakElement(int[] nums) {
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            // 如果mid
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    @Test
    public void testsearch3() {
        search3(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}, 4);
    }

    /**
     * https://leetcode.cn/problems/binary-search/
     * 二分查找
     * <p>
     * 时间复杂度：O(logn)，其中 n 是数组的长度。
     * 空间 O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int search3(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (right >= left) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                System.out.println("找到: " + mid);
                return mid;
            }
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println("没有找到");
        return -1;
    }

    /**
     * 搜索旋转排序数组
     * 输入：nums = [4,5,6,7,0,1,2], target = 0
     * 输出：4
     * https://leetcode.cn/problems/search-in-rotated-sorted-array/
     * 时间复杂度 O(logn)，显然应该使用二分查找
     *
     * @param nums
     * @param target
     * @return
     */
    public int search4(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (right >= left) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                System.out.println("找到: " + mid);
                return mid;
            }
            if (nums[mid] >= nums[left]) {
                // left -> mid 是递增了
                if (target >= nums[left] && target < nums[mid]) {
                    // 在这个递增之间
                    right = mid - 1;
                } else {
                    // 不在这个区间
                    left = mid + 1;
                }
            } else {
                //  mid -> right 是递增了
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

        }
        return -1;
    }

    /**
     * 旋转数组最小值
     * 时间复杂度 O(logn)，显然应该使用二分查找
     * https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 如果mid
            if (nums[mid] < nums[right]) {
                // 证明  mid -》 right 递增
                right = mid;
            } else {
                left = mid + 1;
            }

        }
        return nums[left];
    }

    //------------------------ 90 ---------------------

    /**
     * 958. 二叉树的完全性检验
     * 在一个完全二叉树中，除了最后一个关卡外，所有关卡都是完全被填满的，
     * 并且最后一个关卡中的所有节点都是尽可能靠左的。它可以包含1到2h节点之间的最后一级 h 。
     * 链接：https://leetcode.cn/problems/check-completeness-of-a-binary-tree
     * <p>
     * 也就是层次遍历 null 后面不能有 非null 数据
     *
     * @return
     */
    public boolean isCompleteTree(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        boolean haveNull = false;
        list.add(root);
        while (!list.isEmpty()) {
            TreeNode temp = list.removeFirst();
            if (temp != null && haveNull) {
                return false;
            } else if (temp == null) {
                haveNull = true;
                continue;
            }
            list.add(temp.left);
            list.add(temp.right);
        }
        return true;
    }


    //------------------------ 91 ---------------------

    /**
     * 402. 移掉 K 位数字
     * 给你一个以字符串表示的非负整数 num 和一个整数 k ，
     * 移除这个数中的 k 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。
     * 123531这样「高位递增」的数，肯定不会想删高位，会尽量删低位。
     * 432135这样「高位递减」的数，会想干掉高位，直接让高位变小，效果好。
     * 如果前面中是0 那么不入栈
     * 如果循环了一遍 没有移除够k，那么直接从栈中的末尾移除，因为比栈顶大的都入栈了
     *
     * @return
     */
    @Test
    public void testremoveKdigits() {
//        removeKdigits("1432219", 3);
//        removeKdigits("10200", 1);
        removeKdigits("1234567890", 9);
    }

    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack();
        for (char c : num.toCharArray()) {
            while (k > 0 && !stack.isEmpty() && c < stack.peek()) {
                // 如果k>0 ,又不是空 且 栈顶大于当前的char
                stack.pop();
                k--;
            }
            if (c == '0' && stack.isEmpty()) {

            } else {
                stack.push(c);
            }
        }

        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }
        StringBuilder result = new StringBuilder();
        if (!stack.isEmpty()) {
            while (!stack.isEmpty()) {
                char c = stack.pop();
                result.append(c);
//                result.append(stack.pop);
            }
            result = result.reverse();
        } else {
            result.append("0");
        }

        return result.toString();


    }

    // ----------------92

    /**
     * 31. 下一个排列
     * https://leetcode.cn/problems/next-permutation/description/
     * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
     * 下一个是 132
     * 如果没有比如 321，就返回123，
     * <p>
     * 一直觉得排列的题目很有趣，终于想通了根据当前排列计算出下一个排列的方法，在这里记录一下。
     * 例如 2, 6, 3, 5, 4, 1 这个排列， 我们想要找到下一个刚好比他大的排列，
     * 于是可以从后往前看 我们先看后两位 4, 1 能否组成更大的排列，答案是不可以，
     * 同理 5, 4, 1也不可以 直到3, 5, 4, 1这个排列，因为 3 < 5，
     * 我们可以通过重新排列这一段数字，来得到下一个排列 因为我们需要使得新的排列尽量小，
     * 所以我们从后往前找第一个比3更大的数字，发现是4 然后，我们调换3和4的位置，得到4, 5, 3, 1这个数列
     * 因为我们需要使得新生成的数列尽量小，于是我们可以对5, 3, 1进行排序，可以发现在这个算法中，
     * 我们得到的末尾数字一定是倒序排列的，于是我们只需要把它反转即可 最终，
     * 我们得到了4, 1, 3, 5这个数列 完整的数列则是2, 6, 4, 1, 3, 5
     */
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        // 从后往前找到第一个逆序的数字
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;
        // 找不到逆序数字, 说明数组整体已经是倒序的, 直接返回最小字典序
        if (i < 0) {
            Arrays.sort(nums);
            return;
        }
        // 找到该逆序的数字后面比它大的最小数字
        int j = nums.length - 1;
        while (j > 0 && nums[j] <= nums[i]) j--;
        // 交换两个数的位置
        swap(nums, i, j);
        // 反转后面的元素(必然是有序的)
        reverse(nums, i + 1);
    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    void reverse(int[] nums, int i) {
        int start = i, end = nums.length - 1;
        while (start < end)
            swap(nums, start++, end--);
    }

    @Test
    public void testCon() {
        constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5});
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildNode(nums, 0, nums.length);

    }

    private TreeNode buildNode(int[] nums, int start, int end) {
        TreeNode root = null;
        if (end > start) {
            int maxIndex = getMax(nums, start, end);
            root = new TreeNode(nums[maxIndex]);
            root.left = buildNode(nums, start, maxIndex);
            root.right = buildNode(nums, maxIndex + 1, end);
        }
        return root;
    }

    private int getMax(int[] nums, int start, int end) {
        int max = Integer.MIN_VALUE;
        int index = -1;
        for (int i = start; i < end; i++) {
            if (nums[i] > max) {
                max = Math.max(max, nums[i]);
                index = i;
            }
        }
        return index;
    }


}










