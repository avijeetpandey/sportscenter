package com.ecommerce.sportscenter.controller;

import com.ecommerce.sportscenter.entity.Basket;
import com.ecommerce.sportscenter.entity.BasketItem;
import com.ecommerce.sportscenter.model.BasketItemResponse;
import com.ecommerce.sportscenter.model.BasketResponse;
import com.ecommerce.sportscenter.service.BasketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/baskets")
public class BasketController {
    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping()
    public List<BasketResponse> getBaskets() {
        return basketService.getAllBaskets();
    }

    @GetMapping("/{id}")
    public BasketResponse getBasketById(@PathVariable String id) {
        return basketService.getBasketById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBasketById(@PathVariable String id) {
        basketService.deleteBasketById(id);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<BasketResponse> createBasket(@RequestBody BasketResponse basketResponse) {
        Basket basket = convertToBasket(basketResponse);
        BasketResponse createdBasket = basketService.createBasket(basket);
        return new ResponseEntity<>(createdBasket, HttpStatus.CREATED);
    }

    private Basket convertToBasket(BasketResponse basketResponse) {
        Basket basket = new Basket();
        basket.setId(basketResponse.getId());
        basket.setItems(mapBasketItemResponseToEntities(basketResponse.getItems()));
        return basket;
    }

    private List<BasketItem> mapBasketItemResponseToEntities(List<BasketItemResponse> itemResponse) {
        return itemResponse
                .stream()
                .map(this::convertToBasketItem)
                .toList();
    }

    private BasketItem convertToBasketItem(BasketItemResponse basketItemResponse) {
        BasketItem basketItem = new BasketItem();
        basketItem.setId(basketItemResponse.getId());
        basketItem.setName(basketItemResponse.getName());
        basketItem.setPrice(basketItemResponse.getPrice());
        basketItem.setQuantity(basketItemResponse.getQuantity());
        basketItem.setPictureUrl(basketItemResponse.getPictureUrl());
        basketItem.setProductBrand(basketItemResponse.getProductBrand());
        basketItem.setProductType(basketItemResponse.getProductType());
        return basketItem;
    }
}
