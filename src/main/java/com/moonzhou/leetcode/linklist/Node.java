package com.moonzhou.leetcode.linklist;

/**
 * @Description
 * @Author moon-zhou <ayimin1989@163.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/10/27
 */
public class Node<E> {

    E item;

    Node<E> next;

    public Node() {
    }

    public Node(Node node) {
        this.item = (E) node.getItem();
        this.next = node.getNext();
    }

    public Node(E item) {
        this.item = item;
    }

    public Node(E item, Node<E> next) {
        this.item = item;
        this.next = next;
    }

    public void setItem(E item) {
        this.item = item;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public E getItem() {
        return item;
    }

    public Node<E> getNext() {
        return next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "item=" + item +
                ", next=" + next +
                '}';
    }
}
