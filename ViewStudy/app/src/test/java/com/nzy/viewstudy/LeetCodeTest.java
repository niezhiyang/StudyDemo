package com.nzy.viewstudy;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author niezhiyang
 * since 2020/10/28
 */
public class LeetCodeTest {
    @Test
    public void text() {
        String t = "ADOBECODEBANC";
        String s = "ABC";
        System.out.println("----" + minWindow(s, t));
        ;
        int[] ints = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        System.out.println(max + "----" + maxSum(ints, ints.length - 1));
        System.out.println("----"+getMaxw(ints));
    }

    HashMap<Character, Integer> window = new HashMap<>();
    HashMap<Character, Integer> need = new HashMap<>();

    public String minWindow(String s, String t) {

        for (int i = 0; i < s.length(); i++) {
            need.put(s.charAt(i), need.getOrDefault(s.charAt(i), 0) + 1);
        }
        int left = 0;
        int right = -1;

        int leftResult = -1;
        int rightResult = -1;
        int minSize = Integer.MAX_VALUE;

        // 先移动right 直到包含 这个串之后停止
        while (right < t.length()) {
            right++;
            if (right < t.length()) {
                window.put(t.charAt(right), window.getOrDefault(t.charAt(right), 0) + 1);
                while (isMatcher() && left <= right) {

                    if (right < t.length() && right - left + 1 < minSize) {
                        leftResult = left;
                        rightResult = right;
                        minSize = right - left + 1;
                    }
                    if (window.containsKey(t.charAt(left))) {
                        window.put(t.charAt(left), window.getOrDefault(t.charAt(left), 0) - 1);
                    }

                    left++;


                }
            }
        }
        return rightResult != -1 ? t.substring(leftResult, rightResult + 1) : "";
    }

    private boolean isMatcher() {
        // 遍历need ，看window里面是否符合
        for (Character key : need.keySet()) {
            if (window.getOrDefault(key, 0) < need.get(key)) {
                return false;
            }
        }
        return true;
    }


    int max = Integer.MIN_VALUE;

    public int maxSum(int[] nums, int i) {

        if (i == 0) {
            max = nums[0];
            return nums[0];
        }
//        包含当前点在内的最大自序和 = 该点之前连续的最大和 + 该点值 或 该点值
        int preMax = maxSum(nums, i - 1);
        int currMax = Math.max(preMax + nums[i], nums[i]);
        max = Math.max(currMax, max);
        return currMax;
    }


    public int getMaxw(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;

    }

}
