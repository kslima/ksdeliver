package com.kslima.ksdeliver.services;

import com.kslima.ksdeliver.dto.OrderDTO;
import com.kslima.ksdeliver.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {
        return repository.findOrdersWithProducts().stream()
                .map(OrderDTO::new)
                .collect(Collectors.toList());
    }


}
