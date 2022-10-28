package com.kudiukin.homework10.facade;

import com.kudiukin.homework10.NotFoundException;
import com.kudiukin.homework10.converter.ShopConverter;
import com.kudiukin.homework10.dto.ShopDto;
import com.kudiukin.homework10.model.Shop;
import com.kudiukin.homework10.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.kudiukin.homework10.converter.ShopConverter.convertShopDto2ShopModel;
import static com.kudiukin.homework10.converter.ShopConverter.convertShopModel2ShopDto;

@Component
public class ShopFacadeImpl implements ShopFacade{

    @Autowired
    private ShopService shopService;

    @Override
    public ShopDto createShop(ShopDto shopDto) {
        Shop newShop = shopService.createShop(convertShopDto2ShopModel(shopDto));
        return convertShopModel2ShopDto(newShop);
    }

    @Override
    public void deleteShop(ShopDto shopDto) throws NotFoundException {
        shopService.deleteShop(shopDto.getId());
    }

    @Override
    public ShopDto getShopById(ShopDto shopDto) throws NotFoundException {
        Shop getShop = shopService.getShopById(shopDto.getId());
        return convertShopModel2ShopDto(getShop);
    }

    @Override
    public List<ShopDto> getAllShops() {
        return shopService.getAllShops().stream().map(ShopConverter::convertShopModel2ShopDto)
                .collect(Collectors.toList());
    }
}
