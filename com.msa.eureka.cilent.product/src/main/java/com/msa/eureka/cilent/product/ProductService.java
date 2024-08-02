package com.msa.eureka.cilent.product;

import com.msa.eureka.cilent.product.entity.Product;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final String ENTITY_NOTFOUND = "상픔을 찾지 못했습니다. 상품 id : ";


    @Transactional
    public void addProduct(RequestProduct request) {
        Product product = new Product(request);
        productRepository.save(product);
    }

    public ResponseProduct getProductById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOTFOUND + productId));
        return new ResponseProduct(product);
    }

    @Transactional
    public void update(Long productId, RequestProduct request) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOTFOUND + productId));
        try {
            product.update(request);
        } catch (Exception e) {
            throw new IllegalArgumentException("업데이트 실패");
        }
    }

    @Transactional
    public void delete(Long productId) {
        try {
            Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOTFOUND + productId));
            productRepository.delete(product);
        } catch (Exception e) {
            throw new IllegalArgumentException("삭제 실패");
        }
    }

    public List<ResponseProduct> getProducts() {
        List<Product> products = productRepository.findAll();
        return productToResponseProduct(products);
    }


    private List<ResponseProduct> productToResponseProduct(List<Product> products) {
        List<ResponseProduct> responses = new ArrayList<>();
        for (Product product : products) {
            ResponseProduct responseProduct = new ResponseProduct(product);
            responses.add(responseProduct);
        }
        return responses;
    }
}
