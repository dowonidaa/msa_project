package com.msa.eureka.cilent.product;

import com.msa.eureka.cilent.product.dto.RequestProduct;
import com.msa.eureka.cilent.product.dto.ResponseProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ResponseProduct>> getProducts() {
        List<ResponseProduct> products = productService.getProducts();
        return ResponseEntity.ok(null);
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody RequestProduct request) {
        productService.addProduct(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long productId) {
        ResponseProduct response = productService.getProductById(productId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long productId, @RequestBody RequestProduct request) {
            productService.update(productId, request);
            return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long productId) {
            productService.delete(productId);
            return ResponseEntity.ok().build();
    }
}
