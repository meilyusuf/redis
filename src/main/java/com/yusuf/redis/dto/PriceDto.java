package com.yusuf.redis.dto;

import lombok.Data;

@Data
public class PriceDto {
    private double price;
    private String origin;
    private String destination;
    private String product;
}
