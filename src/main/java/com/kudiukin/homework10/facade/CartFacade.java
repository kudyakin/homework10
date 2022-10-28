package com.kudiukin.homework10.facade;

import com.kudiukin.homework10.NotFoundException;
import com.kudiukin.homework10.dto.CartDto;
import com.kudiukin.homework10.dto.PersonDto;
import com.kudiukin.homework10.model.Cart;

import java.util.List;

public interface CartFacade {

    CartDto createCartByPersonId(PersonDto personDto) throws NotFoundException;

    CartDto getCartById(CartDto cartDto) throws NotFoundException;

    void removeCartById(CartDto cartDto) throws NotFoundException;

    CartDto addProductByProductIdAndCartId(CartDto cartDto) throws NotFoundException;

    CartDto removeProductByProductIdAndCartId(CartDto cartDto) throws NotFoundException;

    void removeAllProductsFromCartById(CartDto cartDto) throws NotFoundException;

    List<CartDto> getAllCarts();
}
