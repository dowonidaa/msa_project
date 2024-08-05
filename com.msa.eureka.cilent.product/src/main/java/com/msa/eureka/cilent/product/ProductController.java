package com.msa.eureka.cilent.product;

import com.msa.eureka.cilent.product.dto.RequestProduct;
import com.msa.eureka.cilent.product.dto.ResponseProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ResponseProduct>> getProducts(Pageable pageable,
                                                             @RequestHeader("X-User-Id") String username,
                                                             @RequestHeader("X-Role")String role) {
        Page<ResponseProduct> products = productService.getProducts(pageable, username, role);
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody RequestProduct request,
                                           @RequestHeader("X-User-Id") String username,
                                           @RequestHeader("X-Role")String role) {
        productService.createProduct(request, username, role);
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

    @GetMapping("/{productId}/reduceQuantity")
    public ResponseEntity<?> reduceQuantity(@PathVariable Long productId, @RequestParam int quantity) {
        productService.reduceQuantity(productId, quantity);
        return ResponseEntity.ok().build();
    }
}
