package com.moonzhou.cachealgorithm.backup;

import com.alibaba.fastjson.JSON;
import com.moonzhou.cachealgorithm.ICache;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * @Description 当缓存（页）满时，优先移除最近最久未使用的数据（使用时间维度）
 *
 * 设计：
 *
 *
 * 核心方法：
 *     Object get(key)：从canche中获取key对应的数据，如果此key已过期，移除此key，并则返回null。
 *     void put(key,value,expired)：设置k-v，如果容量不足，则根据LRU置换算法移除“最久未被使用的key”，
 *         需要注意，根据LRU优先移除已过期的keys，如果没有，则根据LRU移除未过期的key。
 *         如果未设定过期时间，则认为永不自动过期。
 *
 * @Author moon-zhou <ayimin1989@163.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/10/20
 */
public class LRUCache implements ICache {

    /**
     * 按照使用(get)排序,保存所有key-value
     * 链表结构是顺序的，越后，表示使用的时间离现在越近
     */
    private final Map<String, Value> CACHE = new LinkedHashMap<>();

    /**
     * 过期数据，只保存有过期时间的key
     * 暂不考虑并发，我们认为同一个时间内没有重复的key，如果改造的话，可以将value换成set
     *
     * 存储的key为过期时间（Long），value为CACHE的key值
     * TreeMap为有序的，默认key升序，所以如果遇到一个没有过期的，那么之后的都没有过期
     */
    private final TreeMap<Long, String> EXPIRED = new TreeMap<>();

    /**
     * 缓存大小
     */
    private final int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 获取全量缓存值
     * 用于测试
     * @return
     */
    @Override
    public Map<String, Value> get() {
        return CACHE;
    }

    /**
     * 正常获取值
     * 判断当前值是否过期，做一个过期处理的触发
     * @param key
     * @return
     */
    @Override
    public Object get(String key) {
        // 优先获取缓存的值
        Value value = CACHE.get(key);
        if (value == null) {
            return null;
        }

        // 如果不包含过期时间
        long expired = value.expired;
        long now = System.nanoTime();

        // 已过期，get的时候主动触发删除缓存
        if (expired > 0 && expired <= now) {
            CACHE.remove(key);
            EXPIRED.remove(expired);
            return null;
        }

        // 相对于FIFO，增加顺序重置
        CACHE.remove(key);
        CACHE.put(key,value);

        return value.value;
    }

    /**
     * 无过期时间的设置值
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        put(key, value, -1);
    }

    /**
     *
     * @param key
     * @param value
     * @param seconds
     */
    @Override
    public void put(String key, Object value, int seconds) {
        // 如果容量不足，移除过期数据
        if (isFull()) {
            long now = System.nanoTime();
            // 有过期的,全部移除
            Iterator<Long> iterator = EXPIRED.keySet().iterator();
            while (iterator.hasNext()) {
                long _key = iterator.next();
                // 如果有一个key未过期，那么之后的都没有过期，利用了TreeMap的key有序的特点
                if (_key > now) {
                    break;
                }
                // 如果已过期，则删除，一次移除所有过期key
                String _value = EXPIRED.get(_key);
                CACHE.remove(_value);
                iterator.remove();
            }
        }

        /**
         * 如果仍然容量不足，则移除缓存里最开始的塞的数据，塞的越早，表示越惰性，使用的越少
         * 如果使用的越多，那么会重新设置该值，放在LinkedHashMap后面，第88行
         */
        if (isFull()) {
            Iterator<String> iterator = CACHE.keySet().iterator();
            while (iterator.hasNext() && isFull()) {
                String _key = iterator.next();
                Value _value = CACHE.get(_key);
                long expired = _value.expired;
                if (expired > 0) {
                    EXPIRED.remove(expired);
                }
                iterator.remove();
            }
        }

        // 如果此key已存在,移除旧数据
        Value current = CACHE.remove(key);
        if (current != null && current.expired > 0) {
            EXPIRED.remove(current.expired);
        }

        // 如果指定了过期时间
        if (seconds > 0) {
            long expireTime = expiredTime(seconds);
            EXPIRED.put(expireTime, key);
            CACHE.put(key, new Value(expireTime, value));
        } else {
            CACHE.put(key, new Value(-1, value));
        }

    }

    private long expiredTime(int expired) {
        return System.nanoTime() + TimeUnit.SECONDS.toNanos(expired);
    }

    /**
     * 当前缓存的值大于或者等于限定容量大小时，表示已经满了
     * @return
     */
    private boolean isFull() {
        return capacity <= CACHE.size();
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
