package com.example.jinxiMall.controller;

import com.example.jinxiMall.Repository.InventoryRepository;
import com.example.jinxiMall.Repository.ProductRepository;
import com.example.jinxiMall.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InventoryRepository inventoryRepository;

    //创建新商品
    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody Product product) throws Exception {
        Long id = productRepository.save(product).getId();
        HttpHeaders responseHeaders = setLocationInResponseHeader(id);
        inventoryRepository.saveByProductId(id);
        return new ResponseEntity<Product>(productRepository.findProductById(id), responseHeaders, HttpStatus.CREATED);
    }

    //修改商品信息
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable Long id, @RequestBody Product product) throws Exception {
        productRepository.findById(id);
        productRepository.updateById(id, product.getName(), product.getDescription(), product.getPrice());
    }

    //根据商品id查找商品
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable Long id) {
        return productRepository.findProductById(id);
    }

    //查找所有商品
    //根据name和描述模糊查询
    //根据name查询
    @GetMapping
    public List<Product> getProducts(@RequestParam(value = "name", required = false, defaultValue = "") String name, @RequestParam(value = "description", required = false, defaultValue = "") String description) {
        if (!name.isEmpty() && !description.isEmpty()) {
            return productRepository.findByNameAndDescriptionContaining(name, description);
        } else if (!name.isEmpty()) {
            return productRepository.findByName(name);
        } else {
            return productRepository.findAll();
        }
    }

    private HttpHeaders setLocationInResponseHeader(Long id) {
        URI location = URI.create("http://192.168.56.1:8083/products/" + id);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        return responseHeaders;
    }
}
