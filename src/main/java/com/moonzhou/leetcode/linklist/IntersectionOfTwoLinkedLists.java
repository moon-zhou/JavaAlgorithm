package com.moonzhou.leetcode.linklist;

/**
 * @Description 查找两个链表的交点，要求时间复杂度为 O(N)，空间复杂度为 O(1)。如果不存在交点则返回 null。
 *
 * 核心算法：
 *     两个链表：A B
 *     如果有交点，交点分别分割两个链表，即交点前+交点后（交点放在交点后）
 *     A:          a1 → a2
 *                       ↘
 *                        c1 → c2 → c3
 *                       ↗
 *     B:    b1 → b2 → b3
 *
 *     设 A 的长度为 a + c，B 的长度为 b + c，其中 c 为尾部公共部分长度，可知 a + c + b = b + c + a。
 *
 *     当访问 A 链表的指针访问到链表尾部时，令它从链表 B 的头部开始访问链表 B；
 *     同样地，当访问 B 链表的指针访问到链表尾部时，令它从链表 A 的头部开始访问链表 A。
 *     这样就能控制访问 A 和 B 两个链表的指针能同时访问到交点。
 *
 *     如果不存在交点，那么 a + b = b + a，以下实现代码中 l1 和 l2 会同时为 null，从而退出循环。
 *
 *
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20%E9%A2%98%E8%A7%A3%20-%20%E9%93%BE%E8%A1%A8.md
 *
 * @Author moon-zhou <ayimin1989@163.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/10/27
 */
public class IntersectionOfTwoLinkedLists {

    public static void main(String[] args) {

        // 初始化节点
        Node A1 = new Node("a1");
        Node A2 = new Node("a2");

        Node B1 = new Node("b1");
        Node B2 = new Node("b2");
        Node B3 = new Node("b3");

        Node C1 = new Node("c1");
        Node c2 = new Node("c2");
        Node c3 = new Node("c3");

        // 初始化链表
        A1.setNext(A2);
        A2.setNext(C1);

        B1.setNext(B2);
        B2.setNext(B3);
        B3.setNext(C1);

        C1.setNext(c2);
        c2.setNext(c3);
        c3.setNext(null);

        Node intersectionNode = getIntersectionNode(A1, B1);
        System.out.println(intersectionNode);
    }

    public static Node getIntersectionNode(Node headA, Node headB) {
        Node l1 = headA;
        Node l2 = headB;

        while (l1 != l2) {
            l1 = (l1 == null) ? headB : l1.next;
            l2 = (l2 == null) ? headA : l2.next;
        }

        // l1==l2
        return l1;
    }
}
