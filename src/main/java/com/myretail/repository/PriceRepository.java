package com.myretail.repository;

import com.myretail.repository.model.PriceEntity;
import org.springframework.data.repository.CrudRepository;

public interface PriceRepository extends CrudRepository<PriceEntity, Integer> {
}
