package com.matheus.crm.product.service;

import com.matheus.crm.product.dto.ProductDTO;
import com.matheus.crm.product.entity.Product;
import com.matheus.crm.product.repository.ProductRepository;
import com.matheus.crm.product.service.exception.DatabaseException;
import com.matheus.crm.product.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDTO findProductBySku(Integer sku){
        Optional<Product> productOptional = productRepository.findProductBySku(sku);
        Product entity = productOptional.orElseThrow(() -> new NotFoundException("SKU: " + sku + " not found!"));
        return new ProductDTO(entity);

    }

    @Transactional(readOnly = true)
    public List<ProductDTO> findAllProducts(){
        List<Product> list = productRepository.findAll();

        List<ProductDTO> listDto = list.stream()
                .map(x -> new ProductDTO(x)).collect(Collectors.toList());
        return listDto;
    }

    @Transactional
    public void deleteProductBySku(Integer sku) {
        try {
            productRepository.deleteBySku(sku);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("SKU not found!");
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        };

    }

    @Transactional
    public ProductDTO addProduct(ProductDTO product) {
        Product entity = new Product();
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setImgUrl(product.getImgUrl());
        entity.setCategory(product.getCategory());
        entity.setSku(product.getSku());
        entity.setSupplierId(product.getSupplierId());

        entity = productRepository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO updateProduct(Long id, ProductDTO product) {
        Product entity = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("product not found for id: " + id));

        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setImgUrl(product.getImgUrl());
        entity.setSku(product.getSku());
        entity.setSupplierId(product.getSupplierId());


        Product updatedproduct = productRepository.save(entity);

        return new ProductDTO(updatedproduct);
    }
}
