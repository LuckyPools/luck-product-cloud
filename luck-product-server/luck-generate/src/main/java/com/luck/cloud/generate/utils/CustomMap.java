package com.luck.cloud.generate.utils;

import java.util.HashMap;


/**
 * 自定义map
 *
 * @author luck
 * @date 2024-07-30
 */
public class CustomMap<K,V> extends HashMap<K, V> {


    public static <K extends Object, V extends Object>
    CustomMap<K, V> create() {
        return new CustomMap<>();
    }

    public CustomMap build(K key, V value) {
        super.put(key, value);
        return this;
    }

}
