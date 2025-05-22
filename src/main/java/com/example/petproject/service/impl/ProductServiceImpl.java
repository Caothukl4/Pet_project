package com.example.petproject.service.impl;

import com.example.petproject.dto.request.ProductRequest;
import com.example.petproject.dto.respone.ProductResponse;
import com.example.petproject.entity.Product;
import com.example.petproject.exception.APIException;
import com.example.petproject.repository.ProductRepository;
import com.example.petproject.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setOriginalPrice(request.getOriginalPrice());
        product.setCategory(request.getCategory());
        product.setStatus(request.getStatus());

        Product saved = productRepository.save(product);
        return modelMapper.map(saved, ProductResponse.class);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new APIException("Không tìm thấy sản phẩm với ID: " + id, 404));
        return modelMapper.map(product, ProductResponse.class);
    }


    @Override
    public List<ProductResponse> getProductByStatus(String status) {
        List<Product> products = productRepository.findByStatus(status);

        if (products.isEmpty()) {
            throw new APIException("Không tìm thấy sản phẩm với status: " + status, 404);
        }
        return products.stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getProductsByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category)
                .stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> searchProducts(String name) {
        return productRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new APIException("Không tìm thấy sản phẩm với ID: " + id, 404));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        product.setStock(request.getStock());
        product.setOriginalPrice(request.getOriginalPrice());
        product.setStatus(request.getStatus());

        Product updated = productRepository.save(product);
        return modelMapper.map(updated, ProductResponse.class);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new APIException("Không tìm thấy sản phẩm với ID: " + id, 404));
        productRepository.delete(product);
    }
}
