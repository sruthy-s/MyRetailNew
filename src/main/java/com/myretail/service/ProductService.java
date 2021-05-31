package com.myretail.service;

import com.myretail.exception.ProductException;
import com.myretail.model.CreateProduct;
import com.myretail.model.Price;
import com.myretail.model.Product;
import com.myretail.model.UpdateProduct;
import com.myretail.repository.ProductRepository;
import com.myretail.repository.model.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ProductService {
    private static final String PRODUCT_NAME = "name";
    private static final String CURRENCY_CODE = "currency_code";
    @Autowired
    ProductRepository productRepository;
    @Autowired
    RestTemplate restTemplate;
    @Value("${api.base.url}")
    String apiBaseUrl;

    public Product getProductById(int id) {
        Optional<ProductEntity> productEntityOpt = productRepository.findById(id);

        if (productEntityOpt.isPresent()) {
            ProductEntity productEntity = productEntityOpt.get();

            Price price = restTemplate.getForEntity(getPriceApiUrl(id), Price.class).getBody();
            return new Product(productEntity.getId(), productEntity.getName(), price);
        } else {
            throw new ProductException(HttpStatus.NOT_FOUND, String.format("Couldn't find product with ID: %s", id));
        }
    }

    public int createProduct(CreateProduct product) {
        validateName(product.getName());
        validatePrice(product.getCurrentPrice());
        ProductEntity productEntity = new ProductEntity(product.getName());
        ProductEntity savedProduct = productRepository.save(productEntity);

        Price price = product.getCurrentPrice();
        restTemplate.postForEntity(getPriceApiUrl(savedProduct.getId()), price, void.class);
        return savedProduct.getId();
    }

    public Product updateProduct(int productId, UpdateProduct product) {
        restTemplate.put(getPriceApiUrl(productId), product.getPrice());
        return getProductById(productId);
    }

    private String getPriceApiUrl(int productId) {
        String url = String.format("%s%s/price", apiBaseUrl, productId);
        return url;
    }

    private void validateName(String name) {
        checkIfEmpty(PRODUCT_NAME, name);
    }

    private void validatePrice(Price price) {
        if (null == price) {
            throw new ProductException(HttpStatus.BAD_REQUEST, "Price cannot be empty in the request");
        }
        checkIfEmpty(CURRENCY_CODE, price.getCurrencyCode());
        if (price.getValue() <= 0) {
            throw new ProductException(HttpStatus.BAD_REQUEST, "Price value should be greater than 0.");
        }
    }

    private void checkIfEmpty(String attrName, String attrValue) {
        if (attrValue == null || attrValue.trim().isEmpty()) {
            throw new ProductException(HttpStatus.BAD_REQUEST, String.format("%s cannot be empty in the request", attrName));
        }
    }
}
