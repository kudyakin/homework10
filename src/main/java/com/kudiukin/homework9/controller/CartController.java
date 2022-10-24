package com.kudiukin.homework9.controller;

import com.kudiukin.homework9.converter.CartConverter;
import com.kudiukin.homework9.dto.CartDto;
import com.kudiukin.homework9.dto.PersonDto;
import com.kudiukin.homework9.service.CartService;
import com.kudiukin.homework9.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static com.kudiukin.homework9.converter.CartConverter.convertCartModel2CartDto;

@Controller
@RequestMapping(path="/api/cart")
@Slf4j
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createCart(@ModelAttribute("person") PersonDto personDto) throws NotFoundException {
        cartService.createCartByPersonId(personDto.getId());
        log.info("New cart for person with ID [{}] is created", personDto.getId());
        return "/cart/createCartSuccess";
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.POST})
    public String deleteCart(@ModelAttribute("cart") CartDto cartDto) throws NotFoundException {
        cartService.removeCartById(cartDto.getCartId());
        log.info("Cart with ID [{}] is deleted", cartDto.getCartId());
        return "/cart/deleteCartSuccess";
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public String getCartById(@ModelAttribute("cartById") CartDto cartDto, Model model) throws NotFoundException {
        CartDto cartById = convertCartModel2CartDto(cartService.getCartById(cartDto.getCartId()));
        model.addAttribute("cartById", cartById);
        return "/cart/getCartSuccess";
    }

    @RequestMapping(value = "/add", method = {RequestMethod.PUT, RequestMethod.POST})
    public String addProductByProductIdAndCartId(@ModelAttribute("cart") CartDto cartDto) throws NotFoundException {
        cartService.addProductByProductIdAndCartId(cartDto.getCartId(), cartDto.getProductId());
        log.info("Product with ID [{}] is added to cart with ID [{}]", cartDto.getProductId(), cartDto.getCartId());
        return "/cart/addProductToCartSuccess";
    }

    @RequestMapping(value = "/remove", method = {RequestMethod.PUT, RequestMethod.POST})
    public String removeProductByProductIdAndCartId(@ModelAttribute("cart") CartDto cartDto) throws NotFoundException {
        cartService.removeProductByProductIdAndCartId(cartDto.getCartId(), cartDto.getProductId());
        log.info("Product with ID [{}] is deleted from cart with ID [{}]", cartDto.getProductId(), cartDto.getCartId());
        return "/cart/removeProductFromCartSuccess";
    }

    @RequestMapping(value = "/clean", method = RequestMethod.POST)
    public String removeAllProductsFromCartById(@ModelAttribute("cart") CartDto cartDto) throws NotFoundException {
        cartService.removeAllProductsFromCartById(cartDto.getCartId());
        log.info("Cart with ID [{}] is cleaned]", cartDto.getCartId());
        return "/cart/cleanCartSuccess";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createCartView(Model model) {
        model.addAttribute("person", new PersonDto());
        model.addAttribute("cart", new CartDto());
        return "/cart/createCart";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteCartView(Model model) {
        model.addAttribute("cart", new CartDto());
        return "/cart/deleteCart";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAllСarts(Model model) {
        model.addAttribute("all", cartService.getAllCarts().stream()
                .map(CartConverter::convertCartModel2CartDto).collect(Collectors.toList()));
        return "/cart/allCarts";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getCartByIdView(Model model) {
        model.addAttribute("cartById", new CartDto());
        return "/cart/getCart";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addProductByProductIdAndCartIdView(Model model) {
        model.addAttribute("cart", new CartDto());
        return "/cart/addProductToCart";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String removeProductByProductIdAndCartIdView(Model model) {
        model.addAttribute("cart", new CartDto());
        return "/cart/removeProductFromCart";
    }

    @RequestMapping(value = "/clean", method = RequestMethod.GET)
    public String removeAllProductsFromCartByIdView(Model model) {
        model.addAttribute("cart", new CartDto());
        return "/cart/cleanCart";
    }
}
