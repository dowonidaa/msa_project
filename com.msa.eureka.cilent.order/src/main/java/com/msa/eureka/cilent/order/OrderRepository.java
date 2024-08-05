package com.msa.eureka.cilent.order;

import com.msa.eureka.cilent.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderCustomRepository {

}
