package com.kudiukin.homework6.converter;

import com.kudiukin.homework6.dto.ProductDto;
import com.kudiukin.homework6.model.Product;

public final class ProductConverter {

    public static ProductDto convertProductModel2ProductDto (Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setShopId(product.getShop().getId());
        productDto.setProductId(product.getId());
        productDto.setShop(product.getShop());
        return productDto;
    }
}
