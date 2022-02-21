package demo;

import com.nzy.viewstudy.ListNode;
import com.nzy.viewstudy.TreeNode;

/**
 * @author niezhiyang
 * since 2022/2/18
 */
public class Solution {
    @org.junit.Test
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
        System.out.println("反转链表：" + printListNode(res));
    }





    public String printListNode(ListNode node) {
        StringBuilder sb = new StringBuilder();
        while (node != null) {
            sb.append(node.val);
            sb.append(" -> ");
            node = node.next;

        }
        return sb.toString();
    }

    public ListNode reverse(ListNode head) {

        //       申请节点，pre和 cur，pre指向null
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            //记录当前节点的下一个节点
            ListNode tmp = cur.next;
            //然后将当前节点指向pre,相当赋值，当当前的next节点给上一个
            cur.next = pre;
            //pre和cur节点都前进一位
            pre = cur;
            cur = tmp;
        }
        return pre;


    }

    @org.junit.Test
    public void test() {

    }
    public TreeNode getParentNode(TreeNode root , TreeNode p,TreeNode q){
        if(root==null){
            return null;
        }
        if(root==p||root==q){
            return root;
        }
        TreeNode left = getParentNode(root.left, p, q);
        TreeNode right = getParentNode(root.right, p, q);
        // 都不等于null 自己就是
        if(left!=null && right !=null){
            return root;
        }


        if(left == null){
            return right;
        }

        if(right==null){
            return left;
        }
        return  null;

    }












}




















































