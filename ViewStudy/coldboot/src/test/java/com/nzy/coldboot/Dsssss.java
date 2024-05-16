package com.nzy.coldboot;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author niezhiyang
 * since 2024/4/29
 */
public class Dsssss {
    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        moveJiOu(nums);
        System.out.println(Arrays.toString(nums));
    }

    //https://www.nowcoder.com/questionTerminal/beb5aa231adc45b2a5dcc5b62c93f593?u_atoken=50210515-9ecb-4717-bb2f-5105515b6fd4&u_asession=01lBEXJ5iJK-xqW6oYZGYBehv9UE9_diPO1VLmeJeQOZZAATrvape1CnvPG9A52NwvX0KNBwm7Lovlpxjd_P_q4JsKWYrT3W_NKPr8w6oU7K-Li7-QNHLulm_9hi6RWM_7MJtBx3S14qt35vRMTfjp8mBkFo3NEHBv0PZUm6pbxQU&u_asig=05TrrStJKDQyRL6Pe0d0BBudB2njKw7Ild18MC7W8uajnCmX7k-lOaZub8O9ZeALTgWuIgbONlBHRXu1-B12kifP4LHPjX5907K-txzQcH3UmpMabM_J-UKBMf6nWHl-5wjPPn-XeGXDreuo9MsIM9WLZnSPGDnr5S6-RvOtHKEgP9JS7q8ZD7Xtz2Ly-b0kmuyAKRFSVJkkdwVUnyHAIJzToa8Vt0r92plzbIg9M640pZXxwQHSsLQNDfkbILggi41YpiKJZCArXmgv6vSk2RSO3h9VXwMyh6PgyDIVSG1W84e5kNRA7V7_g0dyd2LP3xxPV46rxeunwwnCjSohRo2NjjroiW10CAo3omzWtvqz3EekJQaPUEiizeJrvIM-UVmWspDxyAEEo4kbsryBKb9Q&u_aref=6mGzVj%2F4f%2BRG0%2BwoVXwsnwXRSu4%3D
    public void moveJiOu(int[] nums) {
        //[1, 2, 3, 4, 5]
        //[1,3,2,4,5]
        //[1,3,5,4,2]
        int left = 0;
        int right = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[right] % 2 != 0) {
                int numRight = nums[right];
                for (int j = right; j > left; j--) {
                    nums[j] = nums[j - 1];
                }
                nums[left] = numRight;

                left++;
            }
            right++;
        }
    }

    // https://leetcode.cn/problems/container-with-most-water/
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int result = 0;
        while (right > left) {
            result = Math.max(Math.min(height[left], height[right]) * (right - left), result);
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return result;
    }

    /**
     * 爬楼梯
     * <p>
     * https://leetcode.cn/problems/climbing-stairs/
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        int pre = 1;
        int now = 2;
        for (int i = 3; i <= n; i++) {
            int temp = now;
            now = pre + now;
            pre = temp;
        }
        return now;
    }

    /**
     * 已经排好序的链表，删除排序链表中的重复元素
     * https://leetcode.cn/problems/remove-duplicates-from-sorted-list/
     */

    @Test
    public void testdeleteDuplicates() {
        ListNode head = new ListNode(1);
        ListNode head1 = new ListNode(2);
        head.next = head1;
        ListNode head2 = new ListNode(2);
        head1.next = head2;
        ListNode head3 = new ListNode(4);
        head2.next = head3;
        ListNode head4 = new ListNode(5);
        head3.next = head4;
        System.out.println("--------");
        System.out.println(printListNode(deleteDuplicates(head)));
        System.out.println("--------");
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode pre = new ListNode(-1);
        ListNode temp = pre;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                while (cur.val == cur.next.val) {
                    cur = cur.next;
                }
            } else {
                temp.next = cur;
                temp = temp.next;
                cur = cur.next;
            }

        }
        return pre.next;

    }

    /**
     * 反转链表
     * https://leetcode.cn/problems/reverse-linked-list/
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode temp = cur.next;
            // 当前的指向前一个
            cur.next = pre;

            // 替换
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    /**
     * 链表是否有环
     * https://leetcode.cn/problems/linked-list-cycle/
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            if (slow == fast) {
                return true;
            } else {
                slow = slow.next;
                fast = fast.next.next;
            }
        }
        return false;
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
        ListNode temp = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                // 找到这个交点，此时让
                while (temp != null) {
                    if (slow == temp) {
                        return temp;
                    }
                    temp = temp.next;
                    slow = slow.next;
                }

            }
        }
        return null;
    }

    /**
     * 两个链表的交点,
     * https://leetcode.cn/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lengthA = length(headA);
        int lengthB = length(headB);
        if (lengthA > lengthB) {
            for (int i = 0; i < lengthA - lengthB; i++) {
                headA = headA.next;
            }
        } else {
            for (int i = 0; i < lengthB - lengthA; i++) {
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

    private int length(ListNode headA) {
        int length = 0;
        ListNode temp = headA;
        while (temp != null) {
            temp = temp.next;
            length++;
        }
        return length;
    }

    /**
     * 链表倒数第K个节点
     * https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
     *
     * @param head
     * @param cnt
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int cnt) {
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < cnt; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 合并两个有序链表
     * https://leetcode.cn/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/
     *
     * @param node1
     * @param node2
     * @return
     */


    public ListNode mergeTwoLists(ListNode node1, ListNode node2) {
        // 哑节点
        ListNode resultPre = new ListNode(-1);
        ListNode temp = resultPre;
        while (node1 != null && node2 != null) {
            if (node1.val > node2.val) {
                temp.next = node2;
                node2 = node2.next;
            } else {
                temp.next = node1;
                node1 = node1.next;
            }
            temp = temp.next;
        }
        //
        if (node1 == null) {
            // 把 2 全部放进去
            temp.next = node2;
        } else {
            // 把 1 全部放进去
            temp.next = node1;
        }
        return resultPre.next;
    }

    /**
     * 链表两两交换
     * https://leetcode.cn/problems/swap-nodes-in-pairs/
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        // 1-2-3-4
        // 2-1-3-4
        // temp = 2-3-4
        ListNode temp = head.next;
        // tempNext = 3-4;
        ListNode tempNext = head.next.next;
        // 2-1
        temp.next = head;
        //           4->3
        head.next = swapPairs(tempNext);
        return temp;
    }

    /**
     * 25. K 个一组翻转链表
     * https://leetcode.cn/problems/reverse-nodes-in-k-group/
     *
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // 1->2->3->4->5->6
        if (head == null) {
            return null;
        }
        // temp 假如现在 4-5-6
        ListNode end = head;
        for (int i = 0; i < k - 1; i++) {
            end = end.next;
            if (end == null) {
                return head;
            }
        }
        ListNode nextListNode = end.next;
        // 3->2->1
        ListNode swap = reverseListNode(head, end);
        head.next = reverseKGroup(nextListNode, k);
        return swap;

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

    private ListNode swapPairsGroup(ListNode left, ListNode right) {
        ListNode cur = left;
        ListNode pre = null;
        while (cur != right) {
            ListNode temp = cur.next;
            cur.next = pre;

            pre = cur;
            cur = temp;
        }
        return pre;
    }

    ///------------------------ 10 ---------------------
    // 删除节点
    // https://leetcode.cn/problems/delete-middle-node-lcci/
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    // 给个头结点,然后删除某个节点val 是 val的
    // https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof/
    // 题目保证链表中节点的值互不相同
    public ListNode deleteNode(ListNode head, int val) {
        if (head.val == val) {
            return head.next;
        }
        ListNode cur = head.next;
        ListNode pre = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
                return head;
            }
            cur = cur.next;
            pre = pre.next;
        }
        return head;
    }

    /**
     * 给你一个链表,删除链表的倒数第 n 个结点,并且返回链表的头结点。
     * https://leetcode.cn/problems/SLwz0R/
     */

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode temp = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        if (fast == null) {
            // 如果是头结点,直接返回下一个就行
            return head.next;
        }
        while (fast != null) {
            fast = fast.next;
            temp = slow;
            slow = slow.next;

        }
        if (slow != null) {
            temp.next = slow.next;
        }
        return head;

    }

    /**
     * 二叉树前序遍历
     * <p>
     * https://leetcode.cn/problems/binary-tree-preorder-traversal/
     * 1
     * 2          3
     * 4     5   6      7
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

    /**
     * 层遍历二叉树,每一层放到数组中
     * https://leetcode.cn/problems/binary-tree-level-order-traversal/
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            ArrayList<Integer> temp = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode top = queue.removeFirst();
                temp.add(top.val);
                if (top.left != null) queue.add(top.left);

                if (top.right != null) queue.add(top.right);
            }
            result.add(temp);
        }
        return result;
    }

    //https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/
    // Z
    public List<List<Integer>> levelOrderZ(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            LinkedList<Integer> temp = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode top = queue.removeFirst();
                if (result.size() % 2 == 0) {
                    temp.addLast(top.val);
                } else {
                    temp.addFirst(top.val);
                }

                if (top.left != null) queue.add(top.left);

                if (top.right != null) queue.add(top.right);
            }
            result.add(temp);
        }
        return result;
    }

    public int[] levelOrder1(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        LinkedList<TreeNode> temp = new LinkedList<>();
        if (root != null) {
            temp.add(root);
        }
        while (!temp.isEmpty()) {
            TreeNode top = temp.removeFirst();
            result.add(top.val);
            if (top.left != null) temp.add(top.left);

            if (top.right != null) temp.add(top.right);
        }
        return new int[]{};
    }

    /**
     * 1
     * 2  3
     * 5
     * 257 二叉树的所有路径  ["1->2->5","1->3"]
     * https://leetcode.cn/problems/binary-tree-paths/
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        binary("", root, result);
        return result;
    }

    private void binary(String s, TreeNode treeNode, List<String> result) {
        String temp = "";
        if (!s.isEmpty()) {
            temp = s + "->" + treeNode.val;
        } else {
            temp = treeNode.val + "";
        }

        if (treeNode.left == null && treeNode.right == null) {
            result.add(temp);
        } else {
            if (treeNode.left != null) {
                binary(temp, treeNode.left, result);
            }
            if (treeNode.right != null) {
                binary(temp, treeNode.right, result);
            }
        }


    }

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

    }

    public List<List<Integer>> pathTarget(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        getPath(result, root, target, new ArrayList<Integer>());
        return result;
    }

    private void getPath(List<List<Integer>> result, TreeNode root, int target, ArrayList<Integer> integers) {
        if (root != null) {
            integers.add(root.val);
            int temp = target - root.val;
            if (temp == 0 && root.left == null && root.right == null) {
                result.add(new ArrayList<>(integers));
            } else {
                if (root.left != null) {
                    getPath(result, root.left, temp, integers);
                }
                if (root.right != null) {
                    getPath(result, root.right, temp, integers);
                }
            }
            integers.remove(integers.size() - 1);
        }
    }

    // https://leetcode.cn/problems/validate-binary-search-tree
    public boolean isValidBST(TreeNode root) {
        return isValid(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    private boolean isValid(TreeNode root, int maxValue, int minValue) {
        if (root == null) {
            return true;
        }
        if (root.val >= maxValue || root.val <= minValue) {
            return false;
        } else {
            return isValid(root.left, root.val, minValue) && isValid(root.right, maxValue, root.val);
        }
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

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        return Math.abs(leftDepth - rightDepth) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getDepth(root.left), getDepth(root.right)) + 1;

    }

    /**
     * https://leetcode.cn/problems/diameter-of-binary-tree/
     * 543. 二叉树的直径
     */

    int mAxd = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        getdeep(root);
        return mAxd;
    }

    private int getdeep(TreeNode root) {
        int leftDeep = getdeep(root.left);
        int rightDeep = getdeep(root.right);
        mAxd = Math.max(mAxd, rightDeep + leftDeep);
        return Math.max(leftDeep, rightDeep) + 1;
    }

    /**
     * 是否含有一个路径 的sum 是某个值
     *
     * @param root
     * @param sum
     * @return
     */
    boolean hasPath = false;

    public boolean hasPathSum(TreeNode root, int sum) {
        hasPath(root, sum);
        return hasPath;
    }

    private void hasPath(TreeNode root, int sum) {
        if (root != null) {
            sum = sum - root.val;
            if (root.left == null && root.right == null && sum == 0) {
                hasPath = true;
            } else {
                hasPath(root.left, sum);
                hasPath(root.right, sum);
            }
        }
    }

    /**
     * 二叉树反转
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

    // 是否是平衡二叉树 深度不能查过1
    public boolean checkSymmetricTree(TreeNode root) {
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
            int key = target - nums[i];
            if (map.containsKey(key)) {
                return new int[]{map.get(key), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

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
     * @return
     */
    @Test
    public void testDu() {
        int[] nums = new int[]{1, 1, 2};
        int length = removeDuplicates(nums);
        System.out.println(Arrays.toString(nums));
        System.out.println(length);
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length == 1 || nums.length == 0) {
            return nums.length;
        }
        int left = 0;
        int right = 1;
        while (right < nums.length) {
            if (nums[left] != nums[right]) {
                left++;
                nums[left] = nums[right];
            }
            right++;


        }
        return left + 1;
    }

    /**
     * https://leetcode.cn/problems/jump-game/
     * 给定一个非负整数数组 nums ,你最初位于数组的 第一个下标 。
     * <p>
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * <p>
     * 判断你是否能够到达最后一个下标。
     * 输入：nums = [2,3,1,1,4]
     * 输出：true
     * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int k = 0;
        for (int i = 0; i <= k; i++) {
            int temp = nums[i] + i;
            k = Math.max(k, temp);
            if (k >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * https://leetcode.cn/problems/generate-parentheses/
     * 数字 n 代表生成括号的对数,请你设计一个函数,用于能够生成所有可能的并且 有效的 括号组合。
     * 括号生成 n对 （）
     */
    @Test
    public void testgenerateParenthesis() {
        generateParenthesis(3);
    }

    public List<String> generateParenthesis(int n) {
        ArrayList<String> result = new ArrayList<>();
        generte(result, "", n, 0, 0);
        return result;
    }

    private void generte(ArrayList<String> result, String s, int n, int left, int right) {
        if (left == right && left == n) {
            result.add(s);
        } else {
            if (left < n) {
                generte(result, s + "(", n, left + 1, right);
            }
            if (right < left) {
                generte(result, s + ")", n, left, right + 1);
            }
        }
    }

    /**
     * B是否是A的子树
     *
     * @param A
     * @param B
     * @return https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof/solution/
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null) {
            return false;
        }
        if (B == null) {
            return false;
        }
        boolean isSame = isSub(A, B);
        return isSame || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean isSub(TreeNode a, TreeNode b) {
        if (b == null) {
            return true;
        }
        if (a == null) {
            return false;
        }
        return a.val == b.val && isSub(a.left, b.left) && isSub(a.right, b.right);
    }

    /**
     * 最大子数组和
     * nums = [-2,1,-3,4,-1,2,1,-5,4]
     * dp[i] 就是以i为结尾的 和 是多少
     * dp[0] = -2
     * dp[1] = Math.max(1,dp[0]+1) = 1
     * dp[2] = [1,-3]
     * dp[3] = [4]
     * dp[4] = [4,-1] = 3
     * dp[5] = [4,-1,2] = 3+2
     * dp[6] = [4,-1,2,1] =5+1
     * <p>
     * https://leetcode.cn/problems/maximum-subarray/
     */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int result = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    public int maxSubArray1(int[] nums) {
        int pre = nums[0];
        int cur = nums[0];
        int result = pre;
        for (int i = 1; i < nums.length; i++) {
            cur = Math.max(pre + nums[i], nums[i]);
            pre = cur;
            result = Math.max(result, cur);
        }
        return result;
    }

    /**
     * 实现 pow(x, n) ,即计算 x 的 n 次幂函数（即,xn）。
     * https://leetcode.cn/problems/powx-n/
     */


    @Test
    public void testPmy() {
        myPow(0.00001, 2147483647);
    }

    public double myPow(double x, int n) {
        if (n < 0) {
            return 1 / myPow1(x, n);
        } else {
            return myPow1(x, n);
        }
    }

    public double myPow1(double x, int n) {
        if (n == 0) {
            System.out.println("1.000000");
            return 1.0;
        }

        int temp = n % 2;
        if (temp == 0) {
            System.out.println("----" + n / 2 + "----" + n + "-----");
            return myPow1(x, n / 2) * myPow1(x, n / 2);
        } else {
            System.out.println("----" + n / 2 + "----" + n);
            return myPow1(x, n / 2) * myPow1(x, n / 2) * x;
        }
    }

    /**
     * 合并两个有序数组
     * https://leetcode.cn/problems/merge-sorted-array/
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    @Test
    public void textmerge() {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};
        merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int endM = m - 1;
        int endN = n - 1;

        while (endM > -1 || endN > -1) {
            int tempM = 0;
            if (endM <= -1) {
                tempM = Integer.MIN_VALUE;
            } else {
                tempM = nums1[endM];
            }

            int tempN = 0;
            if (endN <= -1) {
                tempN = Integer.MIN_VALUE;
            } else {
                tempN = nums2[endN];
            }
            if (tempM > tempN) {
                nums1[endM + endN + 1] = nums1[endM];
                endM--;

            } else {
                nums1[endM + endN + 1] = nums2[endN];
                endN--;

            }


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

    @Test
    public void testAdd() {
        String result = add("456", "77");
        System.out.println(result);
    }

    public String add(String num1, String num2) {
        int index1 = num1.length() - 1;
        int index2 = num2.length() - 1;
        StringBuilder result = new StringBuilder();
        int fat = 0;
        while (index1 > -1 || index2 > -1) {
            if (index1 == -1) {
                int temp = num2.charAt(index2) - '0' + fat;
                fat = temp / 10;
                result.append(temp % 10);
                index2--;
            } else if (index2 == -1) {
                int temp = num1.charAt(index1) - '0' + fat;
                fat = temp / 10;
                result.append(temp % 10);
                index1--;
            } else {
                char char1 = num1.charAt(index1);
                char char2 = num2.charAt(index2);
                int temp = char1 - '0' + char2 - '0' + fat;
                fat = temp / 10;
                result.append(temp % 10);
                index2--;
                index1--;
            }
        }
        if (fat != 0) {
            result.append('1');
        }
        return result.reverse()
                .toString();
    }

    /**
     * 把m个同样的苹果放在n个同样的盘子里,允许有的盘子空着不放,问有多少种不同的分法？(注：5,1,1和1,1,5是同一种分法）
     *
     * @param m
     * @param n
     * @return
     */
    public int getResult(int m, int n) {
        if (n == 1) {
            return 1;
        }
        if (n > m) {
            return getResult(m, m);
        } else {
            return getResult(m - n, n) + getResult(m, n - 1);
        }

    }

    /**
     * 给定一个字符串 s ,请你找出其中不含有重复字符的 最长子串 的长度。
     * 无重复字符的最长子串
     * 滑块
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc",所以其长度为 3。
     * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
     */
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int right = 0;
        int left = 0;
        int result = 0;
        while (right < s.length()) {
            char cIndex = s.charAt(right);
            if (!map.containsKey(cIndex)) {
                map.put(cIndex, right);
                right++;
                result = Math.max(right - left, result);
            } else {
                int temp = map.get(cIndex);
                // 存在这个index，证明已经重复了，
                // 把left 制定到这个index，并且把left到index的map数据移除
                for (int i = left; i <= temp; i++) {
                    map.remove(cIndex);
                }
                left = temp + 1;
            }
        }
        return result;
    }

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

    public boolean findNumberIn2DArray(int[][] plants, int target) {
        int i = plants.length - 1;
        int j = 0;
        while (i >= 0 && j < plants[0].length) {
            if (plants[i][j] > target) {
                i--;
            } else if (plants[i][j] < target) {
                j++;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 322 零钱兑换
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
     * min(11-1 11-2  11-5)
     * <p>
     * dp[12]
     * dp[1]= 1;
     * dp[2]=2;
     * dp[3]=2+1;
     * dp[4]=2+2;
     * dp[5]=1
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // 假如里面都是1，最大值就是aount+1，
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i < amount; i++) {
            for (int j = 0; j <= coins.length; j++) {
                if (i >= coins[j]) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j] + 1]);
                }
            }
        }
        if (dp[amount] == amount + 1) {
            return -1;
        }
        return dp[amount];
    }

    /**
     * 39. 组合总和
     * 无重复元素的数组
     * 输入：candidates = [2,3,6,7], target = 7
     * 输出：[[2,2,3],[7]]
     */
    @Test
    public void testCombinationSum() {
        combinationSum(new int[]{3, 5, 8}, 11);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < candidates.length; i++) {
            dfs(new ArrayList<Integer>(), result, candidates, target, i);
        }
        return result;
    }

    private void dfs(ArrayList<Integer> integers, ArrayList<List<Integer>> result, int[] candidates, int target, int startIndex) {
        for (int i = startIndex; i < candidates.length; i++) {
            target = target - candidates[i];
            integers.add(candidates[i]);
            if (target == 0) {
                result.add(new ArrayList<>(integers));
                System.out.println(Arrays.toString(integers.toArray()));
            } else if (target > 0) {
                dfs(new ArrayList<>(integers), result, candidates, target, i);
            }
        }
    }

    private void dfs2(ArrayList<Integer> integers, ArrayList<List<Integer>> result, int[] candidates, int target, int startIndex) {
        for (int i = startIndex; i < candidates.length; i++) {
            target = target - candidates[i];
            integers.add(candidates[i]);
            if (target == 0) {
                result.add(new ArrayList<>(integers));
                System.out.println(Arrays.toString(integers.toArray()));
            } else if (target > 0) {
                dfs(new ArrayList<>(integers), result, candidates, target, i);
            }
        }
    }

    /**
     * 518. 零钱兑换 II
     * 请你计算并返回可以凑成总金额的硬币组合数 个数
     * 输入：amount = 5, coins = [1, 2, 5]
     * 输出：4
     * 解释：有四种方式可以凑成总金额：
     * 输入：coins = [1, 2, 5], amount = 11
     * 输出：3
     * 解释：11 = 5 + 5 + 1
     *
     * dp[i] = dp[i-1]+
     */

    /**
     * 字符串压缩
     * 字符串压缩。利用字符重复出现的次数,编写一种方法,实现基本的字符串压缩功能。
     * 比如,字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短,则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/compress-string-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权,非商业转载请注明出处。
     */
    @Test
    public void testCOm() {
        compressString("aabcccccaaa");
    }

    public String compressString(String S) {
        if (S.length() == 0) {
            return S;
        }
        StringBuilder result = new StringBuilder();
        char temp = S.charAt(0);
        int fat = 1;
        for (int i = 1; i < S.length(); i++) {
            if (temp == S.charAt(i)) {
                fat++;
            } else {
                result.append(temp);
                result.append(fat);
                temp = S.charAt(i);
                fat = 1;
            }
        }
        result.append(temp);
        result.append(fat);
        System.out.println(result.toString());
        if (result.length() < S.length()) {
            return result.toString();
        }
        return S;
    }

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
//        char[] demo = {'a', 'a', 'b', 'b', 'c', 'c'};
        char[] demo = {'a', 'b', 'c'};
        compress(demo);
    }

    public int compress(char[] chars) {
        if (chars.length == 0) {
            return 0;
        }
        if (chars.length == 1) {
            return 1;
        }
        int left = 0;
        int right = 1;
        int num = 1;
        while (right < chars.length) {
            char cright = chars[right];
            char cLeft = chars[left];
            if (cright == cLeft) {
                num++;
            } else {
                // 复制
                if (num == 1) {
                    left++;
                } else {
                    for (int i = 0; i < (num + "").length(); i++) {
                        left++;
                        chars[left] = (num + "").charAt(i);
                    }
                    left++;
                }
                chars[left] = chars[right];
                num = 1;
            }
            right++;
        }
        if (num == 1) {
            left++;
        } else {
            for (int i = 0; i < (num + "").length(); i++) {
                left++;
                chars[left] = (num + "").charAt(i);
            }
            if (left < chars.length && right < chars.length) {
                chars[left] = chars[right];
            }

        }
        System.out.println(Arrays.toString(chars));
        return left;
    }

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
        int result = 0;
        for (int i = 1; i < s.length(); i++) {
            int length1 = longPa(i, i, s);
            int length2 = longPa(i - 1, i, s);
            result = Math.max(result, Math.max(length1, length2));
        }
        return "";
    }

    private int longPa(int left, int right, String s) {
        int result = 0;
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                result++;

            } else {
                break;
            }
        }
        return result;
    }

    public int maxProfit(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            int temp = prices[i] - prices[i - 1];
            if (temp > 0) {
                result = result + temp;
            }
        }
        return result;
    }

    /**
     * 最长连续递增序列
     * 给定一个未经排序的整数数组,找到最长且 连续递增的子序列,并返回该序列的长度。
     * dp[0]=1
     * dp[1]= if(3>1) dp[0]+1 else dp[0]
     * <p>
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
        int result = 0;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                count++;
            } else {
                count = 1;
            }
            result = Math.max(count, result);
        }
        return result;
    }

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
        permute(new int[]{1, 2, 3});
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dps(new ArrayList<Integer>(), result, nums);

        return result;

    }

    private void dps(ArrayList<Integer> path, List<List<Integer>> result, int[] nums) {
        if (nums.length == path.size()) {
            result.add(new ArrayList<>(path));
            System.out.println(Arrays.toString(path.toArray()));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (!path.contains(nums[i])) {
                    path.add(nums[i]);
                    dps(path, result, nums);
                    path.remove(path.size() - 1);
                }

            }
        }

    }

    /**
     * https://leetcode.cn/problems/maximum-product-subarray/
     * 给你一个整数数组 nums ,请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字）,并返回该子数组所对应的乘积。
     * 因为有负数,所以记录起来最大值 和 最小值
     * dp[i]=dp[i-1]
     */

    public int maxProduct(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        // 状态定义：以索引 i 结尾
        // 思考清楚一种特例： [2, -1 ,3]，前面乘起来是负数的话，倒不如另起炉灶
        int[] maxDp = new int[len];
        int[] minDp = new int[len];

        maxDp[0] = nums[0];
        minDp[0] = nums[0];

        for (int i = 1; i < len; i++) {
            if (nums[i] >= 0) {
                maxDp[i] = Math.max(nums[i], maxDp[i - 1] * nums[i]);
                minDp[i] = Math.min(nums[i], minDp[i - 1] * nums[i]);
            } else {
                maxDp[i] = Math.max(nums[i], minDp[i - 1] * nums[i]);
                minDp[i] = Math.min(nums[i], maxDp[i - 1] * nums[i]);
            }
        }

        int res = maxDp[0];
        for (int i = 1; i < len; i++) {
            res = Math.max(res, maxDp[i]);
        }
        return res;
    }

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
        ArrayList<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            while (right > left) {
                int temp = nums[i] + nums[left] + nums[right];
                if (temp > 0) {

                    right--;
                } else if (temp < 0) {

                    left++;
                } else {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);
                    while (right > left && nums[right - 1] == nums[right]) {
                        right--;
                    }
                    right--;
                    while (right > left && nums[left + 1] == nums[left]) {
                        left++;
                    }
                    left++;
                }
            }
        }
        return result;
    }

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
//        rotateRight(head, 2);
        System.out.println(printListNode(head));
//        System.out.println(printListNode(rota(head)));
        System.out.println(printListNode(rota(rota(head))));
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

    public ListNode rotateRight(ListNode head, int k) {
        ListNode first = head;
        int length = getlenth(first);
        int realK = k % length;
        for (int i = 0; i < realK; i++) {
            first = rota(first);
        }
        return first;
    }

    private int getlenth(ListNode first) {
        ListNode temp = first;
        int result = 0;
        while (temp != null) {
            temp = temp.next;
            result++;
        }
        return result;
    }

    public ListNode rota(ListNode head) {
        ListNode temp2 = head;
        ListNode temp = head;
        while (temp.next != null) {
            temp2 = temp;
            temp = temp.next;
        }
        temp.next = head;
        temp2.next = null;
        return temp;
    }

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
        return left + 1;
    }

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

    @Test
    public void testLongestConsecutive1() {
        longestConsecutive1(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1});
    }

    public int longestConsecutive1(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        int result = 1;
        for (int i = 0; i < nums.length; i++) {
            int temp = i;
            int tempResult = 1;
            System.out.println("-----------" + nums[temp]);
            System.out.println("-----------" + map.containsKey(nums[temp] + 1) + "------");
            int index = nums[temp];
            while (temp < nums.length && map.containsKey(index + 1)) {
                index++;
                tempResult++;
                result = Math.max(result, tempResult);
                System.out.println("1111-----------" + tempResult);
            }
        }
        System.out.println("-----------");
        System.out.println(result);
        System.out.println("-----------");
        return result;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 1. 要不在root左边
        // 2. 要不在右边
        // 3，如果在中间，那么root就是
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        } else if (right == null) {
            return left;
        } else {
            return right;
        }
    }

    /**
     * https://leetcode.cn/problems/spiral-matrix/
     * 螺旋矩阵 54
     * 顺时针打印矩阵
     * [1,2,3,4]
     * [1,2,3,4]
     * [1,2,3,4]
     * [1,2,3,4]
     *
     * @return
     */
    @Test
    public void testspiralOrder() {
        int[][] matrix = new int[][]{
                new int[]{1, 2, 3, 4},
                new int[]{1, 2, 3, 4},
                new int[]{1, 2, 3, 4},
                new int[]{1, 2, 3, 4}
        };
        spiralOrder(matrix);
    }

    public int[] spiralOrder(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<>();
        int left = 0;
        int top = 0;
        int right = matrix[0].length - 1;
        int bottom = matrix.length - 1;
        while (true) {
            while (left <= right) {
                result.add(matrix[top][left]);
                left++;
            }
            top++;
            System.out.println(Arrays.toString(result.toArray()));
            if (top >= bottom) {
                break;
            }
            while (top <= bottom) {
                result.add(matrix[top][right]);
                top++;
            }
            System.out.println(Arrays.toString(result.toArray()));
            right--;
            if (left >= right) {
                break;
            }
            while (left <= right) {
                result.add(matrix[bottom][right]);
                right--;
            }
            bottom--;
            if (top >= bottom) {
                break;
            }
            while (top <= bottom) {
                result.add(matrix[left][bottom]);
                bottom--;
            }
            left++;
            if (left >= right) {
                break;
            }
        }
        int[] resultTemp = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resultTemp[i] = result.get(i);
        }
        System.out.println(Arrays.toString(result.toArray()));
        return resultTemp;

    }

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
    @Test
    public void textMin() {
        minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3});
    }

    public int minSubArrayLen(int s, int[] nums) {
        int left = 0;
        int right = 0;
        int temp = 0;
        int result = Integer.MAX_VALUE;
        while (right < nums.length) {
            temp = temp + nums[right];
            while (temp >= s) {
                result = Math.min(result, right - left + 1);
                temp = temp - nums[left];
                left++;
            }
            right++;
        }
        System.out.println("-----------");
        System.out.println(result);
        System.out.println("-----------");
        if (result == Integer.MAX_VALUE) {
            return -1;
        }
        return result;
    }

    /**
     * 旋转 升序数组之后查找
     * 1. 二分之后 肯定有一个是有顺序的,另一个 无序的
     * <p>
     * <p>
     * <p>
     * [5,6,1],[2,3,4]
     *
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (right >= left) {
            int mid = (right + left) / 2;
            if (nums[0] == nums[mid]) {
                return mid;
            }
            if (nums[0] < nums[mid]) {
                // 0 - > mid 是生序
                if (nums[0] <= target && target < nums[mid]) {
                    right--;
                } else {
                    left++;
                }
            } else {
                // mid->right shengxu
                if (nums[mid] <= target && target < nums[right]) {
                    left++;
                } else {
                    right--;
                }

            }
        }
        return -1;
    }
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
     *         3   4 | 6   7
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
        System.out.println(rootIndex + "---" + (leftIn.length + 1));
        TreeNode left = deduceTree(leftPre, leftIn);
        TreeNode right = deduceTree(rightPre, rightIn);
        root.left = left;
        root.right = right;
        return root;
    }

    class LRUCache {


        class LRUNode {
            public int val;
            public int key;
            public LRUNode pre;
            public LRUNode next;
        }

        private int capacity;
        private LRUNode head = new LRUNode();
        private LRUNode tail = new LRUNode();
        private HashMap<Integer, LRUNode> map = new HashMap<>();

        public LRUCache(int capacity) {
            this.capacity = capacity;
            head.val = -1;
            tail.val = -1;
            head.next = tail;
            tail.pre = head;
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                LRUNode node = map.get(key);
                // 移动
                deleteThis(node);
                addHead(node);
                return node.val;
            }
            return -1;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                LRUNode node = map.get(key);
                node.val = value;
                deleteThis(node);
                addHead(node);
            } else {
                if (map.size() > capacity) {
                    // 移除尾部
                    deleteTail();
                }
                LRUNode node = new LRUNode();
                node.val = value;
                node.key = key;
                addHead(node);
                map.put(key, node);
            }
        }

        private void deleteTail() {
            LRUNode tailNode = tail.pre;
            tailNode.next = tail;
            tail.pre = tailNode.pre;
            map.remove(tail.key);
        }

        private void addHead(LRUNode node) {
            LRUNode headNext = head.next;
            head.next = node;
            headNext.pre = node;
            node.pre = head;
            node.next = headNext;
        }

        private void deleteThis(LRUNode node) {
            LRUNode pre = node.pre;
            pre.next = node.next;
            node.next = pre;
        }
    }

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

    public void testNum() {

    }


    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfsnumIslands(i, j, grid);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfsnumIslands(int i, int j, char[][] grid) {
        if (i < grid.length && j < grid[0].length && i >= 0 && j >= 0) {
            if (grid[i][j] == '1') {
                grid[i][j] = '0';
                dfsnumIslands(i - 1, j, grid);
                dfsnumIslands(i, j - 1, grid);
                dfsnumIslands(i, j + 1, grid);
                dfsnumIslands(i + 1, j, grid);
            }

        }
    }

    int maxAir = 0;
    int resultAir = 0;

    /**
     * 岛屿最大面积
     * https://leetcode.cn/problems/max-area-of-island/
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    maxAir = 1;
                    dfsnumIslandsMax(i, j, grid);
                    Math.max(maxAir, resultAir);
                }
            }
        }
        return resultAir;
    }

    private void dfsnumIslandsMax(int i, int j, int[][] grid) {
        if (i < grid.length && j < grid[0].length && i >= 0 && j >= 0) {
            if (grid[i][j] == 1) {
                grid[i][j] = 0;
                dfsnumIslandsMax(i - 1, j, grid);
                dfsnumIslandsMax(i, j - 1, grid);
                dfsnumIslandsMax(i, j + 1, grid);
                dfsnumIslandsMax(i + 1, j, grid);
                maxAir++;
            }

        }
    }

    /**
     * 给定一个二叉树的 根节点 root,想象自己站在它的右侧,按照从顶部到底部的顺序,返回从右侧所能看到的节点值。
     * https://leetcode.cn/problems/binary-tree-right-side-view/
     * * -----1
     * * --2      3
     * * 4   5  6   7
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        LinkedList<TreeNode> list = new LinkedList<>();
        if (root != null) {
            list.add(root);
        }
        while (!list.isEmpty()) {
            int length = list.size();
            for (int i = 0; i < length; i++) {
                TreeNode pop = list.removeFirst();
                if (i == length - 1) {
                    result.add(pop.val);
                }
                if (pop.left != null) {
                    list.add(pop.left);
                }
                if (pop.right != null) {
                    list.add(pop.right);
                }
            }
        }
        return result;
    }

    /**
     * *      * -----1
     * *      * --2      3
     * *      * 4   5  6   7
     */
    @Test
    public void testwith() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(123);
        TreeNode node7 = new TreeNode(65);
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        ArrayList<Integer> pre = new ArrayList<>();
        preorder(node1, pre);
        System.out.println("前序：" + Arrays.toString(pre.toArray()));
        widthOfBinaryTre1e(node1);
        ArrayList<Integer> pre2 = new ArrayList<>();
        preorder(node1, pre2);
        System.out.println("前序：" + Arrays.toString(pre2.toArray()));
    }

    public int widthOfBinaryTre1e(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        if (root != null) {
            root.val = 1;
            list.add(root);
        }
        int resultNum = 0;
        TreeNode start = new TreeNode(-1);
        while (!list.isEmpty()) {
            int length = list.size();
            for (int i = 0; i < length; i++) {
                TreeNode pop = list.removeFirst();
                if (i == 0) {
                    start = pop;
                }
                if (i == length - 1) {
                    int temp = pop.val - start.val;
                    resultNum = Math.max(resultNum, temp);
                }
                if (pop.left != null) {
                    pop.left.val = 2 * pop.val;
                    list.add(pop.left);
                }
                if (pop.right != null) {
                    pop.right.val = 2 * pop.val + 1;
                    list.add(pop.right);
                }
            }
        }
        System.out.println("-------");
        System.out.println(resultNum);
        System.out.println("-------");
        return resultNum;
    }

    /**
     * * 148. 排序链表
     * * -1->5->3->4->0->6
     *
     * @return
     */

    // 合并两个有序的链表 ，
    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode preResult = new ListNode(-1);
        ListNode temp = preResult;
        while (head1 != null && head2 != null) {
            if (head1.val > head2.val) {
                temp.next = head2;
                head2 = head2.next;
            } else {
                temp.next = head1;
                head1 = head1.next;
            }
            temp = temp.next;
        }
        if (head1 == null) {
            temp.next = head2;
        } else {
            temp.next = head1;
        }
        return preResult.next;
    }

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
        merge1(demo);

    }

    public int[][] merge(int[][] intervals) {

        ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (end >= intervals[i][0]) {
                if (end <= intervals[i][1]) {
                    end = intervals[i][1];
                }
            } else {
                System.out.println(Arrays.toString(new int[]{start, end}));
                ArrayList<Integer> list = new ArrayList<>();
                list.add(start);
                list.add(end);
                temp.add(list);
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        temp.add(list);

        int[][] result = new int[temp.size()][2];
        for (int i = 0; i < temp.size(); i++) {
            ArrayList<Integer> integer = temp.get(i);
            for (int j = 0; j < integer.size(); j++) {
                result[i][j] = integer.get(j);
            }
        }
        return result;
    }

    public int[][] merge1(int[][] intervals) {
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

    /**
     */
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


    }

    /**
     * https://leetcode.cn/problems/longest-common-subsequence/
     * 1143. 最长公共子序列 , 顺序必须一样，并不是连续的
     * 输入：text1 = "abcde", text2 = "ace"
     * 输出：3
     * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
     * https://leetcode.cn/problems/longest-common-subsequence/solution/shi-pin-jiang-jie-shi-yong-dong-tai-gui-hua-qiu-ji/
     * *      a b c d e
     * *   0  0 0 0 0 0
     * * a 0  1 1 1 1 1
     * * c 0  1 1 2 2 1
     * * e 0  1 1 2 2 3
     *
     * @param text1
     * @param text2 如果 text1.chatAt(i) == text2.chatAt(j)
     *              dp[i,j] = dp[i-1,j-1] + 1
     *              如果 text1.chatAt(i) != text2.chatAt(j)
     *              dp[i,j] = Math.max(dp[i-1,j],dp[i,j-1])
     * @return
     */
    @Test
    public void testlongestCommonSubsequence() {
        longestCommonSubsequence("abcde", "ace");
    }

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
//        return dp[text1Length][text2Length];
        return dp[text1.length()][text2.length()];
    }

    /**
     * https://leetcode.cn/problems/minimum-window-substring/
     * 76. 最小覆盖子串
     * 输入：s = "ADOBECODEBANC", t = "ABC"
     * 输出："BANC"
     */
    public String minWindow(String test, String res) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < res.length(); i++) {
            map.put(res.charAt(i), map.getOrDefault(res.charAt(i), 0) + 1);
        }
        int left = 0;
        int right = 0;
        HashMap<Character, Integer> temp = new HashMap<>();
        while (right < test.length()) {
            temp.put(test.charAt(right), map.getOrDefault(test.charAt(right), 0) + 1);
//            if (checkMap(map, temp)) {
//                // 如果满足 遍历left
//                temp.put(test.charAt(left), map.getOrDefault(test.charAt(left), 0) - 1);
//                left++;
//            } else {
//
//            }

        }
        return "";
    }

    // 大数相成

    /**
     * 823
     *  29
     *7407
     *
     *
     *
     * @param num1
     * @param num2
     * @return
     */
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
     * 上面的 不能找到 ((),
     * 所以同样的方式 从右往左走，找到最大值
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int result = 0;
        int index = 0;
        int leftNum = 0;
        int rightNum = 0;
        while (index < s.length()) {
            if (s.charAt(index) == '(') {
                leftNum++;
            } else {
                rightNum++;
                if (leftNum == rightNum) {

                }
            }
        }
        return 0;
    }

    /**
     * [[1,2,3],
     * [4,5,6]]
     * 14
     * 25
     * 36
     * 867. 转置矩阵,。主对角线反转矩阵，沿着 1 5 9 对折
     * 注意这里并不是正方形矩阵，所以用原地旋转 是不行的。所以重新创建一个数组
     */
    public int[][] transpose(int[][] matrix) {
        int[][] result = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result[i][j] = matrix[j][i];
            }
        }
        return result;
    }

    /**
     * * 1 2 3
     * * 4 5 6
     * * 7 8 9
     * 先折叠
     * 7 8 9
     * 4 5 6
     * 1 2 3
     * 再沿着对角线折叠
     * 7 4 1
     * 8 5 2
     * 9 6 3
     *
     * @param matrix
     */
    @Test
    public void testrotate() {
        int[][] ma = new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9}};
        rotate(ma);
    }

    public void rotate(int[][] matrix) {
        int[][] matrix1 = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix1[i][j] = matrix[matrix[0].length - 1 - i][j];
            }
        }
        for (int i = 0; i < matrix1.length; i++) {
            System.out.println(Arrays.toString(matrix1[i]));
        }
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = matrix1[j][i];
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }

    }

    /**
     * 最大正方形 则最大的是 2*2
     * <p>
     *
     *
     *   0 1 1 0
     *   1 1 1 1
     *   0 1 0 1
     */
    public int maximalSquare(char[][] matrix) {
        return 0;
    }

}
