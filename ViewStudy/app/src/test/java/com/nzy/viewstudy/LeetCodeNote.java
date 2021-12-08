package com.nzy.viewstudy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
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
    public ListNode mergeTwoLists(ListNode node1, ListNode node2) {
        // 先创建一个新的节点
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        while (node1 != null && node2 != null) {
            if (node1.val < node2.val) {
                prev.next = node1;
                node1 = node1.next;
            } else {
                prev.next = node2;
                node1 = node2.next;
            }
        }
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
    public void deleteNode(ListNode node) {
        //既然不能先删除自己，那就把自己整容成儿子，再假装儿子养活孙子
        node.val = node.next.val;
        node.next = node.next.next;
    }

    // 给个头结点，然后删除某个节点val 是 val的
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

    ///------------------------ 11 ---------------------

    /**
     * 二叉树前序遍历
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
        return isValidBST(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    private boolean isValidBST(TreeNode node, int upper, int lower) {
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
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
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
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int pm = 0;
        int pn = 0;
        int[] result = new int[n + m];
        int tail = -1;

        while (pm < m && pn < n) {
            tail++;
            if (nums1[pm] < nums2[pn]) {
                result[tail] = nums1[pm];
                pm++;
            } else {
                result[tail] = nums2[pn];
                pn++;
            }
        }

        if (pm == m) {
            // 证明nums1 到头了，把n剩下的全部放进去
            for (int i = 0; i < n - pn; i++) {
                result[++tail] = nums2[++pn];
            }
        } else {
            for (int i = 0; i < m - pm; i++) {
                result[++tail] = nums1[++pm];
            }
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

}
