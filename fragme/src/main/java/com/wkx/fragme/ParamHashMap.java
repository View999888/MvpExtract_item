package com.wkx.fragme;

import java.util.HashMap;

/**
 * 创建此集合工具类 用来的存放apiservice集合的map集合的参数
 */
public class ParamHashMap extends HashMap<String, Object> {
    public ParamHashMap add(String key, Object value) {
        this.put(key, value);
        return this;
    }
}
