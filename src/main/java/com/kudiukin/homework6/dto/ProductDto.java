package com.kudiukin.homework6.dto;

import com.kudiukin.homework6.model.Shop;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {

    private String name;

    private BigDecimal price;

    private Long shopId;

    private Long productId;

    private Shop shop;
}