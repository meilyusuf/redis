package com.yusuf.redis.controller;

import com.yusuf.redis.dto.PriceDto;
import com.yusuf.redis.entity.Price;
import com.yusuf.redis.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class ApiController {

    @Autowired
    ApiService apiService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> add(Price price) {
        apiService.add(price);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Map<Long, Price> getRedis(Price price) {
        return apiService.getAll();
    }

    @RequestMapping(value = "/get_data_redis", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<PriceDto> getDataRedis() {
        return apiService.getDataRedis();
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<String> testRedis() {
        return apiService.testRedis();
    }

}
