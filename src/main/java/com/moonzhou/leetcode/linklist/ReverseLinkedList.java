package com.moonzhou.leetcode.linklist;

/**
 * @Description 链表反转
 * @Author moon-zhou <ayimin1989@163.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/10/27
 */
public class ReverseLinkedList {

    public static void main(String[] args) {

        Node A1 = initLinkedList();
        System.out.println(A1);

        Node A1Reversion = reverseListRecursive(A1);
        System.out.println(A1Reversion);

        Node B1 = initLinkedList();
        System.out.println(B1);

        Node B1Reversion = reverseListHeadInsertion(B1);
        System.out.println(B1Reversion);
    }

    /**
     * 初始化链表，返回头结点
     * @return
     */
    private static Node initLinkedList() {
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
        A5.setNext(null);

        return A1;
    }

    /**
     * 递归
     * @param node
     * @return
     */
    public static Node reverseListRecursive(Node node) {
        // 如果节点的当前为null（链表为空），或者链表的下个节点为null（到达链尾），跳出递归
        if (node == null || node.next == null) {
            return node;
        }

        // 否则当前节点的下一个节点，进行反转
        Node next = node.next;
        Node newHead = reverseListRecursive(next);

        // 下一个节点的，反转后的下一个节点，不是下一个的下一个节点（next.next），而是当前节点(next.pre)
        next.next = node;

        // 当前节点每一次递归都作为当次的新的尾节点，下一次递归时都会被上一行赋上新值，直到最后一个节点（原始的头结点）变成反转后的尾节点
        node.next = null;

        return newHead;
    }

    /**
     * 头插法
     * @param head
     * @return
     */
    public static Node reverseListHeadInsertion(Node head) {
        // 初始化一个null节点
        Node newHead = new Node();

        while (head != null) {
            Node next = head.next;

            // 原有节点保持，其next即为新head的next，然后整个head再作为新head的next，达到头插法的目的
            head.next = newHead.next;
            newHead.next = head;

            head = next;
        }

        // 刨除当前null节点，返回其next即为新的反转后的链表头
        return newHead.next;
    }

}
