package com.nzy.viewstudy.stady;

import java.util.HashMap;
import java.util.Map;

/**
 * @author niezhiyang
 * since 2024/3/4
 */
public class SSSS {
    class Node {
        public int key, value;
        public Node next;
        public Node pre;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

    }

    class LRUCache {

        private Node head;
        private Node tail;

        private int size;
        private HashMap<Integer, Node> map = new HashMap<>();

        public LRUCache(int capacity) {
            this.size = capacity;
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.pre = head;
        }

        public void put(int key, int value) {
            Node node = map.get(key);
            if (node == null) {
                // 证明没有，直接放到最前面
                if (map.size() == size) {
                    // 移除末尾的
                    map.remove(tail.key);
                    Node tailNext = tail.next;
                    tailNext.next = null;
                    tail = tailNext;

                }
                Node nodeTemp = new Node(key, value);
                // 添加到头部
                head.next.pre = nodeTemp;
                nodeTemp.next = head.next;
            } else {
                // 不等于null ，证明里面已经有了 ,抽出来
                node.next.pre = node.pre;
                node.pre.next = node.next;

                /// 放到头部
                node.next = head;
                head.pre = node;
                node.pre = null;
            }
        }


        public int get(int key) {
            Node node = map.get(key);
            if (node == null) {
                return -1;
            }
            //证明youzh
            return -1;
        }
    }

}


