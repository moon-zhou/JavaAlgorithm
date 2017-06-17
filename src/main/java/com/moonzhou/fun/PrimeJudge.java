package com.moonzhou.fun;

import org.junit.Assert;
import org.junit.Test;

/**
 * 判断一个数为素数
 * 
 * @author moon-zhou
 *
 */
public class PrimeJudge {

    @Test
    public void testPrime() {

        // base algorithm to judge prime
        Assert.assertTrue(isPrime(2));

        Assert.assertTrue(isPrime(137));

        Assert.assertFalse(isPrime(4));

        // 高级算法
        Assert.assertTrue(isPrimeUpgrade(2));
        
        Assert.assertTrue(isPrimeUpgrade(137));
        
        Assert.assertFalse(isPrimeUpgrade(4));

    }

    /**
     * 质数除了1和本身之外没有其他约数，所以判断n是否为质数，根据定义直接判断从2到n-1是否存在n的约数即可
     * 
     * @param num
     * @return
     */
    public static boolean isPrime(int num) {
        int lastApproximately = num - 1;

        for (int i = 2; i <= lastApproximately; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 对于每个数n，其实并不需要从2判断到n-1，我们知道，一个数若可以进行因数分解，那么分解时得到的两个数一定是一个小于等于sqrt(n)， 一个大于等于sqrt(n)，据此，上述代码中并不需要遍历到n-1，遍历到sqrt(n)即可，因为若sqrt(n)左侧找不到约数，那么右侧也一定找不到约数。
     * 
     * @param num
     * @return
     */
    public static boolean isPrimeUpgrade(int num) {
        int tmp = (int) Math.sqrt(num);

        for (int i = 2; i <= tmp; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;

    }
}
