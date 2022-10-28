package com.kudiukin.homework10.facade;

import com.kudiukin.homework10.NotFoundException;
import com.kudiukin.homework10.converter.ProductConverter;
import com.kudiukin.homework10.dto.ProductDto;
import com.kudiukin.homework10.model.Product;
import com.kudiukin.homework10.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.kudiukin.homework10.converter.ProductConverter.convertProductModel2ProductDto;

@Component
public class ProductFacadeImpl implements ProductFacade{

    @Autowired
    private ProductService productService;

    @Override
    public ProductDto createProduct(ProductDto productDto) throws NotFoundException {
        Product newProduct = productService.createProduct(productDto.getName(), productDto.getPrice(), productDto.getShopId());
        return convertProductModel2ProductDto(newProduct);
    }

    @Override
    public ProductDto getProductById(ProductDto productDto) throws NotFoundException {
        Product getProduct = productService.getProductById(productDto.getProductId());
        return convertProductModel2ProductDto(getProduct);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        Product updatedProduct = productService.updateProduct(productDto.getProductId(), productDto.getName(),
                productDto.getPrice(), productDto.getShopId());
        return convertProductModel2ProductDto(updatedProduct);
    }

    @Override
    public void deleteProduct(ProductDto productDto) throws NotFoundException {
        productService.deleteProduct(productDto.getProductId());
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts().stream().map(ProductConverter::convertProductModel2ProductDto)
                .collect(Collectors.toList());
    }
}
