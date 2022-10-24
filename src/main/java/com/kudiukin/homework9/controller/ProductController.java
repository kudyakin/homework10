package com.kudiukin.homework9.controller;

import com.kudiukin.homework9.converter.ProductConverter;
import com.kudiukin.homework9.dto.ProductDto;
import com.kudiukin.homework9.service.ProductService;
import com.kudiukin.homework9.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static com.kudiukin.homework9.converter.ProductConverter.convertProductModel2ProductDto;

@Controller
@RequestMapping(path="/api/product")
@Slf4j
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createProduct(@ModelAttribute("product") ProductDto productDto) throws NotFoundException {
        productService.createProduct(productDto.getName(), productDto.getPrice(), productDto.getShopId());
        log.info("New product is created with name [{}] and price [{}]", productDto.getName(), productDto.getPrice());
        return "/product/createProductSuccess";
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public String getProductById(@ModelAttribute("productById") ProductDto productDto, Model model) throws NotFoundException {
        ProductDto productById = convertProductModel2ProductDto(productService.getProductById(productDto.getProductId()));
        model.addAttribute("productById", productById);
        return "/product/getProductSuccess";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateProduct(@ModelAttribute("product") ProductDto productDto) {
        productService.updateProduct(productDto.getProductId(), productDto.getName(), productDto.getPrice(), productDto.getShopId());
        log.info("Product with ID [{}] is updated", productDto.getProductId());
        return "/product/updateProductSuccess";
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.POST})
    public String deleteProduct(@ModelAttribute("product") ProductDto productDto) throws NotFoundException {
        productService.deleteProduct(productDto.getProductId());
        log.info("Product with ID [{}] is deleted", productDto.getProductId());
        return "/product/deleteProductSuccess";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createProductView(Model model) {
        model.addAttribute("product", new ProductDto());
        return "/product/createProduct";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getProductByIdView(Model model) {
        model.addAttribute("productById", new ProductDto());
        return "/product/getProduct";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateProductView(Model model) {
        model.addAttribute("product", new ProductDto());
        return "/product/updateProduct";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteProductView(Model model) {
        model.addAttribute("product", new ProductDto());
        return "/product/deleteProduct";
    }

    @GetMapping( "/all")
    public String getAllProducts(Model model) {
        model.addAttribute("all", productService.getAllProducts().stream()
                .map(ProductConverter::convertProductModel2ProductDto).collect(Collectors.toList()));
        return "/product/allProducts";
    }
}
