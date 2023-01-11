package com.nzy.viewstudy;

import org.junit.Test;

/**
 * @author niezhiyang
 * since 12/16/21
 */
public class 背包 {

    @Test
    public void text() {

        int[] values = {6, 3, 5, 4, 6};
        int[] weights = {2, 2, 6, 5, 4};

        int sumWeight = 10;
        System.out.println(maxValue(values, weights, sumWeight));
        System.out.println(maxValue1(values, weights, sumWeight));
        System.out.println(maxValueExactly(values, weights, sumWeight));
        ;// 15
    }

    /**
     * 给你一个可装载重量为 W 的背包和 N 个物品，每个物品有重量和价值两个属性。
     * 其中第 i 个物品的重量为 wt[i]，价值为 val[i]，现在让你用这个背包装物品，
     * 最多能装的价值是多少？
     * 每个重量的只有一个
     * 0-1背包
     * values 是价值数组
     * weights 是重量数组
     * sumWeight 是总重量
     * 编号为K的物品 的价值是 value【k】，重量是 weight【k】 0<=k<n(value.length)
     * dp(i,j) 是 最大承重为j ,有前i件物品可选(只让你选前i件物品) 时的最大总价值， 0<=i<n  0<=j<=capacity
     * 可以认为
     * dp(5,10) 数组的长度为5， 最大承重是10
     * 初始值 dp[i,0] dp[0,j] 都是0
     * 最后一个不选，那么 j是不变的
     * dp(i,j) = dp(i-1,j)
     * 如果选择最后一件物品
     * dp(i,j) = values[i] + dp(i-1,j-capacity[i])
     * <p>
     * 所以是 两个最大值
     * dp(i,j) = Math.max(dp(i-1,j),values[i] + dp(i-1,j-capacity[i]))
     * <p>
     * 如果 j< weights[i] 只能不选择最后一件物品   dp(i-1,j)
     */

    //-----------0 1 背包，只能选一次
    public static int maxValue(int[] values, int[] weights, int capacity) {

        if (values == null || values.length == 0) {
            return 0;
        }
        if (weights == null || weights.length == 0) {
            return 0;
        }
        if (values.length != weights.length) {
            return 0;
        }
        if (capacity <= 0) {
            return 0;
        }

        int[][] dp = new int[values.length + 1][capacity + 1];

//        dp[0][j] = 0;
//        dp[i][0] = 0;
        for (int i = 1; i <= values.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                // 如果最后一件无法选 因为是从1 开始的 所以这里weights[i - 1]
                if (j < weights[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 选 或者不选 的最大值
                    dp[i][j] = Math.max(dp[i - 1][j]
                            //因为是从1 开始的 所以这里weights[i - 1]
                            , values[i - 1] + dp[i - 1][j - weights[i - 1]]);
                }

            }
        }
        return dp[values.length][capacity];
    }

    // 优化空间
    public static int maxValue1(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0) {
            return 0;
        }
        if (weights == null || weights.length == 0) {
            return 0;
        }
        if (values.length != weights.length) {
            return 0;
        }
        if (capacity <= 0) {
            return 0;
        }

        int[] dp = new int[capacity + 1];
        for (int i = 1; i <= values.length; i++) {
            for (int j = capacity; j >= weights[i - 1]; j--) {
                dp[j] = Math.max(dp[j], values[i - 1] + dp[j - weights[i - 1]]);
            }
        }
        return dp[capacity];
    }


    // 恰好 等于 capacity
    public static int maxValueExactly(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0) {
            return 0;
        }
        if (weights == null || weights.length == 0) {
            return 0;
        }
        if (values.length != weights.length) {
            return 0;
        }
        if (capacity <= 0) {
            return 0;
        }

        int[][] dp = new int[values.length + 1][capacity + 1];

//        dp[0][j] = 0;
//        dp[i][0] = 0;
        for (int i = 1; i <= values.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                // 如果最后一件无法选 因为是从1 开始的 所以这里weights[i - 1]
                if (j < weights[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 选 或者不选 的最大值
                    dp[i][j] = dp[i - 1][j]
                            //因为是从1 开始的 所以这里weights[i - 1]
                            + dp[i - 1][j - weights[i - 1]];
                }

            }
        }
        return dp[values.length][capacity];
    }

    // 完全背包 背包 正好等于 amount
    // dp[i][j] 的定义如下,若只使用前 i 个物品（可以重复使用），当背包容量为 j 时，有 dp[i][j] 种方法可以装满背包。
    // amount 为 0也算一种，什么都不选
    // * 输入：amount = 5, coins = [1, 2, 5]
    //[1,1,1,1,1]
    //[1,1,1,2]
    //[1,2,2]
    //[5]
    int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        // base case
        for (int i = 0; i <= n; i++)
            dp[i][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                // 如果当前的j大于 coins[i - 1]了 ,证明可以选择
                if (j >= coins[i - 1]) {
                    //          不选  +  选
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                } else {
                    // 只能不选
                    dp[i][j] = dp[i - 1][j];
                }

            }

        }
        return dp[n][amount];
    }

    // 完全背包优化空间
    int change1(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1; // base case
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j >= coins[i]) {
                    dp[j] = dp[j] + dp[j - coins[i]];
                }
            }
        }


        return dp[amount];
    }
//           「力扣」上的 0-1 背包问题：
//
//            「力扣」
//            「力扣」第 474 题：一和零（中等）；
//            「力扣」第 494 题：目标和（中等）；

//            「力扣」上的 完全背包问题：
//
//            「力扣」第 322 题：零钱兑换（中等）；
//            「力扣」第 518 题：零钱兑换 II（中等）；
//              279. 完全平方数
//                377. 组合总和 Ⅳ
//    139. 单词拆分

    /**
     * 第 416 题：分割等和子集（中等）；
     * 分割等和子集
     * 1.所有加起来不能是奇数
     * 2.分割出来必须等于 数组和的一半
     */

    public boolean canPartition(int[] nums) {
        int len = nums.length;
        // 题目已经说非空数组，可以不做非空判断
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 特判：如果是奇数，就不符合要求
        if ((sum % 2) == 1) {
            return false;
        }

        int target = sum / 2;
        // 创建二维状态数组，行：物品索引，列：容量（包括 0）
        boolean[][] dp = new boolean[len][target + 1];

        // 先填表格第 0 行，第 1 个数只能让容积为它自己的背包恰好装满
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }
        // 再填表格后面几行
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= target; j++) {
                // 直接从上一行先把结果抄下来，然后再修正
                dp[i][j] = dp[i - 1][j];

                if (nums[i] == j) {
                    dp[i][j] = true;
                    continue;
                }
                if (nums[i] < j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[len - 1][target];
    }
    /**
     * 494. 目标和
     * 给你一个整数数组 nums 和一个整数 target 。
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     * 输入：nums = [1,1,1,1,1], target = 3
     * 输出：5
     * 解释：一共有 5 种方法让最终目标和为 3 。
     * -1 + 1 + 1 + 1 + 1 = 3
     * +1 - 1 + 1 + 1 + 1 = 3
     * +1 + 1 - 1 + 1 + 1 = 3
     * +1 + 1 + 1 - 1 + 1 = 3
     * +1 + 1 + 1 + 1 - 1 = 3
     *
     * dp[i][j] 代表前i个数，target 是j
     */
}
