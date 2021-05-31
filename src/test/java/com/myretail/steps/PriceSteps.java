package com.myretail.steps;

import com.myretail.model.Price;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceSteps {
    @Value("${api.base.url}")
    private String apiBaseUrl;
    private RestTemplate restTemplate = new RestTemplate();

    @When("^I create price with currency (.*) and value (.*)$")
    public void createPrice(String cur, float val) {
        Price p = new Price(val, cur);
        restTemplate.postForEntity(getPriceApiUrl(1), p, Price.class);
    }

    @Then("^the price should have currency (.*) and value (.*)$")
    public void getPrice(String cur, float val) {
        Price res = restTemplate.getForEntity(getPriceApiUrl(1), Price.class).getBody();

        assertThat(val).isEqualTo(res.getValue());
        assertThat(cur).isEqualTo(res.getCurrencyCode());
    }

    public String getPriceApiUrl(int productId) {
        String url = String.format("%s%s/price", apiBaseUrl, productId);
        return url;
    }
}
