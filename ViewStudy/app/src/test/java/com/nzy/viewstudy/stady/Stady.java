package com.nzy.viewstudy.stady;

import com.nzy.viewstudy.TreeNode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * @author niezhiyang
 * since 8/18/21
 */
public class Stady {
    @Test
    public void test() {
//        int[] array = new int[]{1, 0, 2, 0, 0, 1, 1, 0};
//
//
//        int left = 0;// 等于0
//        int right = 0; // 不等于0
//
//        while (right < array.length && left < array.length) {
//            int temp = array[left];
//            array[left] = array[right];
//            array[right] = temp;
//
//        }
//
//        System.out.println(Arrays.toString(array));

        //        三数之和
        //        x的n次幂
        int[] nums1 = new int[]{0};
        int[] nums2 = new int[]{1};
        merge(nums1, 0, nums2, 1);

    }

    /**
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * <p>
     * 有效括号组合需满足：左括号必须以正确的顺序闭合。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 3
     * 输出：["((()))","(()())","(())()","()(())","()()()"]
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {

        ArrayList<String> strings = new ArrayList<>();
        record(0, 0, "", strings, n);
        return strings;

    }

    private void record(int left, int right, String currentStr, ArrayList<String> strings, int n) {
        // 1.先写 出来的条件
        if ((left == right) && (left == n)) {
            System.out.println(currentStr);
            strings.add(currentStr);
        }
        // 2. 计算逻辑

        // 如果里面左括号小于 n ,就能放左括号
        if (left < n) {
            // 3. 下探一下层 递归
            record(left + 1, right, currentStr + "(", strings, n);
        }

        //
        if (right < left) {
            // 3. 下探一下层 递归
            record(left, right + 1, currentStr + ")", strings, n);
        }


        // 4. 回溯 这里都是局部变量不用回溯


    }


    /**
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。
     *
     * @param x
     * @param n
     * @return
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
     * 从上到下打印二叉树
     *
     * @param root
     * @return [1, 2, 3, 4, 5]
     */
    public int[] levelOrder(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        ArrayList<Integer> arrayList = new ArrayList<>();

        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll != null) {
                arrayList.add(poll.val);
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }


        }

        int[] reslut = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            reslut[i] = arrayList.get(i);
        }

        return reslut;

    }

    /**
     * 之字形打印
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回其层次遍历结果：
     * <p>
     * [
     * [3],
     * [20,9],
     * [15,7]
     * ]
     */

    public List<List<Integer>> levelOrder1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) queue.add(root);
        while (!queue.isEmpty()) {
            LinkedList<Integer> tmp = new LinkedList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                if (res.size() % 2 == 0) tmp.addLast(node.val); // 偶数层 -> 放在后面
                else tmp.addFirst(node.val); // 奇数层 -> 放在前面
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            res.add(tmp);
        }
        return res;

    }


    /**
     * 一行一行打印
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        ArrayList<List<Integer>> reslut = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> row = new ArrayList<>();
            // 因为当走到这里 quene 的 size 相当于一个固定值,一直递减的
            for (int i = queue.size(); i > 0; i--) {
                TreeNode poll = queue.poll();
                row.add(poll.val);
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }

            reslut.add(row);
        }


        return reslut;
    }


    /**
     * 在每个树行中找最大值
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> reslut = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> row = new ArrayList<>();
            // 因为当走到这里 quene 的 size 相当于一个固定值,一直递减的
            int max = Integer.MIN_VALUE;
            for (int i = queue.size(); i > 0; i--) {
                TreeNode poll = queue.poll();
                max = Math.max(max, poll.val);
                row.add(poll.val);
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }

            reslut.add(max);
        }


        return reslut;
    }

    /**
     * 贪心
     * 买卖股票的最佳时机 II
     * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * <p>
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     * 也就是只要是这一个大于上一个 就是收入
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;

        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                ans += (prices[i] - prices[i - 1]);
            }
        }

        return ans;
    }

    /**
     * 合并两个有序数组
     * 从后往前 添加
     * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
     * <p>
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     * <p>
     * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
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
        // 只要有一个大于0，就走这里面
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


    @Test
    public void test1() {
        int[] array = new int[]{1, 1, 2, 3, 3, 4, 5, 5, 6};
        removeDuplicates(array);
    }

    /**
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
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


    /**
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数
     * 输入: nums = [1,2,3,4,5,6,7], k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右旋转 1 步: [7,1,2,3,4,5,6]
     * 向右旋转 2 步: [6,7,1,2,3,4,5]
     * 向右旋转 3 步: [5,6,7,1,2,3,4]
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        k = nums.length % k;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }

    @Test
    public void test2() {
        isValid("(])");
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        char[] chars = s.toCharArray();
        stack.push(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar == '(' || aChar == '[' || aChar == '{') {
                stack.push(aChar);
            } else {
                if (stack.size() > 0) {
                    Character character = stack.get(stack.size() - 1);
                    if (character == '(' && aChar == ')') {
                        stack.pop();
                    } else if (character == '{' && aChar == '}') {
                        stack.pop();
                    } else if (character == '[' && aChar == ']') {
                        stack.pop();
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }


            }

        }

        return stack.isEmpty();


    }

    /**
     * 有效的字母异位词
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
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


    /**
     * 零钱兑换
     */

    public int coinChange(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        int[] count = new int[amount];
        return coinChange(coins, amount, count);
    }

    /**
     * 比如 coins = [1, 2, 5], amount = 11
     * dp[11] = min (dp[10] + 1, dp[9] + 1, dp[6] + 1)
     *
     * @param coins
     * @param remain
     * @param count  是带有记忆的数组，和爬楼梯一个道理
     * @return
     */
    private int coinChange(int[] coins, int remain, int[] count) {
        if (remain < 0) {
            return -1;
        }
        if (remain == 0) {
            return 0;
        }
        // 带记忆的数组
        if (count[remain - 1] != 0) {
            return count[remain - 1];
        }
        int min = Integer.MAX_VALUE;
        // 遍历数组，递归计算最小的
        for (int coin : coins) {
            // 算出来 减去当前的coin 的最小值，
            int res = coinChange(coins, remain - coin, count);
            if (res >= 0 && res < min) {
                // 如果符合条件，就 +1 ，因为减去自己
                min = 1 + res;
            }
        }
        count[remain - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[remain - 1];
    }

    /**
     * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap();
        HashSet<List<String>> set = new HashSet<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortString = new String(chars);
            List<String> orDefault = map.getOrDefault(sortString, new ArrayList<>());
            orDefault.add(str);
            map.put(sortString, orDefault);
        }
        Set<String> strings = map.keySet();
        for (String key : strings) {
            List<String> strings1 = map.get(key);
        }
        return new ArrayList(map.values());

    }


    /**
     * 输入：nums = [2,3,1,1,4]
     * 输出：true
     * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
     * <p>
     * <p>
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
        if (nums == null) {
            return false;
        }
        //前n-1个元素能够跳到的最远距离
        int k = 0;
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


    /**
     * * 1. 如果某一个作为 起跳点 的格子可以跳跃的距离是 3，那么表示后面 3 个格子都可以作为 起跳点
     * * 2. 可以对每一个能作为 起跳点 的格子都尝试跳一次，把 能跳到最远的距离 不断更新
     * * 3. 如果可以一直跳到最后，就成功了
     * 方法二：【性质】如果能够到达当前位置，那么也就一定能够到达当前位置的左边所有位置
     * 如果一个位置能够到达，那么这个位置左侧所有位置都能到达。 想到这一点，解法就呼之欲出了~
     */
    class Solution {
        public boolean canJump(int[] nums) {
            int k = 0;//k为当前能够到达的最大位置
            for (int i = 0; i < nums.length; i++) {
                if (i > k) return false;//【关键】遍历元素位置下标大于当前能够到达的最大位置下标，不能到达

                //能够到达当前位置，看是否更新能够到达的最大位置k
                k = Math.max(k, i + nums[i]);

            }
            //跳出则表明能够到达最大位置
            return true;
        }
    }

    public List<List<String>> groupAnagrams1(String[] strs) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String charStr = new String(chars);
            ArrayList<String> value = map.getOrDefault(charStr, new ArrayList<>());
            value.add(str);

            map.put(charStr, value);
        }
        return new ArrayList<>(map.values());

    }


    public int mySqrt(int x) {
        int left = 0, right = x, ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    public void fastSoft(int[] nums) {
        fastSoft(nums, 0, nums.length - 1, nums[0]);
    }

    private void fastSoft(int[] nums, int left, int right, int base) {
        while (right > left) {
            if(nums[right]<base){
                base = nums[right];
            }
        }
    }
}
