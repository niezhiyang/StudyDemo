package com.nzy.viewstudy;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @author niezhiyang
 * since 2020/10/22
 */
public class ListNodeTest {
    @Test
    public void aboutListNode() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        System.out.println("原始链表：" + printListNode(node1));

        System.out.println("反转链表：" + printListNode(revertListNode(node1)));


        ListNode node11 = new ListNode(2);
        ListNode node12 = new ListNode(3);
        ListNode node13 = new ListNode(6);
        ListNode node14 = new ListNode(8);
        node11.next = node12;
        node12.next = node13;
        node13.next = node14;


        ListNode node111 = new ListNode(2);
        ListNode node112 = new ListNode(3);
        ListNode node113 = new ListNode(6);
        ListNode node114 = new ListNode(8);
        node111.next = node112;
        node112.next = node113;
        node113.next = node114;


        ListNode node21 = new ListNode(1);
        ListNode node22 = new ListNode(2);
        ListNode node23 = new ListNode(3);
        ListNode node24 = new ListNode(4);
        node21.next = node22;
        node22.next = node23;
        node23.next = node24;
        System.out.println("倒数第K个：" + printListNode(getkNode(node11, 2)));

        System.out.println("合并有序链表：" + printListNode(mergeTwoLists(node21, node111)));
    }

    // pre 和 now 互换，然后进入下个循环

    /**
     * 题目   反转链表
     *
     * @param head
     * @return
     */
    public ListNode revertListNode(ListNode head) {
        // 这个也把原始链表给改变了,应该copy一份
        ListNode now = head;
        ListNode pre = null;
        while (now != null) {

            // 把next 指向前一个
            ListNode temp = now.next;
            now.next = pre;

            //交换
            pre = now;
            now = temp;

        }
        return pre;

    }


    /**
     * 题目：环形链表
     * 方式1： 链表是否有环，快慢指针
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     * <p>
     * 方式2： 直接遍历，把每个node 都放到一个集合中，当遍历到这个集合有这个node的时候，证明有环
     */


    public boolean isHaveCycle(ListNode head) {
        ListNode fastNode, slowNode;
        fastNode = head;
        slowNode = head;
        while (fastNode != null && fastNode.next != null) {
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
            if (fastNode == slowNode) {
                return true;
            }
        }
        return false;
    }

    /**
     * 两个链表的第一个公共节点
     * 判断两个链表是否就交叉
     * 方式1： 把 一个 完成的链表存放到一个集合里面
     * 然后遍历 第一个链表，看集合里面是否存在，如果有，这个点就是交叉点
     * 时间复杂度0（n+m）
     * 方式2： 先让长的先走，next，当走到 和 短的一样长度的时候,两个同时走，当两个节点相等的时候，证明有交叉
     * 时间复杂度 O(max(n,m))
     */
    public ListNode theFirstNode(ListNode node1, ListNode node2) {
        ArrayList<ListNode> nodes = new ArrayList<>();
        while (node1 != null) {
            nodes.add(node1);
            node1 = node1.next;
        }

        while (node2 != null) {
            if (nodes.contains(node2)) {
                return node2;
            }
            node2 = node2.next;
        }
        return null;

    }


    // 链表中倒数第k个节点
    // 时间复杂度 N
    // 空间复杂度 1
    public ListNode getkNode(ListNode head, int k) {
        ListNode fastNode, slowNode;
        slowNode = head;
        fastNode = head;

        for (int i = 0; i < k; i++) {
            if (fastNode != null) {
                fastNode = fastNode.next;
            }

        }

        while (fastNode != null) {
            slowNode = slowNode.next;
            fastNode = fastNode.next;
            if (fastNode.next == null) {
                return slowNode;
            }

        }
        return null;
    }


    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * 先构建一个新的链表
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     *
     * @return
     */
    public ListNode mergeTwoLists(ListNode node1, ListNode node2) {
        // 先创建一个新的节点
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (node1 != null && node2 != null) {
            if (node1.value <= node2.value) {
                prev.next = node1;
                node1 = node1.next;
            } else {
                prev.next = node2;
                node2 = node2.next;
            }
            prev = prev.next;
        }

        // 合并后 node1 和 node2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = node1 == null ? node2 : node1;

        return prehead.next;
    }


    public String printListNode(ListNode node) {
        StringBuffer sb = new StringBuffer();
        while (node != null) {
            sb.append(node.value);
            sb.append(" -> ");
            node = node.next;

        }
        return sb.toString();
    }

}
