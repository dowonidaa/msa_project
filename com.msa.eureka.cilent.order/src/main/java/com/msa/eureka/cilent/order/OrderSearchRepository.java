package com.msa.eureka.cilent.order;

import com.msa.eureka.cilent.order.dto.ResponseOrder;
import com.msa.eureka.cilent.order.dto.SearchOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderSearchRepository {

    Page<ResponseOrder> searchOrders(SearchOrder searchOrder, Pageable pageable, String username, String role);
}
