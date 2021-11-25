package com.nzy.viewstudy;

public class TreeNode implements Cloneable {
    public TreeNode clone()
    {
        TreeNode o=null;
        try
        {
            o=(TreeNode)super.clone();//Object 中的clone()识别出你要复制的是哪一个对象。
        }
        catch(CloneNotSupportedException e)
        {
            System.out.println(e.toString());
        }
        return o;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "value=" + val +
                ", right=" + right +
                ", left=" + left +
                '}';
    }

    public int val;

    public TreeNode(int val) {
        this.val = val;
    }


    public TreeNode left;
    public TreeNode right;
}
