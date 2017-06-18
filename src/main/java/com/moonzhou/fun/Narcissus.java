package com.moonzhou.fun;

import java.util.Scanner;

/**
 * 水仙花数
 * @author moon-zhou
 *
 */
public class Narcissus {

    public static void main(String[] args) {
        System.out.print("指定最大位数N:");
        Scanner input = new Scanner(System.in);

        int N = input.nextInt();
        input.close();

        for (int i = 3; i <= N; i++) {
            int[] a = new int[i];
            int num = (int) Math.pow(10, i - 1) + 1;
            System.out.print(i + "位的水仙花数有：\t");
            
            while (num <= Math.pow(10, i) - 1) {
                int sum = 0;
                
                // 分解当前数的各个位数，使用数组a进行存放
                for (int j = 0; j < i; j++)
                    a[j] = (int) (num / Math.pow(10, j) % 10);
                for (int j = 0; j < i; j++)
                    sum = sum + (int) Math.pow(a[j], i);
                if (num == sum)
                    System.out.print(num + "\t");
                num++;
            }
            System.out.print("\n");
        }
    }

}
