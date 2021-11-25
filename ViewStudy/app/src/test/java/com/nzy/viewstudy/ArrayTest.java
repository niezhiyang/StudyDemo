package com.nzy.viewstudy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author niezhiyang
 * since 2020/11/3
 */
public class ArrayTest {

    // [1,2,0,4,0,0,3,0,1,1,0]
    int[] arr = new int[]{};

    @Test
    public void text() {

        System.out.println(Arrays.toString(sortByJiOu(new int[]{1, 2, 3, 4, 9, 7})));
        ;
    }

    public int findRepeatNumber(int[] nums) {
        ArrayList<Integer> map = new ArrayList();
        for (int i = 0; i < nums.length; i++) {
            if (map.contains(nums[i])) {
                return nums[i];
            } else {
                map.add(nums[i]);
            }
        }
        return -1;
    }

    public int[] sortByJiOu(int[] nums) {
        // 遍历
        for (int i = 0; i < nums.length; i++) {
            // 偶数
            if (nums[i] % 2 == 0) {
                for (int j = nums.length - i; j > 0; j--) {
                    if (nums[nums.length - j] % 2 != 0) {
                        try {
                            int temp = 0;
                            temp = nums[i];
                            nums[i] = nums[nums.length - j];
                            nums[nums.length - j] = temp;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }


        // 左右指针
        int left = 0, right = nums.length - 1;

        while (left < right) {
            if (nums[left] % 2 == 0) {

            } else {
                left++;
                continue;
            }
            if (nums[right] % 2 != 0) {

            } else {
                right--;
                continue;
            }

            if (nums[left] % 2 == 0 && nums[right] % 2 != 0) {
                int temp = 0;
                temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }

        }

        return nums;

    }

    @Test
    public void text1() {
        moveZeroes(new int[]{1, -2, 3, -4, 5, -6});
    }

    //给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        int left = 0;// 已经移动完的 末尾
        int right = 0; // 没有移动的 起始
        while (right < n) {
            if (nums[right] % 2 == 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
            right++;
        }

        System.out.println(Arrays.toString(nums));

    }

}
