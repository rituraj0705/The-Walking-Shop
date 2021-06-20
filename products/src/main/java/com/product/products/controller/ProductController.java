package com.product.products.controller;

import com.product.products.model.Product;
import com.product.products.service.ProductService;
import com.product.products.utils.ProductResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    ProductResponseVo productResponseVo;

    @RequestMapping(value="/products", method = RequestMethod.POST)
    public List<Product> addProducts(@RequestBody List<Product> products) {
        if (log.isInfoEnabled()) {
            log.info("Starting addProduct() method of ProductController");
        }
        List<Product> productList = productService.addProducts(products);
        if (log.isInfoEnabled()) {
            log.info("Ending addProduct() method of ProductController");
        }
        return productList;
    }

    @RequestMapping(value="/product", method = RequestMethod.POST)
    public ResponseEntity<ProductResponseVo> addProduct(@RequestBody Product product) {
        if (log.isInfoEnabled()) {
            log.info("Starting addProduct() method of ProductController");
        }
        productService.addProduct(product);
        productResponseVo.setProduct(product);
        productResponseVo.setStatusMessage("added");
        if (log.isInfoEnabled()) {
            log.info("Ending addProduct() method of ProductController");
        }
        return new ResponseEntity<ProductResponseVo>(productResponseVo, HttpStatus.OK);
    }

    @RequestMapping(value="/product/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ProductResponseVo> deleteProductById(@PathVariable(value="id") Long productId) {
        if (log.isInfoEnabled()) {
            log.info("Starting deleteProductById() method of ProductController");
        }
        productResponseVo.setStatusMessage("deleted");
        productResponseVo.setProduct(productService.getProductById(productId));
        productService.deleteProductById(productId);
        if (log.isInfoEnabled()) {
            log.info("Ending addProduct() method of ProductController");
        }
        return new ResponseEntity<ProductResponseVo>(productResponseVo, HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Product> getAllProducts () {
        if (log.isInfoEnabled()) {
            log.info("Executed getAllProducts() method of ProductController");
        }
        return productService.getAllProducts();
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public Product getProductByID (@PathVariable Long productId) {
        if (log.isInfoEnabled()) {
            log.info("Executed getProductByID() method of ProductController");
        }
        return productService.getProductById(productId);
    }
}
