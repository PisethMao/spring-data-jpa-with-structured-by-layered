package com.co.istad.piseth.spring_web_mvc.controller;

import com.co.istad.piseth.spring_web_mvc.dto.CreateProductRequest;
import com.co.istad.piseth.spring_web_mvc.dto.PatchProductRequest;
import com.co.istad.piseth.spring_web_mvc.dto.ProductResponse;
import com.co.istad.piseth.spring_web_mvc.dto.UpdateProductRequest;
import com.co.istad.piseth.spring_web_mvc.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{code}")
    public ProductResponse getProductByCode(@PathVariable String code) {
        log.info("Getting product with code: {}", code);
        return productService.getProductByCode(code);
    }

    @GetMapping
    public Page<ProductResponse> getProducts(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize
    ) {
        return productService.getProducts(pageNumber, pageSize);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductResponse createNew(@Valid @RequestBody CreateProductRequest createProductRequest) {
        log.info("Creating new product: {}", createProductRequest);
        return productService.createProduct(createProductRequest);
    }

    @PutMapping("/{code}")
    public ProductResponse updateByCode(@PathVariable String code, @Valid @RequestBody UpdateProductRequest updateProductRequest) {
        log.info("Updating product with code: {} with {}", code, updateProductRequest);
        return productService.updateProduct(code, updateProductRequest);
    }

    @PatchMapping("/{code}")
    public ProductResponse patchByCode(@PathVariable String code,
                                       @RequestBody PatchProductRequest patchProductRequest) {
        log.info("Patching product with code: {} with {}", code, patchProductRequest);
        return productService.patchProduct(code, patchProductRequest);
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByCode(@PathVariable String code) {
        log.info("Deleting product with code: {}", code);
        productService.deleteProduct(code);
    }
}
