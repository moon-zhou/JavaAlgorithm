package com.moonzhou.cachealgorithm;

/**
 * @Description 抽象缓存基础接口
 * @Author moon-zhou <ayimin1989@163.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/10/23
 */
public interface ICache {

    /**
     * 返回全量的缓存值
     * @return
     */
    Object get();

    /**
     * 取值接口
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 设置值接口
     * @param key
     * @param value
     * @param seconds
     */
    void put(String key, Object value, int seconds);

    /**
     * 根据key删除缓存的值
     * @param key
     * @return
     */
    boolean remove(String key);
}
