package com.kslima.ksdeliver.services;

import com.kslima.ksdeliver.dto.OrderDTO;
import com.kslima.ksdeliver.entities.Order;
import com.kslima.ksdeliver.entities.OrderStatus;
import com.kslima.ksdeliver.entities.Product;
import com.kslima.ksdeliver.repositories.OrderRepository;
import com.kslima.ksdeliver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {
        return repository.findOrdersWithProducts().stream()
                .map(OrderDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        Order order = new Order();
        order.setAddress(dto.getAddress());
        order.setLatitude(dto.getLatitude());
        order.setLongitude(dto.getLongitude());
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.PENDING);

        dto.getProducts().forEach(d -> {
            Product product = productRepository.getOne(d.getId());
            order.getProducts().add(product);
        });

        return new OrderDTO(repository.save(order));
    }

    @Transactional
    public OrderDTO setDeliveered(Long id) {
        Order order = repository.getOne(id);
        order.setStatus(OrderStatus.DELIVERED);
        order = repository.save(order);

        return new OrderDTO(order);
    }



}
