package com.yusuf.redis.repository;

import com.yusuf.redis.entity.Price;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

@Repository
public class RedisImplRepo implements RedisRepo {


    public static final String KEY = "PRICE";
    private RedisTemplate<String,Price> redisTemplate;
    private HashOperations hashOperations;

    public RedisImplRepo(RedisTemplate<String, Price> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void saveRedis(Price price) {
        hashOperations.putIfAbsent(KEY, price.getId(), price);
    }

    @Override
    public Map<Long, Price> getAll() {
        return hashOperations.entries(KEY);
    }

}
