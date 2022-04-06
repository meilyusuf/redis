package com.yusuf.redis.repository;

import com.yusuf.redis.entity.Price;

import java.util.Map;

public interface RedisRepo {

    public void saveRedis(Price price);

    public Map<Long, Price> getAll();

}
