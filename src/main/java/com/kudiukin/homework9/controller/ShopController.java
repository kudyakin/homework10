package com.kudiukin.homework9.controller;

import com.kudiukin.homework9.converter.ShopConverter;
import com.kudiukin.homework9.dto.ShopDto;
import com.kudiukin.homework9.service.ShopService;
import com.kudiukin.homework9.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static com.kudiukin.homework9.converter.ShopConverter.convertShopDto2ShopModel;
import static com.kudiukin.homework9.converter.ShopConverter.convertShopModel2ShopDto;

@Controller
@RequestMapping(path="/api/shop")
@Slf4j
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createShop(@ModelAttribute("shop") ShopDto shopDto) {
        shopService.createShop(convertShopDto2ShopModel(shopDto));
        log.info("New shop is created with name [{}]", shopDto.getName());
        return "/shop/createShopSuccess";
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public String getShopById(@ModelAttribute("shopById") ShopDto shopDto, Model model) throws NotFoundException {
        ShopDto shopById = convertShopModel2ShopDto(shopService.getShopById(shopDto.getId()));
        model.addAttribute("shopById", shopById);
        return "/shop/getShopSuccess";
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.POST})
    public String deleteShop(@ModelAttribute("shop") ShopDto shopDto) throws NotFoundException {
        shopService.deleteShop(shopDto.getId());
        log.info("Shop with name [{}} and ID [{}] is deleted", shopDto.getName(), shopDto.getId());
        return "/shop/deleteShopSuccess";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createShopView(Model model) {
        model.addAttribute("shop", new ShopDto());
        return "/shop/createShop";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getShopByIdView(Model model) {
        model.addAttribute("shopById", new ShopDto());
        return "/shop/getShop";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteShopView(Model model) {
        model.addAttribute("shop", new ShopDto());
        return "/shop/deleteShop";
    }

    @GetMapping( "/all")
    public String getAllShops(Model model) {
        model.addAttribute("all", shopService.getAllShops().stream()
                .map(ShopConverter::convertShopModel2ShopDto).collect(Collectors.toList()));
        return "/shop/allShops";
    }
}
