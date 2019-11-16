package com.moonzhou.leetcode.linklist;

/**
 * @Description 从有序链表里删除重复的节点
 *
 * 普通算法：遍历当前节点和下一节点，如果一样，则当前节点指向下一节点的下一节点
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
 *
 * @Author moon-zhou <ayimin1989@163.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/11/16
 */
public class RemoveDuplicateNodeInOrderedLinkedList {

    public static void main(String[] args) {
        test1();

        System.out.println("------------");

        test2();

        System.out.println("------------");

        test3();
    }

    /**
     * test:
     *     Given 1->1->2, return 1->2.
     */
    private static void test1() {
        // 初始化节点
        Node A10 = new Node("1");
        Node A11 = new Node("1");
        Node A2 = new Node("2");

        // 初始化链表
        A10.setNext(A11);
        A11.setNext(A2);

        System.out.println(A10);

//        System.out.println(removeDuplicateNodes(A10));
        System.out.println(removeDuplicateNodesNormal(A10));
    }

    /**
     * test:
     *     Given 1->2->2, return 1->2.
     */
    private static void test2() {
        // 初始化节点
        Node A10 = new Node("1");
        Node A11 = new Node("2");
        Node A2 = new Node("2");

        // 初始化链表
        A10.setNext(A11);
        A11.setNext(A2);

        System.out.println(A10);

//        System.out.println(removeDuplicateNodes(A10));
        System.out.println(removeDuplicateNodesNormal(A10));
    }

    /**
     * test:
     *     Given 1->1->2->3->3, return 1->2->3.
     */
    private static void test3() {
        // 初始化节点
        Node A10 = new Node("1");
        Node A11 = new Node("1");
        Node A2 = new Node("2");
        Node A30 = new Node("3");
        Node A31 = new Node("3");

        // 初始化链表
        A10.setNext(A11);
        A11.setNext(A2);
        A2.setNext(A30);
        A30.setNext(A31);

        System.out.println(A10);

        System.out.println(removeDuplicateNodes(A10));
        System.out.println(removeDuplicateNodesNormal(A10));
    }

    public static Node removeDuplicateNodes(Node head) {
        /*
         * 无节点返回null(传入的当前节点为null，也可以说成返回当前节点)
         * 最后一个节点时，返回当前节点
         */
        if (null == head || null == head.next) {
            return head;
        }

        // 递归查找下一个节点的值是什么
        head.next = removeDuplicateNodes(head.next);

        // 当前节点值和下一个节点值相等，返回下一个节点，否则返回当前节点
        return head.item == head.next.item ? head.next : head;
    }

    /**
     * 普通一次遍历，当前节点值与下一个节点值相同，当前节点指向下一个节点的节点
     * @param head
     * @return
     */
    public static Node removeDuplicateNodesNormal(Node head) {

        // 当前节点
        Node nowNode = head;

        // 下一节点
        Node nextNode = head.next;

        while (null != nextNode) {

            // 当前节点和下一节点的值相等，当前节点指向下一节点的下一节点，当前节点不会发生变化
            if (nowNode.item == nextNode.item) {
                nowNode.next = nextNode.next;
            } else {
                // 当前节点和下一节点的值不同，则当前节点变为下一节点
                nowNode = nextNode;
            }

            // 下一节点，必然为下一节点的下一节点，不受上两个节点的影响
            nextNode = nextNode.next;
        }

        return head;
    }
}
