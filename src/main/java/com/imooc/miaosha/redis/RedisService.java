package com.imooc.miaosha.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by hushida on 18-4-17.
 */
@Service
public class RedisService {
    @Autowired
    JedisPool jedisPool;



    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        //JedisPool jp = null;
        //Jedis jedis = jedisPool.getResource();

        //连接池使用完，要释放，否则很可能不够用了
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();

            String realKey = prefix.getPrefix() + key;
            String str = jedis.get(realKey);
            T t = stringToBean(str, clazz);
            return t;
        } finally {
            returnToPool(jedis);
        }
    }


    public <T> boolean set(KeyPrefix prefix, String key, T value) {
        //连接池使用完，要释放，否则很可能不够用了
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if(str == null || str.length() <= 0) {
                return false;
            }
            String realKey = prefix.getPrefix() + key;
            int seconds = prefix.expireSeconds();
            if(seconds <= 0) {
                jedis.set(realKey, str);
            } else {
                jedis.setex(realKey, seconds, str);
            }
            //jedis.set(realKey, str);
            return true;
        } finally {
            returnToPool(jedis);
        }
    }

    private <T> String beanToString(T value) {
        if(value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if(clazz == int.class || clazz == Integer.class) {
            return "" + value;
        } else if(clazz == String.class) {
            return (String)value;
        } else if(clazz == long.class || clazz == long.class) {
            return "" + value;
        }
        return JSON.toJSONString(value);
    }

    private <T> T stringToBean(String str, Class<T> clazz) {
        if(clazz == null || str == null || str.length() <= 0) {
            return null;
        }

        if(clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if(clazz == String.class) {
            return (T)str;
        } else if(clazz == long.class ) {
            return (T)Long.valueOf(str);
        } else {
            JSONObject jo = JSON.parseObject(str);
            return JSON.toJavaObject(jo, clazz);
        }

    }

    private void returnToPool(Jedis jedis) {
        if(jedis != null) {
            jedis.close();
        }
    }

    public <T> boolean exists(KeyPrefix prefix, String key) {
        //连接池使用完，要释放，否则很可能不够用了
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.exists(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    public <T> Long incr(KeyPrefix prefix, String key) {
        //连接池使用完，要释放，否则很可能不够用了
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.incr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    public <T> Long decr(KeyPrefix prefix, String key) {
        //连接池使用完，要释放，否则很可能不够用了
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.decr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }


}