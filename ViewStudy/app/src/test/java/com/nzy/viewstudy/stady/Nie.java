package com.nzy.viewstudy.stady;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author niezhiyang
 * since 2024/4/29
 */
public class Nie {
    //https://leetcode.cn/problems/move-zeroes/
    public void moveZeroes(int[] nums) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[right] != 0) {
                int numRight = nums[left];
                nums[left] = nums[right];
                nums[right] = numRight;
                left++;
            }
            right++;
        }
    }

    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        moveJiOu(nums);
        System.out.println(Arrays.toString(nums));
    }

    //https://www.nowcoder.com/questionTerminal/beb5aa231adc45b2a5dcc5b62c93f593?u_atoken=50210515-9ecb-4717-bb2f-5105515b6fd4&u_asession=01lBEXJ5iJK-xqW6oYZGYBehv9UE9_diPO1VLmeJeQOZZAATrvape1CnvPG9A52NwvX0KNBwm7Lovlpxjd_P_q4JsKWYrT3W_NKPr8w6oU7K-Li7-QNHLulm_9hi6RWM_7MJtBx3S14qt35vRMTfjp8mBkFo3NEHBv0PZUm6pbxQU&u_asig=05TrrStJKDQyRL6Pe0d0BBudB2njKw7Ild18MC7W8uajnCmX7k-lOaZub8O9ZeALTgWuIgbONlBHRXu1-B12kifP4LHPjX5907K-txzQcH3UmpMabM_J-UKBMf6nWHl-5wjPPn-XeGXDreuo9MsIM9WLZnSPGDnr5S6-RvOtHKEgP9JS7q8ZD7Xtz2Ly-b0kmuyAKRFSVJkkdwVUnyHAIJzToa8Vt0r92plzbIg9M640pZXxwQHSsLQNDfkbILggi41YpiKJZCArXmgv6vSk2RSO3h9VXwMyh6PgyDIVSG1W84e5kNRA7V7_g0dyd2LP3xxPV46rxeunwwnCjSohRo2NjjroiW10CAo3omzWtvqz3EekJQaPUEiizeJrvIM-UVmWspDxyAEEo4kbsryBKb9Q&u_aref=6mGzVj%2F4f%2BRG0%2BwoVXwsnwXRSu4%3D
    public void moveJiOu(int[] nums) {
        //[1, 2, 3, 4, 5]
        //[1,3,2,4,5]
        //[1,3,5,4,2]
        int left = 0;
        int right = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[right] % 2 != 0) {
                int numRight = nums[right];
                for (int j = right; i > left; j--) {
                    nums[j]=nums[j-1];
                }
                nums[left] = numRight;

                left++;
            }
            right++;
        }
    }
}
