package com.snail.abell.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Abell
 * @date 2023/1/31
 */
public class JsonUtil {

    public Map<String, String> jsonMap (JSONObject jsonObject){
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<String, Object> json:jsonObject.entrySet()) {
            map.put(json.getKey(), (String) json.getValue());
        }
        return map;
    }
}
