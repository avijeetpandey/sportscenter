package com.ecommerce.sportscenter.entity;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("BasketItem")
public class BasketItem {
    @Id
    private Integer id;
    private String name;
    private String description;
    private Long price;
    private Integer quantity;
    private String pictureUrl;
    private String productBrand;
    private String productType;
}
