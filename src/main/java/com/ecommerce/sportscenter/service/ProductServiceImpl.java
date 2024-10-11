package com.ecommerce.sportscenter.service;

import com.ecommerce.sportscenter.entity.Product;
import com.ecommerce.sportscenter.model.ProductResponse;
import com.ecommerce.sportscenter.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    public Page<ProductResponse> getAllProducts(Pageable pageable,
                                                Integer brandId,
                                                Integer typeId,
                                                String keyword) {
        log.info("fetching all products");
        Specification<Product> spec = Specification.where(null);

        if (brandId != null) {
            spec = spec.and(((root, _, criteriaBuilder) -> criteriaBuilder.equal(root.get("brandId"), brandId)));
        }

        if (typeId != null) {
            spec = spec.and(((root, _, criteriaBuilder) -> criteriaBuilder.equal(root.get("typeId"), typeId)));
        }

        if (keyword != null && !keyword.isEmpty()) {
            spec = spec.and(((root, _, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + keyword + "%")));
        }

        return productRepository.findAll(spec, pageable).map(this::convertToProductResponse);
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
