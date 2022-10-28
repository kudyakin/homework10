package com.kudiukin.homework10.converter;

import com.kudiukin.homework10.dto.CartDto;
import com.kudiukin.homework10.model.Cart;

public final class CartConverter {

    public static CartDto convertCartModel2CartDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setCartId(cart.getCartId());
        cartDto.setPerson(cart.getPerson());
        cartDto.setProducts(cart.getProducts());
        cartDto.setSum(cart.getSum());
        return cartDto;
    }
}
