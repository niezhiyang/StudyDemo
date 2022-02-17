package com.nzy.viewstudy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * @author niezhiyang
 * since 12/3/21
 */
public class LeetCodeNote {

//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
//
// 示例:
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0]
//
// 说明:
//
//
// 必须在原数组上操作，不能拷贝额外的数组。
// 尽量减少操作次数。
//
// Related Topics 数组 双指针
// 👍 1326 👎 0


    ///------------------------ 1 ---------------------
    //leetcode submit region begin(Prohibit modification and deletion)
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
        // 遍历，遇到不等0，就和 noZeroNum 交换，noZeroNum是已经排好序的末尾
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
     * 一个数组 把奇数移动到偶数的前面，前面都是奇数，后面都是偶数，并且顺讯位置不变
     * 牛客网
     *  https://www.nowcoder.com/questionTerminal/beb5aa231adc45b2a5dcc5b62c93f593?u_atoken=50210515-9ecb-4717-bb2f-5105515b6fd4&u_asession=01lBEXJ5iJK-xqW6oYZGYBehv9UE9_diPO1VLmeJeQOZZAATrvape1CnvPG9A52NwvX0KNBwm7Lovlpxjd_P_q4JsKWYrT3W_NKPr8w6oU7K-Li7-QNHLulm_9hi6RWM_7MJtBx3S14qt35vRMTfjp8mBkFo3NEHBv0PZUm6pbxQU&u_asig=05TrrStJKDQyRL6Pe0d0BBudB2njKw7Ild18MC7W8uajnCmX7k-lOaZub8O9ZeALTgWuIgbONlBHRXu1-B12kifP4LHPjX5907K-txzQcH3UmpMabM_J-UKBMf6nWHl-5wjPPn-XeGXDreuo9MsIM9WLZnSPGDnr5S6-RvOtHKEgP9JS7q8ZD7Xtz2Ly-b0kmuyAKRFSVJkkdwVUnyHAIJzToa8Vt0r92plzbIg9M640pZXxwQHSsLQNDfkbILggi41YpiKJZCArXmgv6vSk2RSO3h9VXwMyh6PgyDIVSG1W84e5kNRA7V7_g0dyd2LP3xxPV46rxeunwwnCjSohRo2NjjroiW10CAo3omzWtvqz3EekJQaPUEiizeJrvIM-UVmWspDxyAEEo4kbsryBKb9Q&u_aref=6mGzVj%2F4f%2BRG0%2BwoVXwsnwXRSu4%3D
     *
     */
    @Test
    public void temp(){
        int[] arr = new int[]{1,2,4,3,6,5};
        moveJiOuShu(arr);
        System.out.println(Arrays.toString(arr));
    }
    public void moveJiOuShu(int[] nums) {
        int left = 0; // 移动完成的末尾
        int right = 0; // 还没移动的开始
        while (right < nums.length) {
            if (nums[right] %2!= 0) {
                // 这里就不能 简单的交换了，需要先记录下来这个奇数
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
     * https://leetcode-cn.com/problems/climbing-stairs/
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

    ///------------------------ 4 ---------------------

    /**
     * 反转链表
     * https://leetcode-cn.com/problems/reverse-linked-list/
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
     * https://leetcode-cn.com/problems/linked-list-cycle/
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

    ///------------------------ 6 ---------------------

    /**
     * 两个链表的交点，
     * https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/
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
     * https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
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
     *
     * @param node1
     * @param node2
     * @return
     */


    public ListNode mergeTwoLists1(ListNode node1, ListNode node2) {
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

        // 合并后 node1 和 node2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        if (node1 == null) {
            prev.next = node2;
        } else {
            prev.next = node1;
        }

        return prehead.next;
    }


    ///------------------------ 9 ---------------------

    /**
     * 连个链表两两交换
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

    ///------------------------ 10 ---------------------
    // 删除节点
    // https://leetcode-cn.com/problems/delete-middle-node-lcci/
    public void deleteNode(ListNode node) {
        //既然不能先删除自己，那就把自己整容成儿子，再假装儿子养活孙子
        node.val = node.next.val;
        node.next = node.next.next;
    }

    // 给个头结点，然后删除某个节点val 是 val的
    // https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/
    public ListNode deleteNode(ListNode head, int val) {
        if (head.val == val) {
            // 如果是头结点，直接返回下一个就行
            return head.next;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val == val) {
                // 找到了 直接break，此时 cur就是当前需要删除的，pre，就是需要删除的前一个
                break;
            } else {
                pre = cur;
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
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * https://leetcode-cn.com/problems/SLwz0R/
     */

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode curr = head;
        ListNode pre = null;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        if (fast == null) {
            // 如果是头结点，直接返回下一个就行
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

    ///------------------------ 11 ---------------------

    /**
     * 二叉树前序遍历
     * <p>
     * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
     *
     * @param root
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;

    }

    public void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        inorder(root.left, res);
        inorder(root.right, res);
    }

    ///------------------------ 12 ---------------------

    /**
     * 层遍历二叉树,每一层放到数组中
     * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
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

    ///------------------------ 12 ---------------------

    /**
     * 层遍历二叉树,Z字形，每一层放到数组中
     * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
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
            // 偶数 添加到头部 ，奇数 添加到尾部
            LinkedList<Integer> temp = new LinkedList<>();
            for (int i = linkedList.size() - 1; i >= 0; i--) {
                TreeNode first = linkedList.removeFirst();
                if (result.size() % 2 == 0) {
                    // 偶数 添加到头部 ，奇数 添加到尾部
                    temp.addLast(first.val);
                } else {
                    // 偶数 添加到头部 ，奇数 添加到尾部
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
     * 层遍历二叉树，放到一个数组中
     *
     * @param root
     * @return
     */
    public int[] levelOrder1(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
            result.add(root.val);
        }

        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll.left != null) {
                queue.add(poll.left);
                result.add(poll.left.val);
            }

            if (poll.right != null) {
                queue.add(poll.right);
                result.add(poll.right.val);
            }
        }
        int[] resultInt = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resultInt[i] = result.get(i);
        }
        return resultInt;
    }

    ///------------------------ 14 ---------------------

    /**
     * 二叉树中所有的路径  ["1->2->5","1->3"]
     * https://leetcode-cn.com/problems/binary-tree-paths/
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<String> result = new ArrayList<>();
        binary(root, "", result);
        return result;
    }

    private void binary(TreeNode root, String path, ArrayList<String> result) {
        if (root != null) {
            String temp = "";
            if (path.isEmpty()) {
                temp = root.val + "";

            } else {
                temp = path + "->" + root.val;
            }

            if (root.left == null && root.right == null) {
                result.add(temp);
            } else {
                binary(root.right, temp, result);
                binary(root.left, temp, result);
            }


        }
    }

    ///------------------------ 15 ---------------------

    /**
     * https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
     * 二叉树中和为某一值的路径
     * <p>
     * 先把所有的路径放到一个集合中，然后遍历集合
     *
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        getPath(root, result, new ArrayList<Integer>());

        List<List<Integer>> now = new ArrayList<>();

        for (int i = 0; i < result.size(); i++) {
            List<Integer> list = result.get(i);
            int temp = 0;
            for (int j = 0; j < list.size(); j++) {
                temp += list.get(j);
                if (sum == temp && j == list.size() - 1) {
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

    ///------------------------ 16 ---------------------

    /**
     * 是否是二叉搜索树
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     * <p>
     * 有效 二叉搜索树定义如下：
     * <p>
     * 节点的左子树只包含 小于 当前节点的数。
     * 节点的右子树只包含 大于 当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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


    ///------------------------ 18 ---------------------
    private boolean isHave = false;

    /**
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

    ///------------------------ 20 ---------------------

    /**
     * 是否是对称二叉树
     * 给定一个二叉树，检查它是否是镜像对称的。
     * https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/
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
     * https://leetcode-cn.com/problems/two-sum/
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
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
     * <p>
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int slow = 0, fast = 1;
        while (fast < nums.length) {
            // 因为是有序数组，所以 slow 代表可以代表不重复的个数

            if (nums[slow] == nums[fast]) {
                // 如果相等 就让 快的走
                fast++;
            } else {
                // 如果不相等 就让慢的走，代表 不重复的个数
                slow++;
                // 赋值给慢的
                nums[slow] = nums[fast];
            }
        }

        return slow + 1;
    }

    ///------------------------ 23 ---------------------

    /**
     * https://leetcode-cn.com/problems/jump-game/
     * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
     * <p>
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * <p>
     * 判断你是否能够到达最后一个下标。
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
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * https://leetcode-cn.com/problems/valid-parentheses/
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
     * https://leetcode-cn.com/problems/generate-parentheses/
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
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
     * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
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

    ///------------------------ 27 ---------------------

    /**
     * 有效的字母异位词
     * 放到map中，遍历第一个 ++ ，遍历第二个 --；最后遍历hashmap 如果value 都等于0 证明是
     * 也可以，先变成char数组 ，然后再排序，再转成String 看看 是否equals
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        // 放到map里面，
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
     * https://leetcode-cn.com/problems/unique-paths/
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
     * 切记，第一行 第一列赋值的时候，如果有障碍物 ，直接跳出循环
     * 不同路径 II
     * https://leetcode-cn.com/problems/unique-paths-ii/
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
        // 切记 不要这样写，因为 假如 第一个是障碍物，那么后面的都是0
//        for (int j = 0; j < n; j++) {
//            if (obstacleGrid[0][j] == 0) {
//                dp[0][j] = 1;
//            }
//        }

        // 当 是障碍物的时 就跳出循环，不在往下执行
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        // 当 是障碍物的时 就跳出循环，不在往下执行
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
     * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * dp[m][n] = Math.min(dp[m-1][n],dp[m][n])+1
     * <p>
     * https://leetcode-cn.com/problems/minimum-path-sum/
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
     * 三角形最小路径和
     * <p>
     * dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        // 创建一个二维数组
        int[][] dp = new int[n][n];
        // 最上面的
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; ++i) {
            // 第一列赋值
            dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; ++j) {
                // 除了第一列 和 最后一列 都是这样的
                dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
            }
            // 最后一列 ，只能是
            dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);
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
     * https://leetcode-cn.com/problems/house-robber/
     */
    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        // 先赋值第一个，因为下面for循环，不能从0开始
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

    ///------------------------ 32 ---------------------

    /**
     * 最大子数组和
     * dp[i] = Math.max(nums[i], dp[i - 1]);
     * https://leetcode-cn.com/problems/maximum-subarray/
     */
    public int maxSubArray(int[] nums) {

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        // 因为有负数，所以是其中的摸一个值
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            result = Math.max(result, dp[i]);
        }
        return result;

    }

    ///------------------------ 33 ---------------------

    /**
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。
     * https://leetcode-cn.com/problems/powx-n/
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
        //拆分成 n/2个，相当于 2的二分，这样 复杂度是 log 2为底 N的对数
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
     * https://leetcode-cn.com/problems/merge-sorted-array/
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
            //获取出队元素后，再将s2里面的元素放入s1里面。
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
            //获取出队元素后，再将s2里面的元素放入s1里面。
            while (!mStack2.empty()) {
                mStack1.push(mStack2.pop());
            }
            return val;
        }

        public boolean empty() {
            return mStack1.isEmpty();
        }
    }

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
        return res.reverse().toString();
    }

    ///------------------------ 34 ---------------------
    // ## 把m个同样的苹果放在n个同样的盘子里，允许有的盘子空着不放，问有多少种不同的分法？(注：5,1,1和1,1,5是同一种分法）
//            - 当苹果是0 或者 盘子是1 的时候认为一种
//- 当盘子 大于 苹果个数的时候，直接 fun(m,m)
//- 当盘子 小于等于 苹果个数的时候，可以认为，有一个是0 再 加上都不是0  fun(m-n,n) m-n 证明每个盘子放了一个
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
     * https://leetcode-cn.com/problems/add-two-numbers/
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * <p>
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * <p>
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * @param l1
     * @param l2
     * @return 暴力，遍历 链表 取出来相加
     * 各位*1 十位 * 10，百为 *100 取出来之后 ，然后相加，然后再弄成链表
     * 有可能会超过了 long 和 int 的最大大小，
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

        // 结果链表的头结点head ，新节点
        ListNode result = new ListNode(-1);
        // 结果链表的遍历指针，代表当前操作的节点
        ListNode curr = result;
        // 进位
        int carry = 0;
        // 长度不够 补 0，或者是 剩下的给到 curr
        while (p != null || q != null) {
            // 如果是null 证明到头了，直接用 0
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

            // 得到 十位 ，进位的
            carry = sum / 10;
            // 得到 个位
            int num = sum % 10;
            // 创建新节点 ， 插入到尾部
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
     * https://leetcode-cn.com/problems/add-binary/
     * 二进制求和
     *
     * @param a
     * @param b 给你两个二进制字符串，返回它们的和（用二进制表示）。
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
     * https://leetcode-cn.com/problems/reverse-integer/
     * 整数反转
     * <p>
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     * <p>
     * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
     * <p>
     * 假设环境不允许存储 64 位整数（有符号或无符号）。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-integer
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
        // 1.整数转字符串，再转字符数组
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
// 4.将原数组转成字符串，再转成整数输出
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
            // 当补位 最后一位之前 判断 如果溢出 ，就返回0 就行了，补充之前 肯定不会移除
            int temp = x % 10;
            // 将每一位数字计算累加，将上次结果*10 + 新数字
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
     * 例如，""，"()"，"(())()" 和 "(()(()))" 都是有效的括号字符串。
     * 如果有效字符串 s 非空，且不存在将其拆分为 s = A + B 的方法，我们称其为原语（primitive），其中 A 和 B 都是非空有效括号字符串。
     * <p>
     * 给出一个非空有效字符串 s，考虑将其进行原语化分解，使得：s = P_1 + P_2 + ... + P_k，其中 P_i 是有效括号字符串原语。
     * <p>
     * 对 s 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 s 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-outermost-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    ////////////////////--------用左括号 有括号 ++的形式，如果左右相等，则截取--------/////////////////////
    public String removeOuterParentheses(String s) {
        // 左括号数量 等于 有括号数量时，拼接
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
        // 左括号数量 等于 有括号数量时，拼接
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
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     * 无重复字符的最长子串
     * 滑块
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
     */

    @Test
    public void getRe11() {
        lengthOfLongestSubstring("abacabcbb");
    }

    // 其他的一些滑动窗口
    // https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/hua-dong-chuang-kou-by-powcai/
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
                // 记住要加1 你就认为 一个 a ，所以这里得加1
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
     * https://leetcode-cn.com/problems/reverse-words-in-a-string/
     * 给你一个字符串 s ，逐个翻转字符串中的所有 单词 。
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
     * 输入：s = "  hello world  "
     * 输出："world hello"
     * 解释：输入字符串可以在前面或者后面包含多余的空格，但是翻转后的字符不能包括。
     */
    // 1. 暴力 以空格分割
    // 2. 把字符串反转过来，然后把单词的 再反转
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
                if (!result.toString().isEmpty()) {
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
     * 给定M×N矩阵，每一行、每一列都按升序排列，请编写代码找出某元素。
     * [
     * [1,   4,  7, 11, 15],
     * [2,   5,  8, 12, 19],
     * [3,   6,  9, 16, 22],
     * [10, 13, 14, 17, 24],
     * [18, 21, 23, 26, 30]
     * ]
     * 给定 target = 5，返回 true。
     * <p>
     * 给定 target = 20，返回 false。
     * https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
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

    public boolean searchMatrix(int[][] matrix, int target) {
        // 可以和对角线 作比较，右边和下边
        // 比如找8 ，之后找对角线，5 的左上 和 9的右下都不用考虑了

        int m = matrix.length;
        if (m == 0) {
            return false;
        }
        int n = matrix[0].length;


        return searchSubMatrix(matrix, target, 0, 0, m - 1, n - 1);
    }

    public boolean searchSubMatrix(int[][] matrix, int target, int startRow, int startColumn, int endRow, int endColumn) {

        if (startRow > endRow || startColumn > endColumn) {
            return false;
        }
        if (matrix[startRow][startColumn] > target || matrix[endRow][endColumn] < target) {
            return false;
        }
        // 去对角线 查找元素  行的个数 和 列的个数的较小的值
        int diagonal_length = Math.min(endRow - startRow + 1, endColumn - startColumn + 1);

        for (int i = 0; i < diagonal_length; i++) {
            if (matrix[startRow + i][startColumn + i] == target) {
                return true;

            } else if (i == diagonal_length - 1 || matrix[startRow + i + 1][startColumn + i + 1] > target) {
                // i ==  diagonal_length-1 表示最后一个元素
                return searchSubMatrix(matrix, target, startRow, startColumn +
                        i + 1, startRow + i, endColumn)
                        ||
                        searchSubMatrix(matrix, target, startRow + i + 1,
                                startColumn, endRow, startColumn + i);
            }
        }
        return false;

    }

    ///------------------------ 41 ---------------------

    /**
     * 多数元素
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * <p>
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/majority-element
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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
    ///------------------------ 42 ---------------------

    /**
     * 二分查找
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-search
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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

    ///------------------------ 43 ---------------------

    /**
     * 零钱兑换
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     * <p>
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
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
     */

    public int coinChange(int[] coins, int amount) {
        // 假如里面都是1
        int[] dp = new int[amount + 1];
        // // 注意：因为要比较的是最小值，这个不可能的值就得赋值成为一个最大值
        Arrays.fill(dp, amount + 1);
        // 理解 dp[0] = 0 的合理性，单独一枚硬币如果能够凑出面值，符合最优子结构
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                // 找 这几个里面dp[i]的最小值
                // 如果 i 大于 当前coin的数值 ，就可以进去
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
                }
            }
        }
        if (dp[amount] == amount + 1) {
            dp[amount] = -1;
        }
        return dp[amount];
    }

    /**
     * 加入 coins = {25,10,5,1} 的话 凑出来 41，因为  1 ，5，10，25 对应都是 向上都是5的倍数
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
     * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     * <p>
     * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sort-colors
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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
     * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。
     * 比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/compress-string-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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
                result.append(temp).append(num);
                temp = S.charAt(index);
                num = 0;

            }

        }
        result.append(temp).append(num);
        if (result.length() >= S.length()) {
            return S;
        }
        return result.toString();
    }


    ///------------------------ 46 ---------------------

    /**
     * 给你一个字符数组 chars ，请使用下述算法压缩：
     * <p>
     * 从一个空字符串 s 开始。对于 chars 中的每组 连续重复字符 ：
     * <p>
     * 如果这一组长度为 1 ，则将字符追加到 s 中。
     * 否则，需要向 s 追加字符，后跟这一组的长度。
     * 输入：chars = ["a","a","b","b","c","c","c"]
     * 输出：返回 6 ，输入数组的前 6 个字符应该是：["a","2","b","2","c","3"]
     * <p>
     * <p>
     * 链接：https://leetcode-cn.com/problems/string-compression
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
                chars[i] = result.toString().toCharArray()[i];
            }
            length = result.length();
        }
        System.out.println(Arrays.toString(chars));
        return length;
    }

    ///------------------------ 47 ---------------------

    /**
     * 5. 最长回文子串
     * 给你一个字符串 s，找到 s 中最长的回文子串。
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
     * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * <p>
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
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
     * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
     * <p>
     * 链接：https://leetcode-cn.com/problems/gu-piao-de-zui-da-li-run-lcof
     * <p>
     * 假设 找到每一天的卖股票 找打最大值
     * <p>
     * 循环，记录当前的最小值，以及可赚的最大值
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
            // 如果 比当前最小值 要大，算吃来当前的利润，然后和 前面最大的利润作比较
            // 如果小于的话，就把最小值 替换成
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
     * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
     * 输入：nums = [1,3,5,4,7]
     * 输出：3
     * 解释：最长连续递增序列是 [1,3,5], 长度为3。
     * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
     * <p>
     * 链接：https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence
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
        int maxResult = 0;
        int temp = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] > 0) {
                temp++;
                maxResult = Math.max(temp, maxResult);
            } else {
                temp = 0;
            }
        }

        return maxResult + 1;

    }


    ///------------------------ 50 ---------------------

    /**
     * 全排列
     * https://leetcode-cn.com/problems/permutations/
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
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
            System.out.println(Arrays.toString(path.toArray()));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (path.contains(nums[i])) continue;
            ArrayList<Integer> temp = new ArrayList<>(path);
            temp.add(nums[i]);
            dps(temp, result, nums);
        }

    }


    ///------------------------ 51 ---------------------

    /**
     * 三数之和
     *
     * @param nums
     * @return 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     * <p>
     * 链接：https://leetcode-cn.com/problems/3sum
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
                    // 因为是递增的，所以这里
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
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     * <p>
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
     * <p>
     * 链接：https://leetcode-cn.com/problems/palindrome-number
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        char[] chars = String.valueOf(x).toCharArray();
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
     * https://leetcode-cn.com/problems/rotate-list/
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
                // 找到这个节点的前一个，然后设置next 为 null 即可
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
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * <p>
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * <p>
     * https://leetcode-cn.com/problems/remove-element/
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
        return left + 1;
    }

    ///------------------------ 55 ---------------------

    /**
     * 最长连续序列
     * 输入：nums = [100,4,200,1,3,2]
     * 输出：4
     * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     * <p>
     * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题
     * <p>
     * https://leetcode-cn.com/problems/longest-consecutive-sequence/
     */

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
        return set.iterator().next();

    }
    ///------------------------ 57 ---------------------

    /**
     * 左旋转字符串
     * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
     * <p>
     * <p>
     * <p>
     * 链接：https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof
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

    //给定一个二叉树, 找到该树中两个指定节点的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            //只要当前根节点是p和q中的任意一个，就返回（因为不能比这个更深了，再深p和q中的一个就没了）
            return root;
        }
        //根节点不是p和q中的任意一个，那么就继续分别往左子树和右子树找p和q
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //p和q都没找到，那就没有
        if (left == null && right == null) {
            return null;
        }
        //左子树没有p也没有q，就返回右子树的结果
        if (left == null) {
            return right;
        }
        //右子树没有p也没有q就返回左子树的结果
        if (right == null) {
            return left;
        }
        //左右子树都找到p和q了，那就说明p和q分别在左右两个子树上，所以此时的最近公共祖先就是root
        return root;
    }
}





























