package com.ecommerce.sportscenter.service;

import com.ecommerce.sportscenter.entity.Product;
import com.ecommerce.sportscenter.model.ProductResponse;
import com.ecommerce.sportscenter.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class ProductServiceImpl implements  ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse getProductById(Integer productId) {
        log.info("fetching product by id: " + productId);
        Product product = productRepository.findById(productId)
                        .orElseThrow(()-> new RuntimeException("product does not exist"));
        ProductResponse productResponse = convertToProductResponse(product);
        log.info("fetched product by id");
        return productResponse;
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        log.info("fetching all products");
        List<Product> products = productRepository.findAll();
        List<ProductResponse> productResponses = products.stream().map(this::convertToProductResponse).toList();
        log.info("fetched all products");
        return productResponses;
    }

    private ProductResponse convertToProductResponse(Product product) {
       return ProductResponse
               .builder()
               .id(product.getId())
               .name(product.getName())
               .description(product.getDescription())
               .price(product.getPrice())
               .pictureUrl(product.getPictureUrl())
               .productBrandName(product.getBrand().getName())
               .productTypeName(product.getType().getName())
               .build();
    }
}
