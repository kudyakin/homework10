package com.kudiukin.homework10.facade;

import com.kudiukin.homework10.NotFoundException;
import com.kudiukin.homework10.dto.ShopDto;

import java.util.List;

public interface ShopFacade {

    ShopDto createShop(ShopDto shopDto);

    void deleteShop(ShopDto shopDto) throws NotFoundException;

    ShopDto getShopById(ShopDto shopDto) throws NotFoundException;

    List<ShopDto> getAllShops();


}
