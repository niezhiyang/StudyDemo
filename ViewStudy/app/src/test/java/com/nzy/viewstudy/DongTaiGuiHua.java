package com.nzy.viewstudy;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author niezhiyang
 * since 9/14/21
 */
public class DongTaiGuiHua {
    @Test
    public void test() {

    }


    /**
     * 不同路径
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * <p>
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * <p>
     * 问总共有多少条不同的路径？
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[n][m];

        // 先把第一列 赋值为1
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        // 再把第一行赋值为1
        for (int i = 0; i < m; i++) {
            dp[0][i] = 1;
        }


        // 其他的遍历即可
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[n - 1][m - 1];

    }


    /**
     * 最小路径和
     * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     *
     * @return
     */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length, columns = grid[0].length;
        int[][] dp = new int[rows][columns];
        dp[0][0] = grid[0][0];
        // 第一行
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        // 第一列
        for (int j = 1; j < columns; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        // 然后加就可以了
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                // 最后加上 自己的数
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[rows - 1][columns - 1];

    }


    /**
     * 不同路径 有障碍物
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     * <p>
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     * <p>
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/unique-paths-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }

        // 定义 dp 数组并初始化第 1 行和第 1 列。
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n && obstacleGrid[0][j] == 0; j++) {
            dp[0][j] = 1;
        }

        // 根据状态转移方程 dp[i][j] = dp[i - 1][j] + dp[i][j - 1] 进行递推。
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];


    }

    /**
     * 爬楼梯
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * <p>
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢
     *
     * @param n
     * @return
     */
    public int climbStairs1(int n) {
        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }
        int pre = 1;
        int now = 2;
        //别忘了临界值 是有 == n的
        for (int i = 3; i <= n; i++) {
            int temp = now;
            now = pre + now;
            pre = temp;
        }


        return now;

    }

    /**
     * 输入: [2,3,-2,4]
     * 输出: 6
     * 解释: 子数组 [2,3] 有最大乘积 6。
     * 乘积最大子数组
     * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
     */

    public int maxProduct(int[] nums) {
        // dp[i] = Math.max(dp[i-1],dp[i-1]*nums[i])
        int max = nums[0], min = nums[0], ans = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; ++i) {
            //            以前最大乘以当前值，        当前值 和 以前最小的乘以当前值，可能是负数
            max = Math.max(max * nums[i], Math.max(nums[i], min * nums[i]));
            min = Math.min(min * nums[i], Math.min(nums[i], max * nums[i]));
            ans = Math.max(max, ans);
        }
        return ans;

    }

    /**
     * 打家劫舍
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * <p>
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额
     *
     * @param nums
     * @return
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


        int result = dp[0];
        for (int i = 1; i < nums.length; i++) {
            System.out.println(dp[i]);
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    /**
     * 零钱兑换
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     * <p>
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     * <p>
     * 你可以认为每种硬币的数量是无限的。
     * <p>
     * coins = [1, 2, 5], amount = 11
     * 凑成面值为 1111 的最少硬币个数可以由以下三者的最小值得到：
     * <p>
     * 凑成面值为 10 的最少硬币个数 + 面值为 1 的这一枚硬币；
     * 凑成面值为 9 的最少硬币个数 + 面值为 2 的这一枚硬币；
     * 凑成面值为 6 的最少硬币个数 + 面值为 5 的这一枚硬币。
     * dp[11] = min (dp[10] + 1, dp[9] + 1, dp[6] + 1)
     *
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount <= 0) return -1;
        return coindp(coins, amount);
    }

    //状态：金额
    //转移：一个硬币，这个硬币的金额在coins[]数组中遍历
    //dp函数：输入金额，返回最少硬币数
    public int coindp(int[] coins, int amount) {
        int[] dp = new int[amount + 1];//最多的硬币情况是全部是1元，共有amount个硬币，共有amount+1个状态，amount+1个金额
        Arrays.fill(dp, amount + 1);//必须将所有的dp赋最大值，因为要找最小值
        dp[0] = 0;//自底向上，金额为0，最小硬币数为0
        for (int i = 1; i <= amount; i++) {//自底向上
            for (int coin : coins) {//遍历coins的金额
                if (i >= coin) {
                    //am-coin 必须大于0，否则数组溢出
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);//金额为11的最小硬币数 和 金额为(11-一个面值)的最小硬币数+1 比较最小值

                }

            }
        }
        return dp[amount] > amount ? -1 : dp[amount];//返回金额为amount的最小硬币数 根据测试用例判断dp[amout]>amount
    }

    /**
     * [2]
     * [3,4]
     * [6,5,7]
     * [4,1,8,3]
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

    @Test
    public void test1() {
        int[] array = new int[]{2, 3, 1, 1, 4};
        System.out.println(canJump(array));
        System.out.println(canJump1(array));
    }

    /**
     * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * [2,3,1,1,4]
     * 判断你是否能够到达最后一个下标。
     *
     * @param nums
     * @return
     */
    // 从后往前来，看看 length -2 是否大于1 ， length -3 是否大于2 ， length -4 是否大于3，以此类推
    public boolean canJump(int[] nums) {
        int len = nums.length;
        int end = len - 1;
        for (int i = len - 2; i >= 0; i--) {
            if (nums[i] >= end - i) {
                end = i;
            }
        }
        return end == 0;
    }

    public boolean canJump1(int[] nums) {
        if (nums == null) {
            return false;
        }
        //前n-1个元素能够跳到的最远距离
        int k = 0;
        for (int i = 0; i <= k; i++) {
            //第i个元素能够跳到的最远距离
            int temp = i + nums[i];
            //更新最远距离
            System.out.println("k= " + k + "    i= " + i + "    nums[i]= " + nums[i]);
            k = Math.max(k, temp);

            //如果最远距离已经大于或等于最后一个元素的下标,则说明能跳过去,退出. 减少循环
            if (k >= nums.length - 1) {
                return true;
            }
        }
        //最远距离k不再改变,且没有到末尾元素
        return false;
    }

    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            result = Math.max(result, dp[i]);
        }
        return 1;
    }


}