package com.moonzhou.cachealgorithm;

import com.alibaba.fastjson.JSON;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * @Description 抽象基本的缓存数据结构
 * @Author moon-zhou <ayimin1989@163.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/10/23
 * <p>
 * 按照访问时间排序,保存所有key-value
 * <p>
 * 过期数据，只保存有过期时间的key
 * 暂不考虑并发，我们认为同一个时间内没有重复的key，如果改造的话，可以将value换成set
 * <p>
 * 存储的key为过期时间（Long），value为CACHE的key值
 * TreeMap为有序的，默认key升序，所以如果遇到一个没有过期的，那么之后的都没有过期
 * <p>
 * 缓存大小
 */
public abstract class AbstractExpireCache implements ICache {

    /**
     * 按照访问时间排序,保存所有key-value
     * 链表结构是顺序的，越后，表示使用的时间离现在越近
     */
    protected final Map<String, Value> CACHE = new LinkedHashMap<>();

    /**
     * 过期数据，只保存有过期时间的key
     * 暂不考虑并发，我们认为同一个时间内没有重复的key，如果改造的话，可以将value换成set
     * <p>
     * 存储的key为过期时间（Long），value为CACHE的key值
     * TreeMap为有序的，默认key升序，所以如果遇到一个没有过期的，那么之后的都没有过期
     */
    protected final TreeMap<Long, String> EXPIRED = new TreeMap<>();

    /**
     * 缓存大小
     */
    private final int capacity;

    public AbstractExpireCache(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public Map<String, Value> get() {
        return CACHE;
    }

    /**
     * 无过期时间的设置值
     * @param key
     * @param value
     */
    protected void put(String key, Object value) {
        // 抽象方法（ICache的接口），由各个子类实现进行调用
        put(key, value, -1);
    }

    /**
     * 当前缓存的值大于或者等于限定容量大小时，表示已经满了
     * @return
     */
    protected boolean isFull() {
        return capacity <= CACHE.size();
    }

    /**
     * 判断时间戳是否过期
     * @param expired
     * @return
     */
    protected long expiredTime(int expired) {
        return System.nanoTime() + TimeUnit.SECONDS.toNanos(expired);
    }

    @Override
    public boolean remove(String key) {
        Value value = CACHE.remove(key);
        if (value == null) {
            return false;
        }
        long expired = value.expired;
        if (expired > 0) {
            EXPIRED.remove(expired);
        }
        return true;
    }
    class Value {

        long expired; // 过期时间,纳秒
        Object value;

        Value(long expired, Object value) {
            this.expired = expired;
            this.value = value;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }

}
