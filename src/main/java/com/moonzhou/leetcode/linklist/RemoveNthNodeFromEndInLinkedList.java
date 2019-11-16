package com.moonzhou.leetcode.linklist;

/**
 * @Description 删除链表里倒数第n个数
 * @Author moon-zhou <ayimin1989@163.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/11/16
 */
public class RemoveNthNodeFromEndInLinkedList {
    public static void main(String[] args) {

        // 初始化节点
        Node A1 = new Node("a1");
        Node A2 = new Node("a2");
        Node A3 = new Node("a3");
        Node A4 = new Node("a4");
        Node A5 = new Node("a5");

        // 初始化链表
        A1.setNext(A2);
        A2.setNext(A3);
        A3.setNext(A4);
        A4.setNext(A5);
        A5.setNext(null); // 可以不用，默认为null

        System.out.println(A1);

//        System.out.println(removeNthNodeFromEndInLinkedList(A1, 7));
//        System.out.println(removeNthNodeFromEndInLinkedList(A1, 5));
//        System.out.println(removeNthNodeFromEndInLinkedList(A1, 2));

//        System.out.println(removeNthNodeFromEndInLinkedListNormal(A1, 7));
//        System.out.println(removeNthNodeFromEndInLinkedListNormal(A1, 5));
        System.out.println(removeNthNodeFromEndInLinkedListNormal(A1, 2));
    }

    /**
     * # # # # # & &
     * & & # # # # #
     *
     * @param head 链表头节点
     * @param n    倒数的节点数
     * @return 如果n超过链表长度，返回头结点
     */
    public static Node removeNthNodeFromEndInLinkedList(Node head, int n) {

        Node fast = head;

        // 正向遍历第n个（从1开始）
        while (n-- > 0) {
            if (null != fast) {
                fast = fast.next;
            } else {
                // 如果n超过链表长度，返回头结点，即不删除任何元素
                return head;
            }
        }

        if (fast == null) {
            return head.next;
        }

        Node slow = head;
        while (fast.next != null) {
            // fast接着往下的数目，即为正方向做倒数的节点
            fast = fast.next;

            // 倒数方向的删除节点的上一个（正数方向）node
            slow = slow.next;
        }

        // 删除核心：删除slow的下一个节点
        slow.next = slow.next.next;

        return head;
    }

    public static Node removeNthNodeFromEndInLinkedListNormal(Node head, int n) {
        Node lengthIteratorNode = head;

        // 计算链表长度(可以考虑优化node节点，往上抽象LinkList<Node>)
        int listLength = 0;

        while (null != lengthIteratorNode) {
            listLength++;
            lengthIteratorNode = lengthIteratorNode.next;
        }

        // 如果倒数的数目大于链表本身的数目，则不删除任何节点，直接返回头结点head
        if (n > listLength) {
            return head;
        } else if (n == listLength) {
            // 删除第一个节点（单向链表头结点需要特殊处理）
            return head.next;
        }

        /*
         * 抽象数据公式(以下索引皆从1开始)：
         * 倒数n + 正数X = （总数 + 1）
         * --》 正数X = （总数 + 1） - 倒数n
         */
        int x = listLength + 1 - n;

        // 从第一个开始算删除节点，x则要删除一个
        Node removeIteratorNode = head;
        x--;

        // 不能到 > 0， 大于0到的节点为删除的节点，因为要做删除（单向链表），所以要到正数这个节点的上一个节点即可，才可以进行删除操作
        while (x-- > 1) {
            removeIteratorNode = removeIteratorNode.next;
        }

        removeIteratorNode.next = removeIteratorNode.next.next;

        return head;
    }
}
