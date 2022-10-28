package com.kudiukin.homework10.facade;

import com.kudiukin.homework10.NotFoundException;
import com.kudiukin.homework10.dto.ProductDto;

import java.util.List;

public interface ProductFacade {

    ProductDto createProduct(ProductDto productDto) throws NotFoundException;

    ProductDto getProductById(ProductDto productDto) throws NotFoundException;

    ProductDto updateProduct(ProductDto productDto);

    void deleteProduct(ProductDto productDto) throws NotFoundException;

    List<ProductDto> getAllProducts();


}
