package com.kudiukin.homework9.dto;

import com.kudiukin.homework9.model.Cart;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private List<Cart> carts;

    private String username;

    private String password;

    private String passwordConfirm;
}
