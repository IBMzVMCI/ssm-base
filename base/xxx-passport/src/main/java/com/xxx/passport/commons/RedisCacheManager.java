package com.xxx.passport.commons;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.Resource;

/**
 * Redis管理工具
 * Date: 14-8-7
 * Time: 下午8:39
 */
public class RedisCacheManager {

    private static Logger LOGGER = LoggerFactory.getLogger(RedisCacheManager.class);
    private static ApplicationContext context;
    private static RedisCacheManager instance = new RedisCacheManager();

    @Resource(name = "redis-COUNTER")
    ShardedJedisPool pool;

    @Autowired
    public void setApplicationContext(ApplicationContext act) {
        context = act;
    }

    private RedisCacheManager() {
    }

    public static RedisCacheManager getInstance() {
        if (instance == null) {
            synchronized (RedisCacheManager.class) {
                if (instance != null) {
                    return instance;
                }
                instance = (RedisCacheManager) BeanFactoryUtils.beanOfType(context, RedisCacheManager.class);
                instance = new RedisCacheManager();
            }
        }
        return instance;
    }

    public RedisCacheManager getManagerByPoolName(){

        return getInstance();
    }
/*
    public static String getKey(String keyPrefix, int type) {
        LOGGER.debug("RedisCacheManager getKey():" + keyPrefix + type);
        return new StringBuilder(keyPrefix).append(type).toString();
    }*/

    public long incr(String key) {
        ShardedJedis jedis = pool.getResource();
        long number = 0;
        try {
            number = jedis.incrBy(key, 1);
        } catch (Exception e) {
            LOGGER.error("RedisCacheManager incr: {}", e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return number;
    }

    public long getCount(String key) {
        ShardedJedis jedis = pool.getResource();
        long number = -1;
        try {
            String value = jedis.get(key);
            if (value != null && !value.equalsIgnoreCase("NULL") && !value.equalsIgnoreCase("NIL")) {
                number = Long.parseLong(value);
            }
        } catch (Exception e) {
            LOGGER.error("RedisCacheManager getCount: {}", e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return number;
    }

    /**
     * 向缓存中设置对象
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value) {
        ShardedJedis jedis = null;
        try {
            String objectJson = JSON.toJSONString(value);
            jedis = pool.getResource();
            jedis.set(key, objectJson);
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 向缓存中设置对象
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value, long time) {
        ShardedJedis jedis = null;
        try {
            String objectJson = JSON.toJSONString(value);
            jedis = pool.getResource();
            jedis.set(key, objectJson);
            //todo time
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 删除缓存中得对象，根据key
     *
     * @param key
     * @return
     */
    public boolean remove(String key) {
        ShardedJedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.del(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 根据key 获取内容
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        ShardedJedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.get(key);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 根据key 获取对象
     *
     * @param key
     * @return
     */
    public <T> T get(String key, Class<T> clazz) {
        ShardedJedis jedis = null;
        try {
            jedis = pool.getResource();
            String value = jedis.get(key);
            return JSON.parseObject(value, clazz);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
