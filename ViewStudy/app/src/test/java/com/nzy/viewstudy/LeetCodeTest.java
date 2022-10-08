package com.nzy.viewstudy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
        System.out.println("----" + getMaxw(ints));
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

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        if (nums == null || nums.length < 3) {
            return result;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {

            if (nums[i] > 0) {
                return result;
            }

            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = nums.length - 1;
            while (right > left) {
                int temp = nums[i] + nums[left] + nums[right];
                if (temp == 0) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);

                    while (right > left && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (right > left && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                } else if (temp > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }


        return result;

    }

    /**
     * 岛屿最大面积
     * https://leetcode.cn/problems/max-area-of-island/
     * <p>
     * [
     * 1. [0,0,1,0,0,0,0,1,0,0,0,0,0],
     * 2. [0,0,0,0,0,0,0,1,1,1,0,0,0],
     * 3. [0,1,1,0,1,0,0,0,0,0,0,0,0],
     * 4. [0,1,0,0,1,1,0,0,1,0,1,0,0],
     * 5. [0,1,0,0,1,1,0,0,1,1,1,0,0],
     * 6. [0,0,0,0,0,0,0,0,0,0,1,0,0],
     * 7. [0,0,0,0,0,0,0,1,1,1,0,0,0],
     * 8 [0,0,0,0,0,0,0,1,1,0,0,0,0]
     * ]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/max-area-of-island
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param grid
     * @return
     */
    @Test
    public void test() {
        int[][] array = {
                  {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}
                , {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0}
                , {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };
        int[][] array1 = {
                {0, 1,0}
                , {1, 1,0}

        };
        maxAreaOfIsland(array);
        System.out.println(maxNum);
    }

    int num = 0;

    int maxNum = 1;

    public int maxAreaOfIsland(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 如果其实位置是 1，往下走
                if (grid[i][j] == 1) {
                    dfs1(grid, i, j);
                    maxNum = Math.max(maxNum, num);
                    num = 1;
                }
            }
        }
        return count;


    }

    void dfs1(int[][] grid, int row, int column) {
        // 判断 base case
        if (!inArea(grid, row, column)) {
            return;
        }
        // 如果这个格子不是岛屿，直接返回
        if (grid[row][column] != 1) {
            return;
        }
        // 将格子标记为「已遍历过」
        grid[row][column] = 2;
        num++;

        // 访问上、下、左、右四个相邻结点
        dfs1(grid, row - 1, column);
        dfs1(grid, row + 1, column);
        dfs1(grid, row, column - 1);
        dfs1(grid, row, column + 1);
    }

    // 判断坐标 (r, c) 是否在网格中
    boolean inArea(int[][] grid, int row, int column) {
        return 0 <= row && row < grid.length
                && 0 <= column && column < grid[0].length;
    }


}
