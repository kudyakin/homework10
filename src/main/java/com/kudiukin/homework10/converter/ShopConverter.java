package com.kudiukin.homework10.converter;

import com.kudiukin.homework10.dto.ShopDto;
import com.kudiukin.homework10.model.Shop;

public final class ShopConverter {

    public static ShopDto convertShopModel2ShopDto (Shop shop) {
        ShopDto shopDto = new ShopDto();
        shopDto.setId(shop.getId());
        shopDto.setName(shop.getName());
        shopDto.setAddress(shop.getAddress());
        shopDto.setProducts(shop.getProducts());
        return shopDto;
    }

    public static Shop convertShopDto2ShopModel (ShopDto shopDto) {
        Shop shop = new Shop();
        shop.setId(shopDto.getId());
        shop.setName(shopDto.getName());
        shop.setAddress(shopDto.getAddress());
        shop.setProducts(shopDto.getProducts());
        return shop;
    }
}
