package com.ecommerce.sportscenter.service;

import com.ecommerce.sportscenter.entity.Basket;
import com.ecommerce.sportscenter.entity.BasketItem;
import com.ecommerce.sportscenter.model.BasketItemResponse;
import com.ecommerce.sportscenter.model.BasketResponse;
import com.ecommerce.sportscenter.repository.BasketRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class BasketServiceImpl implements  BasketService {
    private final BasketRepository basketRepository;

    public BasketServiceImpl(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Override
    public List<BasketResponse> getAllBaskets() {
        List<Basket> basketResponses = (List<Basket>) basketRepository.findAll();
        return basketResponses
                    .stream()
                    .map(this::convertToBasketResponse)
                    .collect(Collectors.toList());
    }

    @Override
    public BasketResponse getBasketById(String basketId) {
        return null;
    }

    @Override
    public void deleteBasketById(String basketId) {

    }

    @Override
    public BasketResponse createBasket(Basket basket) {
        return null;
    }

    private BasketResponse convertToBasketResponse(Basket basket) {

        if (basket == null) {
            return null;
        }

        List<BasketItemResponse> basketItemResponses = basket
                                                        .getItems()
                                                        .stream()
                                                        .map(this::convertToBasketItemResponse)
                                                        .toList();

        return BasketResponse
                .builder()
                .id(basket.getId())
                .items(basketItemResponses)
                .build();
    }

    private BasketItemResponse convertToBasketItemResponse(BasketItem basketItem) {
        return BasketItemResponse
                    .builder()
                    .id(basketItem.getId())
                    .price(basketItem.getPrice())
                    .quantity(basketItem.getQuantity())
                    .name(basketItem.getName())
                    .pictureUrl(basketItem.getPictureUrl())
                    .productBrand(basketItem.getProductBrand())
                    .productType(basketItem.getProductType())
                    .build();
    }
}
