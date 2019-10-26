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

//        testLFU();
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

        ICache lru = new LRUCache(2);
        lru.put("moon1", "zhou1", 300);

        System.out.println(JSON.toJSONString(lru.get()));

        lru.put("moon2", "zhou2", 300);
        System.out.println(JSON.toJSONString(lru.get()));

        lru.get("moon1");

        // 超出设置的容量，删除moon1
        lru.put("moon3", "zhou3", 300);
        System.out.println(JSON.toJSONString(lru.get()));
    }

    private static void testLFU() {
        ICache lfu = new LFUCache(2);
        lfu.put("moon1", "zhou1", 300);

        System.out.println(JSON.toJSONString(lfu.get()));

        lfu.put("moon2", "zhou2", 300);
        System.out.println(JSON.toJSONString(lfu.get()));

        lfu.get("moon1");

        // 超出设置的容量，删除moon1
        lfu.put("moon3", "zhou3", 300);
        System.out.println(JSON.toJSONString(lfu.get()));

        lfu.remove("moon3");
        System.out.println(JSON.toJSONString(lfu.get()));
    }
}
