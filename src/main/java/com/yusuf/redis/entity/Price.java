package com.yusuf.redis.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Price implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private double price;
    @Column(name = "origin_code")
    private String origin;
    @Column(name = "destination_code")
    private String destination;
    private String product;

}
