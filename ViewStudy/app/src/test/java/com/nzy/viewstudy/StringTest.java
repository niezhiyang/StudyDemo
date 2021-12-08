package com.nzy.viewstudy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author niezhiyang
 * since 2020/10/19
 */
public class StringTest {
    @Test
    public void reverseTree() {

        String test = "ADOBECODEBANC";
        String res = "ABC";
        String res2 = "AOD";
        System.out.println("---" + minWindow(test, res));
//        System.out.println("---" + minWindow(test, res2));
//        String s = "cbaebabacd";
//        String p = "abc";
//        System.out.println("---" + minWindow2(s, p));


        String ss = "abaabcbb";
        String ss1 = "pwwkew";
        System.out.println("---" + minWindow3(ss));
        System.out.println("---" + minWindow3(ss1));


        String sss = "babad";
        String sss1 = "cbbd";
        System.out.println("---回文串" + minWindow4(sss));
        System.out.println("---回文串" + minWindow4(sss1));

    }


    /**
     * 题目：最小覆盖子串
     * 给你一个字符串 S、一个字符串 T 。请你设计一种算法，可以在 O(n) 的时间复杂度内，从字符串 S 里面找出：包含 T 所有字符的最小子串。
     * 输入：S = "ADOBECODEBANC", T = "ABC"
     * 输出："BANC"
     * 滑块思想
     * <p>
     * 滑块思想都是固定的
     * 只替换以下几步即可
     * 1. 匹配
     * 2. 算结果
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
        Iterator iter = need.entrySet().iterator();
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


    /**
     * 找到字符串中所有字母异位词
     * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引
     *
     * @return
     */
    public String minWindow2(String test, String res) {
        ArrayList<Integer> result = new ArrayList<>();

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

        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
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
                // todo 2 计算结果
                if ((right + 1 - left) == res.length()) {
                    result.add(left);
                }

                // 如果 left 在 要查找的串中，就从滑块中 value 减去1
                if (need.containsKey(test.charAt(left))) {
                    window.put(test.charAt(left), window.getOrDefault(test.charAt(left), 0) - 1);
                }
                left++;// 在前面或者后面 取决于 初始化时 -1 还是 0
            }

//            System.out.println(ansL+"---"+ansR);
//
//            result.add(test.substring(ansL, ansR));


        }
        return Arrays.toString(result.toArray());
//        return ansL == -1 ? "" : test.substring(ansL, ansR);
    }

    /**
     * 无重复字符的最长子串
     * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引
     * <p>
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * <p>
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * <p>
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     * <p>
     * 这个先移动左指针，然后移动右指针
     *
     * @return
     */
    public String minWindow3(String test) {

        HashMap<Character, Integer> window = new HashMap<>();
        int left = -1, right = 0;
        int maxLenth = 0;
        int resL = 0;
        int resR = 0;
        while (left < test.length() && left <= right) {
            left++;
            right = left;
            window.clear();
            // 如果没有重复的，就移动右窗口，
            while (!check1(window) && right < test.length()) {

                window.put(test.charAt(right), window.getOrDefault(test.charAt(right), 0) + 1);

                if (right - left > maxLenth) {
                    maxLenth = right - left;
                    resL = left;
                    resR = right;
                }


                right++;
            }
            if (window.containsKey(left)) {
                window.put(test.charAt(left), window.getOrDefault(test.charAt(right), 0) - 1);
            }

        }

        return test.substring(resL, resR);
//        return ansL == -1 ? "" : test.substring(ansL, ansR);
    }


    // 如果出现重复的就开始移动左窗口
    public boolean check1(HashMap<Character, Integer> window) {
        Iterator iter = window.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Integer val = (Integer) entry.getValue();
            if (val > 1) {
                return true;
            }
        }
        return false;
    }


    /**
     * 题目 最长回文子串
     * 给定一个字符串 s，找到 s 中最长的回文子串
     * <p>
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * <p>
     * 输入: "cbbd"
     * 输出: "bb"
     *
     * @param test
     * @return
     */
    public String minWindow4(String test) {

        HashMap<Integer, Character> window = new HashMap<>();
        for (int i = 0; i < test.length(); i++) {
            char c = test.charAt(i);
            window.put(i, c);
        }
        int left = -1, right = 0;
        int maxLenth = 0;
        int resL = 0;
        int resR = 0;
        while (left < test.length() && left <= right) {
            left++;
            right = left + 1;
            window.clear();
            while (check4(window, right, left) && right < test.length()) {
                if (right - left > maxLenth) {
                    maxLenth = right - left;
                    resL = left;
                    resR = right;
                }


                right++;
            }

        }

        return test.substring(resL, resR);
//        return ansL == -1 ? "" : test.substring(ansL, ansR);
    }


    // 是否符合
    public boolean check4(HashMap<Integer, Character> window, int right, int left) {
        for (int i = left; i < ((right - left + 1) / 2); i++) {
            if (window.get(i) != window.get(left - i)) {
                return false;
            }
        }
        return true;
    }


    @Test
    public void getData() {
        LinkedHashMap<String, String> map = new LinkedHashMap(10, 0.75f, true);
        map.put("123", "123");
        map.put("124", "124");
        map.put("1", "125");
        map.put("125", "126");

        String s = map.get("124");

        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getValue());
        }
    }

    class Parent{
        protected synchronized void getName(){

        }
    }

    class Son extends Parent{
        @Override
        protected  void getName() {
        }
    }

}
