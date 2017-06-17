package com.moonzhou.fun;

import org.junit.Assert;
import org.junit.Test;

/**
 * 判断一个数为素数
 * 
 * @author moon-zhou
 * 
 *         http://blog.csdn.net/huang_miao_xin/article/details/51331710
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

        // 究极算法
        Assert.assertTrue(isPrimeBest(2));
        
        Assert.assertTrue(isPrimeBest(137));
        
        Assert.assertFalse(isPrimeBest(4));

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

    /**
     * 首先看一个关于质数分布的规律：大于等于5的质数一定和6的倍数相邻。例如5和7，11和13,17和19等等； 
     * 证明：令x≥1，将大于等于5的自然数表示如下： ······ 6x-1，6x，6x+1，6x+2，6x+3，6x+4，6x+5，6(x+1），6(x+1)+1 ······ 
     * 可以看到，不在6的倍数两侧，即6x两侧的数为6x+2，6x+3，6x+4，由于2(3x+1)，3(2x+1)，2(3x+2)， 所以它们一定不是素数，
     * 再除去6x本身，显然，素数要出现只可能出现在6x的相邻两侧。 这里有个题外话，关于孪生素数，有兴趣的道友可以再另行了解一下，由于与我们主题无关，暂且跳过。
     * 这里要注意的一点是，在6的倍数相邻两侧并不是一定就是质数。
     * 根据以上规律，判断质数可以6个为单元快进，即将方法（2）循环中i++步长加大为6，加快判断速度
     * 
     * @param num
     * @return
     */
    public static boolean isPrimeBest(int num) {
        // 两个较小数另外处理
        if (num == 2 || num == 3)
            return true;
        
        // 不在6的倍数两侧的一定不是质数
        if (num % 6 != 1 && num % 6 != 5)
            return false;
        
        int tmp = (int) Math.sqrt(num);
        
        // 在6的倍数两侧的也可能不是质数
        for (int i = 5; i <= tmp; i += 6)
            if (num % i == 0 || num % (i + 2) == 0)
                return false;
        
        // 排除所有，剩余的是质数
        return true;
    }
}
