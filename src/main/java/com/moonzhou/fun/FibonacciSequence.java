package com.moonzhou.fun;

/**
 * Fibonacci sequence
 * 
 * @author moon-zhou
 *
 */
public class FibonacciSequence {

    public static void main(String[] args) {
        fSequenceExt();
    }

    public static void fSequenct() {
        int i = 0;
        for (i = 1; i <= 20; i++)
            System.out.println(f(i));
    }

    public static int f(int x) {
        if (x == 1 || x == 2) {
            return 1;
        } else if (x == 0) {
            return 0;
        } else {
            return f(x - 1) + f(x - 2);
        }
    }

    public static void fSequenceExt() {
        for (int i = 0; i <= 20; i++)
            System.out.println(f(i));
    }

}
