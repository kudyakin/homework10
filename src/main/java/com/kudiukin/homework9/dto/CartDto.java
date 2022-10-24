package com.kudiukin.homework9.dto;

import com.kudiukin.homework9.model.Person;
import com.kudiukin.homework9.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CartDto {

    private Long cartId;

    private Person person;

    private List<Product> products;

    private BigDecimal sum;

    private Long productId;
}
