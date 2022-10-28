package com.kudiukin.homework10.service;

import com.kudiukin.homework10.model.Product;
import com.kudiukin.homework10.repository.ProductRepository;
import com.kudiukin.homework10.NotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    private final ShopService shopService;

    public ProductServiceImpl(ProductRepository productRepository, ShopService shopService) {
        this.productRepository = productRepository;
        this.shopService = shopService;
    }

    @Override
    public Product createProduct(String name, BigDecimal price, Long shopId) throws NotFoundException {
        Product product = new Product(name, price);
        product.setShop(shopService.getShopById(shopId));
        shopService.getShopById(shopId).getProducts().add(product);
        productRepository.save(product);
        return product;
    }

    @Override
    public Product getProductById(Long productId) throws NotFoundException {
        if (productRepository.findById(productId).isPresent()) {
            return (productRepository.findById(productId).get());
        } else {
            throw new NotFoundException("Product with ID #" + productId + " is not found");
        }
    }

    @Override
    public Product updateProduct(Long productId, String name, BigDecimal price, Long shopId) {
        return productRepository.findById(productId)
                .map(entity -> {
                    entity.setName(name);
                    entity.setPrice(price);
                    try {
                        entity.setShop(shopService.getShopById(shopId));
                    } catch (NotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    return productRepository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Not Found id = " + productId));
    }

    @Override
    public void deleteProduct(Long productId) throws NotFoundException {
        if (productRepository.existsById(productId)) {
            shopService.getShopById((productRepository.findById(productId).get()).getShop().getId()).getProducts().remove(getProductById(productId));
            productRepository.deleteById(productId);
        } else {
            throw new NotFoundException("Product with ID #" + productId + " is not found");
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }
}
