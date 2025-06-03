package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.BenefitRequest;

import java.util.Map;

public class APIClient {

    public static Response enrollDentalBenefit(Map<String, String> cookies, BenefitRequest payload, String referer) {
        return RestAssured.given()
                .baseUri("https://partner-dev-benefits.plansource.com")
                .basePath("/v1/self_service/coverages")
                .cookies(cookies)
                .header("Referer", referer)
                .header("Content-Type", "application/json")
                .body(payload)
                .put();
    }
}
