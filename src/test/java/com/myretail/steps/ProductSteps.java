package com.myretail.steps;

import com.myretail.model.CreateProduct;
import com.myretail.model.Price;
import com.myretail.model.Product;
import com.myretail.model.UpdateProduct;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductSteps {
    private static final String LOCATION_HEADER = "Location";
    private RestTemplate restTemplate = new RestTemplate();
    private String productUrl = null;
    @Value("${api.base.url}")
    String apiBaseUrl;

    @When("^I create product with name (.*), currency (.*), value (.*)$")
    public void createProduct(String name, String cur, float val) {
        Price p = new Price(val, cur);
        CreateProduct product = new CreateProduct(name, p);
        ResponseEntity<Object> response = restTemplate.postForEntity(apiBaseUrl, product, Object.class);
        productUrl = response.getHeaders().get(LOCATION_HEADER).get(0);
    }

    @When("^I update product to have currency (.*) and value (.*)$")
    public void updateProduct(String cur, float val) {
        Price price = new Price(val, cur);
        UpdateProduct product = new UpdateProduct();
        product.setPrice(price);

        restTemplate.put(productUrl, product, Product.class);
    }

    @Then("^the product should have name (.*), currency (.*), value (.*)$")
    public void getProduct(String name, String cur, float val) {
        Product product = restTemplate.getForEntity(productUrl, Product.class).getBody();
        Price currentPrice = product.getCurrentPrice();

        assertThat(name).isEqualTo(product.getName());
        assertThat(currentPrice).isNotNull();
        assertThat(val).isEqualTo(currentPrice.getValue());
        assertThat(cur).isEqualTo(currentPrice.getCurrencyCode());
    }

    @When("^I get a product that doesn't exist, it should thrown an exception$")
    public void getInvalidProduct() {
        String invalidUrl = this.apiBaseUrl + 999;

        HttpClientErrorException exception = Assertions.assertThrows(
                HttpClientErrorException.class,
                () -> restTemplate.getForEntity(invalidUrl, Product.class)
        );
        assertThat(HttpStatus.NOT_FOUND).isEqualTo(exception.getStatusCode());
        assertThat(exception.getMessage()).contains("Couldn't find product with ID: 999");
    }

    @When("^I create a product with invalid payload, it should thrown an exception$")
    public void createInvalidProduct() {
        HttpServerErrorException exception = Assertions.assertThrows(
                HttpServerErrorException.class,
                () -> restTemplate.postForEntity(apiBaseUrl, "abc", String.class)
        );
        assertThat(HttpStatus.INTERNAL_SERVER_ERROR).isEqualTo(exception.getStatusCode());
    }
}
