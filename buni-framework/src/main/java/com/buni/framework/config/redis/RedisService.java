package com.buni.framework.config.redis;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import com.buni.framework.constant.CommonConstant;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author zp.wei
 * @date 2023/9/19 11:08
 */
@Configuration
public class RedisService {

    @Resource
    private RedisTemplate redisTemplate;


    /********************************************* String *********************************************/


    /**
     * 存储数据并设置过期时间
     *
     * @param key
     * @param val
     */
    public void setHalfHour(String key, Object val) {
        set(key, val, 30L, TimeUnit.MINUTES);
    }


    /**
     * 存储数据并设置过期时间
     *
     * @param key
     * @param val
     */
    public void setOneHour(String key, Object val) {
        set(key, val, 1L, TimeUnit.HOURS);
    }


    /**
     * 存储数据并设置过期时间,默认有效期为1天
     *
     * @param key
     * @param val
     */
    public void setOneDay(String key, Object val) {
        set(key, val, 1L, TimeUnit.DAYS);
    }


    /**
     * 存储缓存
     *
     * @param key
     * @param val
     */
    public void set(String key, Object val) {
        redisTemplate.opsForValue().set(key, val);
    }


    /**
     * 存储缓存
     *
     * @param key      key
     * @param val      value
     * @param time     缓存时间
     * @param timeUnit 时间单位
     */
    public void set(String key, Object val, Long time, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, val, time, timeUnit);
    }


    /**
     * 自增操作
     *
     * @param key
     * @param min
     * @return
     */
    public Long incr(String key, long min) {
        return redisTemplate.opsForValue().increment(key, ObjUtil.isEmpty(key) || min < CommonConstant.ZERO ? CommonConstant.ZERO : min);
    }


    /**
     * 自减操作
     *
     * @param key
     * @param min
     * @return
     */
    public Long decr(String key, long min) {
        return redisTemplate.opsForValue().decrement(key, ObjUtil.isEmpty(key) || min < CommonConstant.ZERO ? CommonConstant.ZERO : min);
    }


    /********************************************* list *********************************************/


    /**
     * 向list的头部插入一条数据
     *
     * @param key   键
     * @param value 值
     */
    public Long listLeftPush(String key, Object value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }


    /**
     * 向list的末尾插入一条数据
     *
     * @param key   键
     * @param value 值
     */
    public Long listRightPush(String key, Object value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }


    /**
     * 向list头部添加list数据
     *
     * @param key   键
     * @param value 值
     */
    public Long listLeftPushAll(String key, List<?> value) {
        return redisTemplate.opsForList().leftPushAll(key, value);
    }


    /**
     * 向list末尾添加list数据
     *
     * @param key   键
     * @param value 值
     */
    public void listRightPushAll(String key, List<?> value) {
        redisTemplate.opsForList().rightPushAll(key, value);
        setKeyTime(key, CommonConstant.THIRTY, TimeUnit.HOURS);
    }


    /**
     * 通过索引设置list元素的值
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     */
    public void listSet(String key, long index, Object value) {
        redisTemplate.opsForList().set(key, index, value);
    }


    /**
     * 从 Redis 获取对象类型的 List
     */
    public List<Object> getList(String key) {
        return listRange(key, 0, -1);
    }


    /**
     * 获取列表指定范围内的list元素，正数则表示正向查找，负数则倒叙查找
     *
     * @param key   键
     * @param start 开始
     * @param end   结束
     * @return boolean
     */
    public List<Object> listRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }


    /**
     * 从列表前端开始取出数据
     *
     * @param key 键
     * @return 结果数组对象
     */
    public Object listPopLeftKey(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }


    /**
     * 从列表末尾开始遍历取出数据
     *
     * @param key 键
     * @return 结果数组
     */
    public Object listPopRightKey(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }


    /**
     * 获取list长度
     *
     * @param key 键
     * @return 列表长度
     */
    public Long listLen(String key) {
        return redisTemplate.opsForList().size(key);
    }


    /**
     * 通过索引获取list中的元素
     *
     * @param key   键
     * @param index 索引（index>=0时，0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推）
     * @return 列表中的元素
     */
    public Object listIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }


    /**
     * 移除list元素
     *
     * @param key   键
     * @param count 移除数量（"负数"则从列表倒叙查找删除 count 个对应的值; "整数"则从列表正序查找删除 count 个对应的值;）
     * @param value 值
     * @return 成功移除的个数
     */
    public Long listRem(String key, long count, Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }


    /**
     * 截取指定范围内的数据, 移除不是范围内的数据
     *
     * @param key   操作的key
     * @param start 截取开始位置
     * @param end   截取激素位置
     */
    public void listTrim(String key, long start, long end) {
        redisTemplate.opsForList().trim(key, start, end);
    }


    /********************************************* hash *********************************************/


    /**
     * 存hash数据
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public void hashPut(String key, String hashKey, String value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }


    /**
     * 批量存储hash数据
     *
     * @param key
     * @param map
     */
    public void hashPutAll(String key, Map map) {
        redisTemplate.opsForHash().putAll(key, map);
    }


    /**
     * 根据hashKey获取对应的值
     *
     * @param key
     * @param hashKey
     */
    public Object hashGet(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }


    /**
     * 获取全部数据
     *
     * @param key
     * @return
     */
    public Object hashGetAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }


    /**
     * 删除hash数据
     *
     * @param key
     * @param hashKey
     */
    public void hashDelete(String key, String hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }


    /**
     * 判断hashKey是否存在
     *
     * @param key
     * @param hashKey
     * @return
     */
    public boolean hashHasKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }


    /**
     * 获取hash数据长度
     *
     * @param key
     * @return
     */
    public Long hashSize(String key) {
        return redisTemplate.opsForHash().size(key);
    }


    /********************************************* set *********************************************/


    /**
     * 存储set类型数据
     *
     * @param key
     * @param value
     * @return
     */
    public Long setAdd(String key, Object value) {
        return redisTemplate.opsForSet().add(key, value);
    }


    /**
     * 移除缓存数据
     *
     * @param key
     * @param value
     * @return
     */
    public Long setRemove(String key, Object value) {
        return redisTemplate.opsForSet().remove(key, value);
    }


    /**
     * 判断是否包含value
     *
     * @param key
     * @param value
     */
    public void setContains(String key, String value) {
        redisTemplate.opsForSet().isMember(key, value);
    }


    /**
     * 获取集合中所有的值
     *
     * @param key
     * @return
     */
    public Set<String> setValues(String key) {
        return redisTemplate.opsForSet().members(key);
    }


    /**
     * 获取集合长度
     *
     * @param key
     * @return
     */
    public Long setSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }


    /********************************************* zset *********************************************/


    /**
     * 存储zset类型数据
     *
     * @param key
     * @param value
     * @return
     */
    public Long zSetAdd(String key, Set<Object> value) {
        return redisTemplate.opsForZSet().add(key, value);
    }


    /**
     * @param key
     * @param currentTime
     * @param score
     * @return
     */
    public Boolean zSetAdd(String key, Long currentTime, Long score) {
        return redisTemplate.opsForZSet().add(key, currentTime, score);
    }


    /**
     * 移除缓存数据
     *
     * @param key
     * @param value
     * @return
     */
    public Long zSetRemove(String key, Object value) {
        return redisTemplate.opsForZSet().remove(key, value);
    }


    /**
     * 获取集合长度
     *
     * @param key
     * @return
     */
    public Long zSetSize(String key) {
        return redisTemplate.opsForZSet().size(key);
    }


    /**
     * 获取范围内的缓存量
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Long zSetCount(String key, Long min, Long max) {
        return redisTemplate.opsForZSet().count(key, min, max);
    }


    /**
     * 移除范围内的缓存数据
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Long zSetRemoveRangeByScore(String key, Long min, Long max) {
        return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
    }


    /********************************************* 操作缓存 *********************************************/


    /**
     * 根据key从redis中获取数据
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 指定 key 的过期时间
     *
     * @param key  键
     * @param time 时间
     */
    public void setKeyTime(String key, long time, TimeUnit timeUnit) {
        redisTemplate.expire(key, time, timeUnit);
    }


    /**
     * 根据 key 获取过期时间（-1 即为永不过期）
     *
     * @param key 键
     * @return 过期时间
     */
    public Long getKeyTime(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }


    /**
     * 判断 key 是否存在
     *
     * @param key 键
     * @return 如果存在 key 则返回 true，否则返回 false
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }


    /**
     * 删除 key
     *
     * @param key 键
     */
    public Long deleteKey(String key) {
        if (ObjUtil.isEmpty(key)) {
            return 0L;
        }
        return redisTemplate.delete(Collections.singletonList(key));
    }


    /**
     * 删除key值前缀匹配相同的缓存
     *
     * @param key
     */
    public void delAllByKey(String key) {
        Set<String> keys = redisTemplate.keys(key + "*");
        if (CollUtil.isNotEmpty(keys)) {
            redisTemplate.delete(keys);
        }
    }


    /**
     * 根据key批量删除缓存
     *
     * @param keys
     */
    public void delAllByKeys(List<String> keys) {
        if (CollUtil.isNotEmpty(keys)) {
            Set<String> allKeys = new HashSet<>();
            keys.forEach(key -> allKeys.addAll(redisTemplate.keys(key)));
            if (CollUtil.isNotEmpty(allKeys)) {
                redisTemplate.delete(allKeys);
            }
        }
    }


    /**
     * 获取 Key 的类型
     *
     * @param key 键
     */
    public String keyType(String key) {
        DataType dataType = redisTemplate.type(key);
        assert dataType != null;
        return dataType.code();
    }


    /**
     * 批量设置值
     *
     * @param map 要插入的 key value 集合
     */
    public void batchSet(Map<String, Object> map) {
        redisTemplate.opsForValue().multiSet(map);
    }


    /**
     * 批量获取值
     *
     * @param list 查询的 Key 列表
     * @return value 列表
     */
    public List<Object> batchGet(List<String> list) {
        return redisTemplate.opsForValue().multiGet(list);
    }


}
