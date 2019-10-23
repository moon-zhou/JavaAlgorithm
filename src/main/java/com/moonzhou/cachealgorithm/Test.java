package com.moonzhou.cachealgorithm;

import com.alibaba.fastjson.JSON;

/**
 * @Description test cache
 * @Author moon-zhou <ayimin1989@163.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/10/20
 */
public class Test {

    public static void main(String[] args) {
//        testFIFO();

        testLRU();
    }

    private static void testFIFO() {

        ICache fifo = new FIFOCache(2);
        fifo.put("moon1", "zhou1", 300);

        System.out.println(JSON.toJSONString(fifo.get()));

        fifo.put("moon2", "zhou2", 300);
        System.out.println(JSON.toJSONString(fifo.get()));

        // 超出设置的容量，删除moon1
        fifo.put("moon3", "zhou3", 300);
        System.out.println(JSON.toJSONString(fifo.get()));
    }

    private static void testLRU() {

        ICache fifo = new LRUCache(2);
        fifo.put("moon1", "zhou1", 300);

        System.out.println(JSON.toJSONString(fifo.get()));

        fifo.put("moon2", "zhou2", 300);
        System.out.println(JSON.toJSONString(fifo.get()));

        fifo.get("moon1");

        // 超出设置的容量，删除moon1
        fifo.put("moon3", "zhou3", 300);
        System.out.println(JSON.toJSONString(fifo.get()));
    }
}
