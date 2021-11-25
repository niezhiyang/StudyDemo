package com.nzy.viewstudy;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

/**
 * 链表
 */
public class LinkListTest {
    /**
     * 链表反转
     */
    @Test
    public void reverseListNode() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        System.out.println(node1);

        ListNode res = reverse(node1);
        System.out.println("反转链表：" + res);

        int[] arr1 = new int[]{2, 11, 3, 15, 9};
        System.out.println("冒泡排序：" + Arrays.toString(sortBubble(arr1)));
        System.out.println("选择排序：" + Arrays.toString(sortSelcet(arr1)));

        int[] arr = new int[]{2, 7, 9, 11, 15};
        int target = 18;
        int[] ints = twoSum(arr, target);
        System.out.println("两数之和：" + Arrays.toString(ints));


        System.out.println("二分查找：" + binarySearch(arr, 11));
        System.out.println("二分查找：" + binarySearchRecursion(arr, 11, 0, arr.length - 1));


    }


    /**
     * 冒泡
     * 依次比较两个相邻的两个数，大的往后挪动，当一轮下来，最大的也就在最后一个了
     * 时间复杂度 o(n^2)
     * 空间复杂度 o(1)
     */
    public int[] sortBubble(int[] nums) {
        int[] arr = Arrays.copyOf(nums, nums.length);
        // 外圈只控制比较次数
        for (int i = 1; i < arr.length; i++) {
            // 因为比较完一轮之后，最大的已经在最后一个了，所以不用再比较最后一个了
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }

            }
        }
        return arr;
    }

    /**
     * 选择
     * 从第一个开始逐步和后面的比较，如果大于，就交换，继续往下比较。比较一圈之后，最大的也就放到最后了
     * 把最小的放到第一个
     * 时间复杂度 o(n^2)
     * 空间复杂度 o(1)
     */
    public int[] sortSelcet(int[] nums) {
        // 外圈只控制比较次数  总共要经过 N-1 轮比较
        int[] arr = Arrays.copyOf(nums, nums.length);
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                // 把小的放前面
                if (arr[j] < arr[i]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }

            }
            System.out.println("选择排序：" + Arrays.toString(arr));
        }
        return arr;
    }

    /**
     * 二分查找 非递归
     */
    public int binarySearch(int[] nums, int target) {
        if (nums.length <= 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
//            int[] arr = new int[]{2, 7, 11, 15}; 11
            int mid = (left + right) / 2;
            if (target == nums[mid]) {
                return (left + right) / 2;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            }
        }

        return -1;
    }

    @Test
    public void reverseListNodes() {
        int[] arr = new int[]{2, 7, 9, 11, 15};
        int[] a = {3, 5, 11, 17, 21, 23, 28, 30, 32, 50, 64, 78, 81, 95, 101};

//        System.out.println("二分查找：" + binarySearchRecursion(a, 81, 0, arr.length - 1));
        System.out.println("二分查找：" + binarySearchRecursion(arr, 11, 0, arr.length - 1));
        System.out.println("二分查找：" + search(0, arr.length - 1, 11, arr));
    }

    /**
     * 二分查找 递归
     */
    public int binarySearchRecursion(int[] nums, int target, int startIndex, int endIndex) {
        int[] arr = Arrays.copyOf(nums, nums.length);
        if (endIndex >= startIndex) {
            int mid = (startIndex + endIndex) / 2;
            if (target == arr[mid]) {
                return mid;
            } else if (target > arr[mid]) {
                return binarySearchRecursion(arr, target, mid + 1, endIndex);
            } else if (target < arr[mid]) {
                return binarySearchRecursion(arr, target, startIndex, mid - 1);
            }
        }
        return -1;
    }

    public static int search(int start, int end, int target, int[] arr) {
        if (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (target > arr[mid]) {
                //target >和=都判断过了arr[mid] 那么下次开始的位置应该越过mid的后一个位置
                return search(mid + 1, end, target, arr);
            } else if (target < arr[mid]) {
                //target <和=都判断过了arr[mid] 那么下次结束的位置应该越过end到mid的前一个位置
                return search(start, mid - 1, target, arr);
            }
        }

        return -1;
    }

    @Test
    public void reverseListNode2() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        System.out.println(node1);

        ListNode res = reverse(node1);
        System.out.println("反转链表：" + res);
    }

    /**
     * 反转链表
     * 时间复杂度是 o(n)
     * 空间复杂度   o(1)
     */
    public ListNode reverse(ListNode head) {

////        申请节点，pre和 cur，pre指向null
        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp = null;
        while (cur != null) {
            //记录当前节点的下一个节点
            tmp = cur.next;
            //然后将当前节点指向pre,相当赋值，当当前的next节点给上一个
            cur.next = pre;
            //pre和cur节点都前进一位
            pre = cur;
            cur = tmp;
        }
        return pre;


    }


    /**
     * 两数之和
     * <p>
     * 时间复杂度是 o(n)
     * 空间复杂度   o(n)
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> recorder = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int first = target - nums[i];
            if (recorder.containsKey(first)) {
                return new int[]{recorder.get(first), i};
            }
            recorder.put(nums[i], i);


        }

        return new int[]{0};
    }


    @Test
    public void reverseTreeNode2() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        System.out.println(node1);

        ListNode res = reverse(node1);
        System.out.println("反转链表：" + res);
    }




}