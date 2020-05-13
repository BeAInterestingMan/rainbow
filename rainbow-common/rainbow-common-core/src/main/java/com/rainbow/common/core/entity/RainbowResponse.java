package com.rainbow.common.core.entity;

import java.util.HashMap;

/**
 *  @Description 响应封装
 *  @author liuhu
 *  @Date 2020/5/13 9:20
 */
public class RainbowResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    public RainbowResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public RainbowResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    @Override
    public RainbowResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public String getMessage() {
        return String.valueOf(get("message"));
    }

    public Object getData() {
        return get("data");
    }
}
