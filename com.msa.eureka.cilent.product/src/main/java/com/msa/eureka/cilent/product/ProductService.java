package com.msa.eureka.cilent.product;

import com.msa.eureka.cilent.product.client.UserClient;
import com.msa.eureka.cilent.product.dto.RequestProduct;
import com.msa.eureka.cilent.product.dto.ResponseProduct;
import com.msa.eureka.cilent.product.entity.Product;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ProductService {

    private final UserClient userClient;
    private final ProductRepository productRepository;
    private final String ENTITY_NOTFOUND = "상픔을 찾지 못했습니다. 상품 id : ";


    @Transactional
    public void createProduct(RequestProduct request, String username, String role) {

        if (!userClient.validated(username, role)) {
            throw new IllegalArgumentException("유저 정보가 일치하지 않습니다.");
        }

        if (role.equals("MEMBER")) {
            throw new IllegalArgumentException("유저 권한이 없습니다.");
        }
        Product product = new Product(request);
        productRepository.save(product);
    }

    public ResponseProduct getProductById(Long productId) {
        log.info("productId = {}",productId);
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOTFOUND + productId));
        return new ResponseProduct(product);
    }

    @Transactional
    public void update(Long productId, RequestProduct request) {
        Product product = findById(productId);
        try {
            product.update(request);
        } catch (Exception e) {
            throw new IllegalArgumentException("업데이트 실패");
        }
    }

    @Transactional
    public void delete(Long productId) {
        try {
            Product product = findById(productId);
            product.delete(true);
        } catch (Exception e) {
            throw new IllegalArgumentException("삭제 실패");
        }
    }

    public List<ResponseProduct> getProducts() {
        List<Product> products = productRepository.findAll();
        return productToResponseProduct(products);
    }


    @Transactional
    public void reduceQuantity(Long productId, int quantity) {
        Product product = findById(productId);
        if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException("상품: " + productId + " 수량이 0 입니다. ");
        }
        product.reduceQuantity(quantity);
    }

    private Product findById(Long productId) {
        return productRepository.findById(productId).filter(p -> !p.getIsDeleted()).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOTFOUND + productId));
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
