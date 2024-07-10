package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {

    static ValidatableResponse response;
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }
    @Test
    //Extract the limit
    public void extractLimit() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is  : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }
    @Test
    //Extract the total
    public void extractTotal() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is  : " + total);
        System.out.println("------------------End of Test---------------------------");
    }
    @Test
    //Extract the name of 5th store
    public void extractNameOfFifthStore() {
        String fifthProductName = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of the fifth product is  : " + fifthProductName);
        System.out.println("------------------End of Test---------------------------");
    }
    @Test
    public void extractNameOfAllStore() {
        List<String> listOfProductName = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("list of the product name is :" + listOfProductName);
        System.out.println("------------------End of Test---------------------------");
    }
    @Test
    public void extractIdOfAllProduct() {
        List<String> listOfProductId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("list of the store id is :" + listOfProductId);
        System.out.println("------------------End of Test---------------------------");
    }
    @Test
    public void printSize() {
        List<String> data = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data is : " + data.size());
        System.out.println("------------------End of Test---------------------------");
    }
    //Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void extractNameProduct() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All values of store Name Energizer - MAX Batteries AA (4-Pack): " + values);
        System.out.println("------------------End of Test---------------------------");
    }
    @Test
    //Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    public void extractGetAllAddressOfStoreByName() {
        List<String> model = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Address of store Name Energizer - N Cell E90 Batteries (2-Pack): " + model);
        System.out.println("------------------End of Test---------------------------");
    }
    @Test
    // Get all the categories of 8th products
    public void extractGetAllServicesOfEighthStore() {
        List<Map<String, ?>> categories = response.extract().path("data[7].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("categories of 8th product: " + categories);
        System.out.println("------------------End of Test---------------------------");
    }
    //Get categories of the store where product id = 150115
    @Test
    public void extractGetStoreServicesByName() {
        List<Map<String, ?>> categories1 = response.extract().path("data.findAll{it.id == 150115}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("categories of product where id is = 150115 : " + categories1);
        System.out.println("------------------End of Test---------------------------");
    }
    @Test
    //Get all the descriptions of all the products
    public void extractGetAllDescription() {

        List<String> disOfAllProduct = response.extract().path("data.description");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The description of all product is : " + disOfAllProduct);
        System.out.println("------------------End of Test---------------------------");
    }
    @Test
    //Get id of all the all categories of all the products
    public void extractGetAllProductId() {

        List<String> disOfAllProduct = response.extract().path("data.categories*.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The id of all the all categories of all the products : " + disOfAllProduct);
        System.out.println("------------------End of Test---------------------------");
    }
    //Find the product names Where type = HardGood
    @Test
    public void extractFindProductNameByType() {
        List<String> productNames = response.extract().path("data.findAll{it.type == 'HardGood'}.name");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("Product Name is Type Of HardGood : " + productNames);
        System.out.println("------------------End of Test---------------------------");
    }

    // Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void extractSizeOfCategoriesByProductName() {
        List<String> categories = response.extract().path("data.find{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("Print Total Number Of Categories For Product Store 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)': " + categories.size());
        System.out.println("------------------End of Test---------------------------");
    }


    // Find the createdAt for all products whose price < 5.49
    @Test
    public void extractFindCreatedAtFromAllProductByPriceFilter() {
        List<String> createdAt = response.extract().path("data.findAll{it.price < 5.49}.createdAt");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The Created At For All Products are: " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    // Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void extractFindNameOfAllCategoriesByProductName() {
        List<Map<String, ?>> menuList = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The categories For product name Energizer - MAX Batteries AA (4-Pack) : " + menuList);
        System.out.println("------------------End of Test---------------------------");
    }

    // Find the manufacturer of all the products
    @Test
    public void extractFindManufacturerOfAllProducts() {
        List<String> manufacturer = response.extract().path("data.manufacturer");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("Name of all manufacturer : " + manufacturer);
        System.out.println("------------------End of Test---------------------------");
    }

    // Find the image of products whose manufacturer is = Energizer
    @Test
    public void extractFindImageOfProductByManufacturer() {
        List<String> image = response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The image of all the products whose manufacturer is Energizer: " + image);
        System.out.println("------------------End of Test---------------------------");
    }

    // Find the createdAt for all categories products whose price > 5.99
    @Test
    public void extractFindCreatedAtForAllCategoriesByPriceFilter() {
        List<String> createdAt = response.extract().path("data.findAll{it.price < 5.99}.createdAt");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The Created At For All Products are: " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the url of all the products
    @Test
    public void extractUrlOfAllProduct() {
        List<String> Url = response.extract().path("data.url");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("url of all the products are " + Url);
        System.out.println("------------------End of Test---------------------------");

    }
}
