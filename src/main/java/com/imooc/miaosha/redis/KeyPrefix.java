package com.imooc.miaosha.redis;

/**
 * Created by hushida on 18-4-18.
 */
public interface KeyPrefix {
    public int expireSeconds();
    public  String getPrefix();
}