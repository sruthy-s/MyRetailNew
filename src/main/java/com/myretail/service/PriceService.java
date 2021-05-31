package com.myretail.service;

import com.myretail.model.Price;
import com.myretail.repository.PriceRepository;
import com.myretail.repository.model.PriceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PriceService {
    @Autowired
    PriceRepository priceRepository;

    public void savePrice(int productId, Price price) {
        PriceEntity priceEntity = new PriceEntity(productId, price.getCurrencyCode(), price.getValue());
        priceRepository.save(priceEntity);
    }

    public Price getPrice(int productId) {
        Optional<PriceEntity> priceEntity = priceRepository.findById(productId);
        Price price = priceEntity.isPresent() ?
                new Price(priceEntity.get().getValue(), priceEntity.get().getCurrency())
                : null;
        return price;
    }
}
