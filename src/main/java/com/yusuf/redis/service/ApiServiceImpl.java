package com.yusuf.redis.service;


import com.yusuf.redis.dto.PriceDto;
import com.yusuf.redis.entity.Price;
import com.yusuf.redis.repository.RedisRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yusuf.redis.repository.PriceRepo;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class ApiServiceImpl implements ApiService {

    Logger logger = Logger.getLogger(ApiServiceImpl.class.getName());

    final String KEY = "price:origin,destination,product";

    @Autowired
    PriceRepo priceRepo;
    @Autowired
    RedisRepo redisRepo;

    @Override
    public void add(Price price) {
        priceRepo.save(price);
        //redisRepo.saveRedis(p1);
        saveDataPrice(price);
    }

    @Override
    public Map<Long, Price> getAll() {

        return redisRepo.getAll();
    }


    @Override
    public List<PriceDto> getDataRedis() {
        Jedis jedis = new Jedis();
        List<String> listStringPrice =  jedis.lrange(KEY , 0L , -1L);
        return toListPrice(listStringPrice);
    }

    private void saveDataPrice(Price price) {
        Jedis jedis = new Jedis();
        String strPrice = price.getPrice() + ":" +
                price.getOrigin() + "," +
                price.getDestination() + "," +
                price.getProduct();
        jedis.lpush(KEY, strPrice);
    }


    @Override
    public List<String> testRedis() {
        Jedis jedis = new Jedis();
        jedis.lpush("price:origin,destination,product", "11:bekasi,jakarta,keyboard");
        jedis.lpush("price:origin,destination,product", "12:bekasi,test,kalong");
        return jedis.lrange("price:origin,destination,product" , Long.valueOf(0), Long.valueOf(-1));
    }

    private List<PriceDto> toListPrice(List<String> listStringPrice) {
        List<PriceDto> prices = new ArrayList<>();
        for (String data :listStringPrice) {
            PriceDto p = new PriceDto();
            String[] arrData = data.split(",");
            String[] priceOrigin = arrData[0].split(":");
            String priceStr = priceOrigin[0];
            p.setPrice(Double.parseDouble(priceStr));
            p.setOrigin(priceOrigin[1]);
            p.setDestination(arrData[1]);
            p.setProduct(arrData[2]);
            prices.add(p);
        }
        return  prices;
    }



}
