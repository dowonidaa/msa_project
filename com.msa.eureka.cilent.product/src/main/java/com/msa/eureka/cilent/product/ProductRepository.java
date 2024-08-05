package com.msa.eureka.cilent.product;

import com.msa.eureka.cilent.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductCustomRepository {
}
