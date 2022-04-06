package com.yusuf.redis.service;

import com.yusuf.redis.dto.PriceDto;
import com.yusuf.redis.entity.Price;

import java.util.List;
import java.util.Map;

public interface ApiService {

    public void add(Price price);
    public Map<Long, Price> getDataWithRedisTemplate();
    public List<String> testRedis();
    public List<PriceDto> getDataRedis();
}
