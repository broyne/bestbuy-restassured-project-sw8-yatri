package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProductsCRUDTest {
    static ValidatableResponse response;

    static int id;
    static String name = "Duracell - AAA Batteries (8-Pack)" + TestUtils.getRandomValue();
    static String type = "HardGood" + TestUtils.getRandomValue();
    static Double price = 5.49;
    static String upc = TestUtils.getRandomValue();
    static Double shipping = 4.99;
    static String description = "Compatible with select electronic devices; AAA size; DURALOCK Power Preserve technology; 4-pack";
    static String manufacturer = "Duracell";
    static String model = "MN2400B4Z";
    static String url = "http://www.bestbuy.com/site/duracell-aaa-batteries-4-pack/43900.p?id=1051384074145&skuId=43900&cmp=RMXCC";
    static String image = "http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/products";
    }

    @Test(priority = 0)
    public void createProduct() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        Response response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .when()
                .body(productPojo)
                .post();
        response.prettyPrint();
        response.then().log().ifValidationFails().statusCode(201);
        id = response.jsonPath().get("id");
    }

    @Test(priority = 1)
    public void viewProduct() {
        Response response = given()
                .when()
                .get();

        response.then().statusCode(200);

        response.prettyPrint();
    }

    @Test(priority = 2)
    public void updateProduct() {
        name = ProductsCRUDTest.name + "Updated";
        type = ProductsCRUDTest.type + "Updated";
        price = 1600.00;
        upc = TestUtils.getRandomValue();
        shipping = 5.99;
        description = "Compatible with select electronic devices; AAA size; DURALOCK Preser technology; 4-pack";
        description = ProductsCRUDTest.description + "Updated";
        manufacturer = ProductsCRUDTest.manufacturer + "Updated";
        model = ProductsCRUDTest.model + "Updated";
        url = ProductsCRUDTest.url + "Updated";
        image = ProductsCRUDTest.image + "Updated";

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        Response response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .pathParam("id", id)
                .when()
                .body(productPojo)
                .put("/{id}");
        response.prettyPrint();
        response.then().log().ifValidationFails().statusCode(200);

    }

    @Test(priority = 3)
    public void deleteProduct() {
        given().log().ifValidationFails()
                .pathParam("id", id)
                .when()
                .delete("/{id}")
                .then().log().ifValidationFails().statusCode(200);

        given()
                .pathParam("id", id)
                .when()
                .get("/{id}")
                .then()
                .statusCode(404);
    }
}
