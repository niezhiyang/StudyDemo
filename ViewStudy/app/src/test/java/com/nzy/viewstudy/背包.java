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
     * <p>
     * 可以认为
     * dp(5,10) 数组的长度为5， 最大承重是10
     * <p>
     * 初始值 dp[i,0] dp[0,j] 都是0
     * 最后一个不选，那么 j是不变的
     * dp(i,j) = dp(i-1,j)
     * 如果选择最后一件物品
     * dp(i,j) = values[i] + dp(i-1,j-capacity[i])
     * <p>
     * <p>
     * 所以是 两个最大值
     * dp(i,j) = Math.max(dp(i-1,j),values[i] + dp(i-1,j-capacity[i]))
     * <p>
     * 如果 j<weights[i] 只能不选择最后一件物品   dp(i-1,j)
     */

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
            for(int j = capacity;j>=weights[i-1];j--){
                dp[j] = Math.max(dp[j],values[i-1]+dp[j-weights[i-1]]);
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
                    dp[i][j] = Math.max(dp[i - 1][j]
                            //因为是从1 开始的 所以这里weights[i - 1]
                            , values[i - 1] + dp[i - 1][j - weights[i - 1]]);
                }

            }
        }
        return dp[values.length][capacity];
    }
}
