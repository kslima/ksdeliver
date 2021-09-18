package com.kslima.ksdeliver.repositories;

import com.kslima.ksdeliver.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
