package com.imooc.miaosha.redis;

/**
 * Created by hushida on 18-4-18.
 */
public class OrderKey extends BasePrefix {
    public OrderKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}