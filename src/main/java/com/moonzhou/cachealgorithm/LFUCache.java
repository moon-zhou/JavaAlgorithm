package com.moonzhou.cachealgorithm;

import java.util.*;

/**
 * @Description 最近最不常用，当缓存容量满时，移除访问次数最少的元素，如果访问次数相同的元素有多个，则移除最久访问的那个。
 * @Author moon-zhou <ayimin1989@163.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/10/26
 */
public class LFUCache implements ICache {
    /**
     * 主要容器，用于保存k-v
     */
    private Map<String, Object> keyToValue = new HashMap<>();

    /**
     * 记录每个k被访问的次数 key-访问次数（取值/赋值）
     */
    private Map<String, Integer> keyToCount = new HashMap<>();

    /**
     * 访问相同次数的key列表，按照访问次数排序，value为相同访问次数到key列表。
     */
    private TreeMap<Integer, LinkedHashSet<String>> countToLRUKeys = new TreeMap<>();

    private int capacity;

    public LFUCache(int capacity) {
        // 初始化，默认访问1次，主要是解决下文
        this.capacity = capacity;
    }

    @Override
    public Object get() {
        return keyToValue;
    }

    @Override
    public Object get(String key) {
        if (!keyToValue.containsKey(key)) {
            return null;
        }

        // 更新使用次数
        touch(key);

        return keyToValue.get(key);
    }

    /**
     * 如果一个key被访问，应该将其访问次数调整。
     *
     * @param key
     */
    private void touch(String key) {
        // count为包装类，因为逻辑上必有值，不会出现NPE
        int count = keyToCount.get(key);

        // 访问次数增加
        keyToCount.put(key, count + 1);

        // 从原有访问次数统计列表中移除
        countToLRUKeys.get(count).remove(key);

        // 如果符合最少调用次数到key统计列表为空，则移除此调用次数到统计
        if (countToLRUKeys.get(count).size() == 0) {
            countToLRUKeys.remove(count);
        }

        // 然后将此key的统计信息加入到管理列表中
        LinkedHashSet<String> countKeys = countToLRUKeys.get(count + 1);
        if (countKeys == null) {
            countKeys = new LinkedHashSet<>();
            countToLRUKeys.put(count + 1, countKeys);
        }
        // 实质有多个逻辑，存在与不存在countKeys，放在最后可以省去多个判断逻辑
        countKeys.add(key);
    }

    @Override
    public void put(String key, Object value, int seconds) {
        if (capacity <= 0) {
            return;
        }

        if (keyToValue.containsKey(key)) {
            keyToValue.put(key, value);
            touch(key);
            return;
        }

        // 容量超额之后，移除访问次数最少的元素
        if (keyToValue.size() >= capacity) {
            Map.Entry<Integer, LinkedHashSet<String>> entry = countToLRUKeys.firstEntry();
            Iterator<String> it = entry.getValue().iterator();
            String evictKey = it.next();
            it.remove();

            if (!it.hasNext()) {
                countToLRUKeys.remove(entry.getKey());
            }

            keyToCount.remove(evictKey);
            keyToValue.remove(evictKey);
        }

        keyToValue.put(key, value);
        keyToCount.put(key, 1);
        LinkedHashSet<String> keys = countToLRUKeys.get(1);
        if (keys == null) {
            keys = new LinkedHashSet<>();
            countToLRUKeys.put(1, keys);
        }
        keys.add(key);
    }

    @Override
    public boolean remove(String key) {
        // 删除value
        Object value = keyToValue.remove(key);
        if (null == value) {
            return false;
        }

        // 删除使用次数对应的统计集合里的值
        // count为包装类，因为逻辑上必有值，不会出现NPE
        int count = keyToCount.get(key);
        countToLRUKeys.get(count).remove(key);

        // 如果符合最少调用次数到key统计列表为空，则移除此调用次数到统计
        if (countToLRUKeys.get(count).size() == 0) {
            countToLRUKeys.remove(count);
        }

        // 删除次数统计
        keyToCount.remove(key);

        return true;
    }
}
