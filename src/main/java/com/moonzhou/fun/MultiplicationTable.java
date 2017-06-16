package com.moonzhou.fun;

public class MultiplicationTable {

    public static void main(String[] args) {

        // 外循环控制行数
        for (int i = 1; i < 10; i++) {

            // 内循环控制每行表达式个数
            for (int j = 1; j <= i; j++) {
                System.out.print(" " + i + "*" + j + "=" + (i * j));
            }

            // 一行结束换行
            System.out.println();
        }
    }

}
