package com.kudiukin.homework10.facade;

import com.kudiukin.homework10.NotFoundException;
import com.kudiukin.homework10.converter.CartConverter;
import com.kudiukin.homework10.dto.CartDto;
import com.kudiukin.homework10.dto.PersonDto;
import com.kudiukin.homework10.model.Cart;
import com.kudiukin.homework10.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.kudiukin.homework10.converter.CartConverter.convertCartModel2CartDto;

@Component
public class CartFacadeImpl implements CartFacade{

    @Autowired
    private CartService cartService;

    @Override
    public CartDto createCartByPersonId(PersonDto personDto) throws NotFoundException {
        Cart newCart = cartService.createCartByPersonId(personDto.getId());
        return convertCartModel2CartDto(newCart);
    }

    @Override
    public CartDto getCartById(CartDto cartDto) throws NotFoundException {
        Cart getCart = cartService.getCartById(cartDto.getCartId());
        return convertCartModel2CartDto(getCart);
    }

    @Override
    public void removeCartById(CartDto cartDto) throws NotFoundException {
        cartService.removeAllProductsFromCartById(cartDto.getCartId());
        cartService.removeCartById(cartDto.getCartId());
    }

    @Override
    public CartDto addProductByProductIdAndCartId(CartDto cartDto) throws NotFoundException {
        Cart addToCart = cartService.addProductByProductIdAndCartId(cartDto.getCartId(), cartDto.getProductId());
        return convertCartModel2CartDto(addToCart);
    }

    @Override
    public CartDto removeProductByProductIdAndCartId(CartDto cartDto) throws NotFoundException {
        Cart deleteFromCart = cartService.removeProductByProductIdAndCartId(cartDto.getCartId(), cartDto.getProductId());
        return convertCartModel2CartDto(deleteFromCart);
    }

    @Override
    public void removeAllProductsFromCartById(CartDto cartDto) throws NotFoundException {
        cartService.removeAllProductsFromCartById(cartDto.getCartId());
    }

    @Override
    public List<CartDto> getAllCarts() {
        return cartService.getAllCarts().stream().map(CartConverter::convertCartModel2CartDto)
                .collect(Collectors.toList());
    }


}
