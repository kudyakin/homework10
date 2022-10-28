package com.kudiukin.homework10.service;

import com.kudiukin.homework10.NotFoundException;
import com.kudiukin.homework10.model.Shop;

import java.util.List;

public interface ShopService {

    Shop createShop(Shop shop);

    void deleteShop(Long shopId) throws NotFoundException;

    Shop getShopById(Long shopId) throws NotFoundException;

    List<Shop> getAllShops();
}
