package com.nzy.viewstudy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * @author niezhiyang
 * since 2024/1/22
 */
public class OneMonth {
    /**
     * https://leetcode.cn/problems/move-zeroes/
     * 给定一个数组 nums,编写一个函数将所有 0 移动到数组的末尾,同时保持非零元素的相对顺序。
     * [1,0,2,3,0.4]->[1,2,3,4,0,0]
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (nums[right] != 0) {
                int rightNum = nums[right];
                nums[right] = nums[left];
                nums[left] = rightNum;
                left++;
            }
            right++;
        }
    }

    @Test
    public void testMoveZeroes() {
        int[] nums = new int[]{1, 0, 2, 3, 0, 4};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 一个数组 把奇数移动到偶数的前面,前面都是奇数,后面都是偶数,并且顺讯位置不变
     * 牛客网
     * [1,2,3,4,5,6]->[1,3,5,2,4,6]
     * [1,2,3,4,5,6] left = 0 right = 0 交换后 left =1 right =1
     * [1,2,3,4,5,6] left =1 right = 2
     * [1,3,2,4,5,6]
     * https://www.nowcoder.com/questionTerminal/beb5aa231adc45b2a5dcc5b62c93f593?u_atoken=50210515-9ecb-4717-bb2f-5105515b6fd4&u_asession=01lBEXJ5iJK-xqW6oYZGYBehv9UE9_diPO1VLmeJeQOZZAATrvape1CnvPG9A52NwvX0KNBwm7Lovlpxjd_P_q4JsKWYrT3W_NKPr8w6oU7K-Li7-QNHLulm_9hi6RWM_7MJtBx3S14qt35vRMTfjp8mBkFo3NEHBv0PZUm6pbxQU&u_asig=05TrrStJKDQyRL6Pe0d0BBudB2njKw7Ild18MC7W8uajnCmX7k-lOaZub8O9ZeALTgWuIgbONlBHRXu1-B12kifP4LHPjX5907K-txzQcH3UmpMabM_J-UKBMf6nWHl-5wjPPn-XeGXDreuo9MsIM9WLZnSPGDnr5S6-RvOtHKEgP9JS7q8ZD7Xtz2Ly-b0kmuyAKRFSVJkkdwVUnyHAIJzToa8Vt0r92plzbIg9M640pZXxwQHSsLQNDfkbILggi41YpiKJZCArXmgv6vSk2RSO3h9VXwMyh6PgyDIVSG1W84e5kNRA7V7_g0dyd2LP3xxPV46rxeunwwnCjSohRo2NjjroiW10CAo3omzWtvqz3EekJQaPUEiizeJrvIM-UVmWspDxyAEEo4kbsryBKb9Q&u_aref=6mGzVj%2F4f%2BRG0%2BwoVXwsnwXRSu4%3D
     */
    @Test
    public void temp() {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6};
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (nums[right] % 2 != 0) {
                int temp = nums[right];
                // 移动
                for (int i = right; i > left; i--) {
                    nums[i] = nums[i - 1];
                }
                nums[left] = temp;
                left++;
            }
            right++;
        }
        System.out.println(Arrays.toString(nums));
    }

    /* 盛最多水的容器
     * 11. 盛最多水的容器
     * https://leetcode.cn/problems/container-with-most-water/
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while (right > left) {
            max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return max;
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
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int pre = 1;
        int now = 2;
        for (int i = 3; i <= n; i++) {
            int temp = pre + now;
            pre = now;
            now = temp;
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
            ListNode temp = now.next;
            now.next = pre;

            // 交换
            pre = now;
            now = temp;
        }
        return pre;
    }

    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
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
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                // 表明有
                // 如果有 再走N，如果相等
                while (temp != slow) {
                    temp = temp.next;
                    slow = slow.next;
                }
                return temp;
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
        int lengthA = getLength(headA);
        int lengthB = getLength(headB);
        if (lengthA > lengthB) {
            for (int i = 0; i < (lengthA - lengthB); i++) {
                headA = headA.next;
            }
            while (headA != headB && headA != null) {
                headA = headA.next;
                headB = headB.next;
            }
            return headA;
        } else {
            for (int i = 0; i < (lengthB - lengthA); i++) {
                headB = headB.next;
            }
            while (headA != headB && headA != null) {
                headA = headA.next;
                headB = headB.next;
            }
            return headA;
        }
    }

    private int getLength(ListNode headA) {
        ListNode temp = headA;
        int length = 0;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 合并两个有序链表
     * https://leetcode.cn/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(-1);
        ListNode temp = result;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                temp.next = l2;
                l2 = l2.next;
            } else {
                temp.next = l1;
                l1 = l1.next;
            }
            temp = temp.next;
        }
        if (l1 == null) {
            temp.next = l2;
        } else {
            temp.next = l1;
        }
        return result.next;

    }

    /* 链表两两交换
     * https://leetcode.cn/problems/swap-nodes-in-pairs/
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 1->2->3->4
        // 2->1  4->3
        ListNode temp = head.next;
        head.next = swapPairs(temp.next);
        temp.next = head;
        return temp;
    }

    /**
     * 25. K 个一组翻转链表
     * https://leetcode.cn/problems/reverse-nodes-in-k-group/
     * 1->2->3->4->5->6
     *
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head;
        for (int i = 0; i < k; i++) {
            if (temp == null) {
                return head;
            }
            temp = temp.next;
        }
        ListNode revert = reverseList(head, temp);
        temp.next = reverseKGroup(temp.next, k);

        return revert;
    }

    private ListNode reverseList(ListNode head, ListNode temp) {
        return null;
    }

    /**
     * https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof/
     * 题目保证链表中节点的值互不相同
     *
     * @param head
     * @param val
     * @return
     */
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

    /**
     * 前序遍历 中序遍历 后续遍历
     * 二叉树遍历方式
     * -----1
     * --2      3
     * 4   5  6   7
     */
    @Test
    public void testOrderTree() {
        // 前 1->2->4->5->3->6->7
        // 中 4->2->5->1->6->3->7
        // 后 4-5-2- 6-7-3-1
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
        printQian(node1);
        System.out.println("---------");
        printZhong(node1);
        System.out.println("---------");
        printHow(node1);
        System.out.println("---------");
        System.out.println(Arrays.toString(levelOrder1(node1)));
        binaryTreePaths(node1);
        System.out.println("deeplengh = " + getTreeNodeLength(node1));
    }

    private void printQian(TreeNode node1) {
        if (node1 != null) {
            System.out.println(node1.val);
            printQian(node1.left);
            printQian(node1.right);
        }
    }

    private void printZhong(TreeNode node1) {
        if (node1 != null) {
            printZhong(node1.left);
            System.out.println(node1.val);
            printZhong(node1.right);
        }
    }

    private void printHow(TreeNode node1) {
        if (node1 != null) {
            printHow(node1.left);
            printHow(node1.right);
            System.out.println(node1.val);
        }
    }

    /**
     * 层遍历二叉树,每一层放到数组中
     * https://leetcode.cn/problems/binary-tree-level-order-traversal/
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            // 因为下面要添加，所以不能
            int size = queue.size();
            ArrayList<Integer> tempList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.removeFirst();
                tempList.add(temp.val);
                if (temp.left != null) {
                    queue.addLast(temp.left);
                }
                if (temp.right != null) {
                    queue.addLast(temp.right);
                }
            }
            result.add(tempList);
        }
        return result;
    }

    /**
     * 层遍历二叉树,Z字形,每一层放到数组中
     * https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/
     * 二叉树的锯齿形层序遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            // 因为下面要添加，所以不能
            int size = queue.size();
            LinkedList<Integer> tempList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.removeFirst();
                if (result.size() % 2 == 0) {
                    tempList.addLast(temp.val);
                } else {
                    tempList.addFirst(temp.val);
                }
                if (temp.left != null) {
                    queue.addLast(temp.left);
                }
                if (temp.right != null) {
                    queue.addLast(temp.right);
                }
            }
            result.add(tempList);
        }
        return result;
    }

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

    /**
     * 257 二叉树的所有路径  ["1->2->5","1->3"]
     * https://leetcode.cn/problems/binary-tree-paths/
     *
     * @param root
     * @return
     */

    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<String> result = new ArrayList<>();
        binaryTree(result, root, "");
        System.out.println(Arrays.toString(result.toArray()));
        return result;
    }

    private void binaryTree(ArrayList<String> result, TreeNode root, String path) {
        if (root != null) {
            if (path.equals("")) {
                path = root.val + "";
            } else {
                path = path + "->" + root.val;
            }

            if (root.left != null) {
                binaryTree(result, root.left, path);
            }

            if (root.right != null) {
                binaryTree(result, root.right, path);
            }
            if (root.right == null && root.left == null) {
                result.add(path);
            }

        }
    }

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
        if (root == null) {
            return true;
        }
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.val > max || root.val < min) {
            return false;
        }
        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }

    /**
     * isBalanced
     * https://leetcode.cn/problems/ping-heng-er-cha-shu-lcof/description/
     * 输入一棵二叉树的根节点,判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1,那么它就是一棵平衡二叉树
     */

    public boolean isBalanced(TreeNode root) {
        int lengthLeft = getTreeNodeLength(root.left);
        int lengthRight = getTreeNodeLength(root.right);
        return ((lengthRight - lengthLeft) >= -1 && (lengthRight - lengthLeft) <= 1) && isBalanced(root.left) && isBalanced(root.right);
    }

    private int getTreeNodeLength(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getTreeNodeLength(root.left), getTreeNodeLength(root.right)) + 1;
    }

    /**
     * https://leetcode.cn/problems/diameter-of-binary-tree/
     * 543. 二叉树的直径
     */

    int maxd = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return maxd;
    }

    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = depth(root.left);
        int right = depth(root.right);
        maxd = Math.max(maxd, left + right);
        return Math.max(left, right) + 1;
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
        int[] result = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp)) {
                result[0] = i;
                result[1] = map.get(temp);
                return result;
            } else {
                map.put(nums[i], i);
            }
        }
        return result;
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
    public void testMoveZeroes11() {
        int[] nums = new int[]{1, 2, 2, 3, 3, 4};
        System.out.println(removeDuplicates(nums));
        int[] nums1 = new int[]{2, 3, 1, 1, 4};
        canJump(nums1);
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (nums[right] == nums[left]) {
                right++;
            } else {
                left++;
                nums[left] = nums[right];
            }
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
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
//        int[] nums1 = new int[]{2, 3, 1, 1, 4};
        System.out.println("--------------");
        int maxStep = nums[0];
        for (int i = 0; i <= maxStep; i++) {
            maxStep = Math.max(maxStep, nums[i] + i);
            System.out.println(maxStep);
            if (maxStep >= nums.length) {
                return true;
            }
        }
        return false;
    }

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
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(' || chars[i] == '{' || chars[i] == '[') {
                stack.push(chars[i]);
            } else if (chars[i] == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                char pop = stack.pop();
                if (pop == '(') {
                } else {
                    return false;
                }
            } else if (chars[i] == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                char pop = stack.pop();
                if (pop == '[') {
                } else {
                    return false;
                }
            } else if (chars[i] == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                char pop = stack.pop();
                if (pop == '{') {
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

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
        generateParent(0, 0, n, "", result);
        return result;
    }

    private void generateParent(int left, int right, int n, String s, List<String> result) {
        if (left == right && left == n) {
            result.add(s);
        }
        //
        if (left < n) {
            generateParent(left + 1, right, n, s + "(", result);
        }
        if (left > right && left <= n) {
            generateParent(left, right + 1, n, s + ")", result);
        }

    }

    /**
     * 给你一个字符串数组,请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     * * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
     * * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap();
        for (int i = 0; i < strs.length; i++) {
            char[] arr = strs[i].toCharArray();
            Arrays.sort(arr);
            String temp = new String(arr);
            List<String> list = map.getOrDefault(temp, new ArrayList<String>());
            list.add(strs[i]);
            map.put(temp, list);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * B是否是A的子树
     *
     * @param A
     * @param B
     * @return https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof/solution/
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        boolean left = isSubStructure(A.left, B);

        boolean right = isSubStructure(A.right, B);

        return (A.val == B.val) && isSubStructure(A.left, B.left) && isSubStructure(A.right, B.right);


    }

    /**
     * 有效的字母异位词
     *
     * @param s
     * @param t
     * @return
     */
    @Test
    public void testIs() {
        isAnagram("anagram", "nagaram");
    }

    public boolean isAnagram(String s, String t) {
        if (s.equals(t)) {
            return false;
        }
        HashMap<Character, Integer> mapS = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int num = mapS.getOrDefault(s.charAt(i), 0) + 1;
            mapS.put(s.charAt(i), num);
        }
        System.out.println(mapS);
        for (int i = 0; i < t.length(); i++) {
            int num = mapS.getOrDefault(t.charAt(i), 0) - 1;
            mapS.put(t.charAt(i), num);

        }

        Set<Character> set = mapS.keySet();
        for (Character chars : set) {
            if (mapS.get(chars) != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 不同路径
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * https://leetcode.cn/problems/unique-paths/
     */
    @Test
    public void testuniquePaths() {
        uniquePaths(3, 7);
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][n] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
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
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] != 1) {
                dp[i][0] = 1;
            } else {
                // 因为有障碍物，所以后面都是0
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] != 1) {
                dp[0][i] = 1;
            } else {
                // 因为有障碍物，所以后面都是0
                break;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * https://leetcode.cn/problems/minimum-path-sum/
     *
     * @param grid
     * @return
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

    /**
     * 120：三角形最小路径和
     * https://leetcode.cn/problems/triangle/
     * 2
     * 3 4
     * 6 5 7
     * 4 1 8 3
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        int n = triangle.get(0)
                .size();
        int[][] dp = new int[m][n];
        dp[0][0] = triangle.get(0)
                .get(0);
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + triangle.get(i)
                    .get(0);
        }
        int result = 0;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][i], dp[i][j - 1]) + triangle.get(i)
                        .get(j);
                if (i == m - 1) {
                    result = Math.min(result, dp[i][j]);
                }
            }
        }
        return result;

    }

    @Test
    public void testRob() {
        rob3(new int[]{1, 2});
    }

    /**
     * 打家劫舍
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

    /**
     * 打家劫舍 假如是圆形，也就是第一个 和 最后一个不能同时透
     * https://leetcode.cn/problems/house-robber/
     */
    @Test
    public void testRob3() {
        System.out.println(rob3(new int[]{1, 2, 1, 1}));
    }

    public int rob3(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] nums1 = new int[nums.length - 1];
        int[] nums2 = new int[nums.length - 1];
        for (int i = 0; i < nums.length; i++) {
            if (i != 0) {
                nums1[i - 1] = nums[i];
            }
            if (i != nums.length - 1) {
                nums2[i] = nums[i];
            }
        }
        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
        System.out.println(rob1(nums1));
        System.out.println(rob1(nums2));

        return Math.max(rob1(nums1), rob1(nums2));
    }

    public int rob1(int[] nums) {
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

    /**
     * 最大子数组和
     * https://leetcode.cn/problems/maximum-subarray/
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }
        return dp[nums.length - 1];
    }

    /**
     * 实现 pow(x, n) ,即计算 x 的 n 次幂函数（即,xn）。
     * https://leetcode.cn/problems/powx-n/
     */
    public double myPow(double x, int n) {
        if (n >= 0) {
            return myPow2(x, n);
        } else {

            return myPow(-1 / x, 1 / n);
        }

    }

    private double myPow2(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        int temp = n % 2;
        double next = myPow(x, n / 2);
        if (temp != 0) {
            return next * next * x;
        } else {
            return next * next;
        }
    }

    @Test
    public void testMerge() {
        merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);
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
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int left = m;
        int right = n;
        while (left > 0 && right > 0) {
            if (nums2[right - 1] > nums1[left - 1]) {
                nums1[left + right - 1] = nums2[right - 1];
                right--;
            } else {
                nums1[left + right - 1] = nums1[left - 1];
                left--;
            }
        }
        // right 走完了
        if (right > -1) {
            // 证明right的没走完
            for (int i = 0; i < right; i++) {
                nums1[i] = nums2[i];
            }
        } else {
            // left 没走完，不用管
        }
    }

    /**
     * 大数相加
     * 输入：num1 = "11", num2 = "123"
     * * 输出："134"
     *
     * @param num1
     * @param num2
     * @return
     */

    @Test
    public void testAdd() {
        add("456", "77");
    }

    public String add(String num1, String num2) {
        int size1 = num1.length() - 1;
        int size2 = num2.length() - 1;
        StringBuilder result = new StringBuilder();
        int bat = 0;
        while (size1 > -1 || size2 > -1) {
            int numSize1 = 0;
            if (size1 > -1) {
                numSize1 = num1.charAt(size1) - '0';
            }
            int numSize2 = 0;
            if (size2 > -1) {
                numSize2 = num2.charAt(size2) - '0';
            }

            int cur = numSize1 + numSize2 + bat;

            result.append(cur % 10);
            bat = cur / 10;
            size1--;
            size2--;


        }
        if (bat != 0) {
            result.append("1");
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
    public int getResult(int m, int n) {//m个苹果放在n个盘子中共有几种方法
        if (n == 1) {
            return 1;
        }
        // 有一个不放
        return getResult(m, n - 1) + getResult(m - 1, n - 1);
    }
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
        lengthOfLongestSubstring("abcabcbb");
    }

    // 其他的一些滑动窗口
    // https://leetcode.cn/problems/longest-substring-without-repeating-characters/solution/hua-dong-chuang-kou-by-powcai/
    public int lengthOfLongestSubstring(String s) {
        // 用来存储 每个char 的对应位置
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        int result = 0;
        String resultStr = "";
        // 滑动
        while (right < s.length()) {
            int index = map.getOrDefault(s.charAt(right), -1);
            if (index > -1) {
                // 证明有，找到这个index，从left 到 这个index map移除
                for (int i = left; i <= index; i++) {
                    map.remove(s.charAt(i));
                }
                System.out.println("remove：" + left + "=" + right);
                System.out.println(map);
                left = index + 1;
                map.put(s.charAt(right), right);
                right++;

            } else {
                // 证明没有，没有就让right++
                if (result < right - left) {
                    resultStr = s.substring(left, right);
                    System.out.println("结果是：" + resultStr + "," + left + "=" + right);
                }
                result = Math.max(result, right - left);

                map.put(s.charAt(right), right);
                right++;
            }
        }
        System.out.println("---------------");
        System.out.println(result);
        System.out.println("---------------");
        return result;
    }

    private void removeMap(HashMap<Character, Integer> map, String s, int left, int right) {
        for (int i = left; i < right; i++) {
            map.remove(s.charAt(i));
        }
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
     * }
     * https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
     */
    @Test
    public void getfindNumberIn2DArray() {
        int[] arr1 = new int[]{1, 4, 7, 11, 15};
        int[] arr2 = new int[]{2, 5, 8, 12, 19};
        int[] arr3 = new int[]{3, 6, 9, 16, 22};
        int[] arr4 = new int[]{10, 13, 14, 17, 24};
        int[] arr5 = new int[]{18, 21, 23, 26, 30};
        System.out.println("----" + findNumberIn2DArray(new int[][]{arr1, arr2, arr3, arr4, arr5}, 9));
    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int left = matrix[0].length - 1;
        int bottom = 0;
        System.out.println("----num=: " + matrix[left][bottom] + "   left=" + left + "   bottom=" + bottom);
        while (left >= 0 && bottom < matrix[0].length) {
            int num = matrix[left][bottom];
            System.out.println("num=: " + num + "   left=" + left + "   bottom=" + bottom);
            if (matrix[left][bottom] > target) {
                left--;
            } else if (matrix[left][bottom] < target) {
                bottom++;
            } else {
                return true;
            }
        }
        return false;
    }
    /**
     * 0，1 背包，要不选要不不选，只能选一次
     */

    /**
     * 给你一个可装载重量为 W 的背包和 N 个物品，每个物品有重量和价值两个属性。
     * 其中第 i 个物品的重量为 wt[i]，价值为 val[i]，
     * 现在让你用这个背包装物品，最多能装的价值是多少？
     */
    int knapsack(int W, int N, int[] wt, int[] val) {
        return 0;
    }


    /**
     * 1
     * 2       3
     * 4   5   6   7
     * pre = 1,2,4,5,3,6,7    =  [1 [2,4,5] [3,6,7]]
     * in =  4,2,5,1,6,3,7    = [4,2,5][1][6,3,7]
     *
     * @return
     */
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        return buildTreeDeep(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
//    }
    @Test
    public void testTreeDeep() {
        TreeNode root = buildTree(new int[]{1, 2, 4, 5, 3, 6, 7}, new int[]{4, 2, 5, 1, 6, 3, 7});
        printQian(root);
        printZhong(root);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder) {
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
        int[] leftIn = Arrays.copyOfRange(inorder, 0, rootIndex);
        int[] rightIn = Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length);
        int[] leftPre = Arrays.copyOfRange(preorder, 1, leftIn.length + 1);
        int[] rightPre = Arrays.copyOfRange(preorder, leftIn.length + 1, preorder.length);

//        System.out.println(Arrays.toString(leftIn));
//        System.out.println(Arrays.toString(rightIn));
//        System.out.println(Arrays.toString(leftPre));
//        System.out.println(Arrays.toString(rightPre));

        TreeNode left = buildTree(leftPre, leftIn);
        TreeNode right = buildTree(rightPre, rightIn);
        root.left = left;
        root.right = right;
        return root;
    }

    /**
     * 零钱兑换
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     * <p>
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     * <p>
     * 你可以认为每种硬币的数量是无限的。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：coins = [1, 2, 5], amount = 11
     * 输出：3
     * 解释：11 = 5 + 5 + 1
     * dp[11] = Min(dp[11-5],dp[11-2],dp[11-1])
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // 假如都是1
        //为啥 dp 数组中的值都初始化为 amount + 1 呢，因为凑成 amount 金额的硬币数最多只可能等于 amount（全用 1 元面值的硬币），
        // 所以初始化为 amount + 1 就相当于初始化为正无穷，便于后续取最小值。
        // 为啥不直接初始化为 int 型的最大值 Integer.MAX_VALUE 呢？
        // 因为后面有 dp[i - coin] + 1，这就会导致整型溢出
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {

                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        if (dp[amount] == amount + 1) {
            return -1;
        }

        return dp[amount];
    }


    @Test
    public void testCoin() {
        System.out.println(coinChange(new int[]{2}, 3));
    }


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
        if (S.isEmpty()) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        char temp = S.charAt(0);
        int tempSum = 1;
        for (int i = 1; i < S.length(); i++) {
            if (temp == S.charAt(i)) {
                tempSum++;
            } else {
                result.append(temp);
                result.append(tempSum);
                tempSum = 1;
                temp = S.charAt(i);

            }
        }

        result.append(temp);
        result.append(tempSum);

        if (result.toString()
                .length() >= S.length()) {
            return S;
        }
        return result.toString();
    }

    @Test
    public void testC() {
        compress(new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'});
    }

    public int compress(char[] chars) {
        if (chars.length == 0) {
            return 0;
        }
        StringBuilder result = new StringBuilder();
        char temp = chars[0];
        int tempSum = 1;
        for (int i = 1; i < chars.length; i++) {
            if (temp == chars[i]) {
                tempSum++;
            } else {
                result.append(temp);
                if (tempSum != 1) {
                    String sumStr = tempSum + "";
                    for (char c : sumStr.toCharArray()) {
                        result.append(c);
                    }
                }
                tempSum = 1;
                temp = chars[i];

            }
        }

        result.append(temp);
        if (tempSum != 1) {
            String sumStr = tempSum + "";
            for (char c : sumStr.toCharArray()) {
                result.append(c);
            }
        }
        System.out.println(result.toString());
        for (int i = 0; i < result.length(); i++) {
            chars[i] = result.charAt(i);
        }
        return result.toString()
                .length();
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
        System.out.println(longestPalindrome("babad"));
    }

    public String longestPalindrome(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            String iStr = longestPalindromeS(s, i, i);
            String iiStr = longestPalindromeS(s, i, i + 1);
            if (iStr.length() > result.length()) {
                result = iStr;
            }
            if (iiStr.length() > result.length()) {
                result = iiStr;
            }
        }
        return result;
    }

    private String longestPalindromeS(String s, int left, int right) {
        String result = "";
        while (left >= 0 && right < s.length() && right >= left) {
            if (s.charAt(left) == s.charAt(right)) {
                result = s.substring(left, right + 1);
                left--;
                right++;
            } else {
                break;
            }
        }

        return result;
    }

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
     */

    public int maxProfit1(int[] prices) {
        int result = 0;
        if (prices.length < 2) {
            return result;
        }
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > min) {
                result = Math.max(result, prices[i] - min);
            } else {
                min = prices[i];
            }
        }
        return result;
    }

    /**
     * 最长连续递增序列
     * 给定一个未经排序的整数数组,找到最长且 连续递增的子序列,并返回该序列的长度。
     * 输入：nums = [1,3,5,4,7]
     * 输出：3
     * 解释：最长连续递增序列是 [1,3,5], 长度为3。
     * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的,因为 5 和 7 在原数组里被 4 隔开。
     * dp[i] = if(dp[i-1])
     * <p>
     * 链接：https://leetcode.cn/problems/longest-continuous-increasing-subsequence
     * <p>
     */
    @Test
    public void getLcis() {
        int[] array = {2, 2, 2, 2, 2};
        findLengthOfLCIS(array);
    }

    public int findLengthOfLCIS(int[] nums) {
        int result = 1;
        int time = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                time++;
                result = Math.max(result, time);
            } else {
                time = 1;
            }

        }
        return result;
    }

    /**
     * 最长递增子序列
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 不是连续的哦
     * 输入：nums = [10,9,2,5,3,7,101,18]
     * 输出：4
     * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
     * <p>
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     */
    public int lengthOfLIS(int[] nums) {
        return 0;
    }

    /**
     * 全排列
     * https://leetcode.cn/problems/permutations/
     * 给定一个不含重复数字的数组 nums ,返回其 所有可能的全排列 。你可以 按任意顺序 返回答案
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     *
     * @return
     */
    @Test
    public void getPermute() {
        int[] demo = {0, -1, 1};
        permute(demo);
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permuteList(result, new ArrayList<Integer>(), nums);
        for (List<Integer> integers : result) {
            System.out.println(Arrays.toString(integers.toArray()));
        }
        return result;
    }

    private void permuteList(List<List<Integer>> result, ArrayList<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            result.add(list);
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (!list.contains(nums[i])) {
                    ArrayList<Integer> temp = new ArrayList<Integer>(list);
                    temp.add(nums[i]);
                    permuteList(result, temp, nums);
                }
            }
        }

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
    @Test
    public void t3estsum() {
        threeSum(new int[]{1, -1, -1, 0});
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        for (int i = 0; i < nums.length; i++) {

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] > 0) {
                break;
            }
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int temp = nums[left] + nums[right] + nums[i];
                System.out.println(i + "---" + left + "---" + right + "---" + temp);
                System.out.println(nums[i] + "===" + nums[left] + "===" + nums[right] + "===" + temp);
                if (temp > 0) {
                    right = right - 1;
                } else if (temp < 0) {
                    left = left + 1;
                } else {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);
                    while (left < right && nums[left + 1] == nums[left]) {
                        left++;
                    }
                    while (left < right && nums[right - 1] == nums[right]) {
                        right--;
                    }
                    left++;
                    right--;

                }
            }
        }
        return result;
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
    public void testLong() {
        longestConsecutive(new int[]{1, 2, 3, 4, 100, 200});
    }

    public int longestConsecutive(int[] nums) {
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

    /**
     * https://leetcode.cn/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    //给定一个二叉树, 找到该树中两个指定节点的最近公共祖先
    // 1. 要不都在左边
    // 2. 要不都在右边
    // 3. 在两边，那么这个点就是
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (p == root) {
            return root;
        }
        if (q == root) {
            return root;
        }
        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);
        if (leftNode == null) {
            return rightNode;
        }
        if (rightNode == null) {
            return leftNode;
        }
        // 都不等于null 就是root
        return root;
    }

    /**
     * https://leetcode.cn/problems/spiral-matrix/
     * 螺旋矩阵 54
     * 顺时针打印矩阵
     * 1   2  3  4
     * 12 13 14  5
     * 11 16 15  6
     * 10  9 8   7
     * <p>
     * 1   2    3
     * 4   5    6
     * 7   8    9
     *
     * @param matrix
     * @return
     */
    @Test
    public void testSpria() {
        int[][] demo = new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9}};
        spiralOrder(demo);
    }

    public Object[] spiralOrder(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<>();
        int left = 0;
        int top = 0;
        int right = matrix.length - 1;
        int bottom = matrix[0].length - 1;
        while (true) {
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
                System.out.println(matrix[top][i]);
            }
            top++;
            if (bottom < top) break;


            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
                System.out.println(matrix[i][right]);
            }
            right--;
            if (right < left) break;


            for (int i = right; i >= left; i--) {
                result.add(matrix[bottom][i]);
                System.out.println(matrix[bottom][i]);
            }
            bottom--;
            if (bottom < top) break;


            for (int i = bottom; i >= top; i--) {
                result.add(matrix[i][left]);
                System.out.println(matrix[i][left]);
            }
            left++;
            if (right < left) break;


        }

        return result.toArray();
    }

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
        if (root != null) {
            if (root.val == val) {
                return root;
            } else if (root.val > val) {
                return searchBST(root.left, val);
            } else {
                searchBST(root.right, val);
            }


        }
        return null;
    }

    /**
     * 209. 长度最小的子数组
     * https://leetcode.cn/problems/minimum-size-subarray-sum/
     *
     * @param nums
     * @return 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组
     */
    @Test
    public void testMin() {
        minSubArrayLen(213, new int[]{12, 28, 83, 4, 25, 26, 25, 2, 25, 25, 25, 12});
    }

    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int index = 0;
        int right = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        while (right < nums.length) {
            sum = sum + nums[index];
            index++;
            while (sum > target) {
                result = Math.min(result, index - left);
                sum = sum - nums[left];
                left--;
            }
            right++;

        }
        return result;
    }

    class LRUCache {
        private int size = 10;
        private LinkedList<Integer> keys = new LinkedList<>();
        private HashMap<Integer, Integer> values = new HashMap<>();
        private int[] value;

        public LRUCache(int capacity) {
            size = capacity;
        }

        public int get(int key) {
            int value = keys.remove(key);
            keys.addFirst(key);
            return value;
        }

        public void put(int key, int value) {
            keys.addFirst(key);
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
    @Test
    public void testNumIslands() {

    }

    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i >= grid.length || i < 0) {
            return;
        }
        if (j >= grid[0].length || j < 0) {
            return;
        }
        if (grid[i][j] == '1') {
            grid[i][j] = '0';
            dfs(grid, i, j - 1);
            dfs(grid, i, j + 1);
            dfs(grid, i - 1, j);
            dfs(grid, i + 1, j);
        }
    }

    /**
     * 岛屿最大面积
     * https://leetcode.cn/problems/max-area-of-island/
     *
     * @param grid
     * @return
     */
    int max = 0;
    int countNum = 0;

    public int maxAreaOfIsland(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    dfs1(grid, i, j);
                    count++;
                    max = Math.max(max, countNum);
                    countNum = 0;
                }
            }
        }
        return count;
    }

    private void dfs1(int[][] grid, int i, int j) {
        if (i >= grid.length || i < 0) {
            return;
        }
        if (j >= grid[0].length || j < 0) {
            return;
        }
        if (grid[i][j] == 1) {
            countNum++;
            // 到了这里就加1
            grid[i][j] = 0;
            dfs1(grid, i, j - 1);
            dfs1(grid, i, j + 1);
            dfs1(grid, i - 1, j);
            dfs1(grid, i + 1, j);
        }
    }

    public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfsPermute(nums, new ArrayList<Integer>(), result);
        return result;
    }

    private void dfsPermute(int[] nums, ArrayList<Integer> integers, List<List<Integer>> result) {
        if (integers.size() == nums.length) {
            result.add(new ArrayList<>(integers));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!integers.contains(nums[i])) {
                integers.add(nums[i]);
                dfsPermute(nums, integers, result);
                integers.remove(integers.size() - 1);
            }
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        dfscombinationSum(candidates, target, result, new ArrayList<Integer>(), 0);
        return result;
    }

    private void dfscombinationSum(int[] candidates, int target, List<List<Integer>> result, ArrayList<Integer> integers, int begin) {
        if (target == 0) {
            result.add(new ArrayList<>(integers));
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = begin; i < candidates.length; i++) {
            integers.add(candidates[i]);
            dfscombinationSum(candidates, target - candidates[i], result, integers, i);
            integers.remove(integers.size() - 1);
        }
    }


    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfscomsubsets(nums, result, new ArrayList<Integer>());
        return result;
    }

    private void dfscomsubsets(int[] nums, List<List<Integer>> result, ArrayList<Integer> integers) {
        for (int i = 0; i < nums.length; i++) {
            if (!integers.contains(nums[i])) {
                // 选或者不选
                integers.add(nums[i]);
                dfscomsubsets(nums, result, integers);
                integers.remove(integers.size() - 1);
            }
        }
    }

    public TreeNode invertTree(TreeNode root) {
        if (root != null) {
            TreeNode temp = root.right;
            root.right = root.left;
            root.left = temp;
            invertTree(root.left);
            invertTree(root.right);

        }
        return root;
    }

    public void flatten(TreeNode root) {
        if (root != null) {
            dfsflatten(root);
        }
    }

    private TreeNode dfsflatten(TreeNode root) {
        if (root != null) {
            TreeNode left = dfsflatten(root.left);
            TreeNode right = dfsflatten(root.right);
        }
        return null;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root != null) {
            if (root.val > key) {
                root.left = deleteNode(root.left, key);
                // 中序遍历
            } else if (root.val < key) {
                root.right = deleteNode(root.left, key);
            } else {

            }

        }

        return null;
    }

    public boolean isValidBST1(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isValidBS1T(root.left, Integer.MAX_VALUE) && isValidBS1T(root.right, Integer.MIN_VALUE);
    }

    private boolean isValidBS1T(TreeNode left, int maxValue) {
        return false;
    }
}

