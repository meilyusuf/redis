package com.yusuf.redis.repository;

import com.yusuf.redis.entity.Price;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepo extends CrudRepository<Price, Long> {
}
