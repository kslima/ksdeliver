package com.kslima.ksdeliver.services;

import com.kslima.ksdeliver.dto.ProductDTO;
import com.kslima.ksdeliver.entities.Product;
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

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setImageUri(dto.getImageUri());

        product = repository.save(product);
        return new ProductDTO(product);
    }
}
