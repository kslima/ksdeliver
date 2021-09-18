package com.kslima.ksdeliver.services;

import com.kslima.ksdeliver.dto.ProductDTO;
import com.kslima.ksdeliver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        return repository.findAllByOrderByNameAsc().stream().map(ProductDTO::new).collect(Collectors.toList());
    }
}
