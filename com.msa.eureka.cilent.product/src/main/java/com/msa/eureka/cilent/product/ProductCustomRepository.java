package com.msa.eureka.cilent.product;

import com.msa.eureka.cilent.product.dto.ResponseProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductCustomRepository {

    Page<ResponseProduct> searchProduct(Pageable pageable, String username, String role);
}
