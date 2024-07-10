package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    //1. Extract the limit
    @Test
    public void extractLimit() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");

    }

    //2. Extract the total
    @Test
    public void extractTotal() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th store
    @Test
    public void extractStore() {
        String storeName = response.extract().path("data[4].name");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The name of 5th store is : " + storeName);
        System.out.println("------------------End of Test---------------------------");

    }

    //4. Extract the names of all the store
    @Test
    public void nameOfStore() {
        List<String> nameOfAllStore = response.extract().path("data.name");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("names of all the store are : " + nameOfAllStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the storeId of all the store
    @Test
    public void allStorestoreId() {
        List<Integer> allStorestoresId = response.extract().path("data.id");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("storeId of all the store : " + allStorestoresId);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the size of the data list
    @Test
    public void sizeOfList() {
        List<Integer> sizeOfDataList = response.extract().path("data");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("Size of the data list is : " + sizeOfDataList.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void valueStore() {
        List<?> ValueOfStore = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("All the value of St Cloud store is : " + ValueOfStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void address() {
        List<?> storeAddress = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("Address of the store Rochester is : " + storeAddress);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the services of 8th store
    @Test
    public void services() {
        List<?> sericesOfStore = response.extract().path("data[7].services");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("All the services of 8th store : " + sericesOfStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void storeServices() {
        List<Map<String, ?>> storeServices = response.extract().path("data[9].storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Store services of service name windows store: " + storeServices);
        System.out.println("------------------End of Test---------------------------");

    }

    //11. Get all the storeId of all the store
    @Test
    public void getStorId() {
        List<Integer> storeIdAllStore = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("storeId of all the store is : " + storeIdAllStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //12. Get id of all the store
    @Test
    public void extractGetAllStoreIdFromService() {
        List<Integer> idOfAllStore = response.extract().path("data.services.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all store is : " + idOfAllStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //13. Find the store names Where state = ND
    @Test
    public void extractStoreNameFromStateND() {
        List<String> storeNamesList = response.extract().path("data.findAll{it.state == 'MN'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all store Where state = MN is : " + storeNamesList);
        System.out.println("------------------End of Test---------------------------");
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void extractTotalNoOfServicesFromStore() {
        List<String> services = response.extract().path("data.findAll{it.name == 'Rochester'}.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total number of services: " + services.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void extractFindCreatedAtForAllServices() {
        List<String> createServices = response.extract().path("data.services*.findAll{it.name == 'Windows Store'}.storeservices.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total number of services: " + createServices);
        System.out.println("------------------End of Test---------------------------");
    }

    // 16. Find the name of all services Where store name = “Fargo”
    @Test
    public void storeServiceName() {
        List<String> StorServices = response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total number of services: " + StorServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //17. Find the zip of all the store
    @Test
    public void storeZip() {
        List<Integer> storeZipCode = response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total number of services: " + storeZipCode);
        System.out.println("------------------End of Test---------------------------");
    }

    //18. Find the zip of store name = Roseville
    public void extractFindZipFromStoreName() {
        List<String> zip = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Zip of Roseville store is : " + zip.get(0));
        System.out.println("------------------End of Test---------------------------");
    }

    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void extractFindServicesDetailsFromServiceName() {
        List<String> data = response.extract().path("data.services*.find{it.name == 'Magnolia Home Theater'}.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store Services Details Of The Service Name = MagnoliaHomeTheater: " + data);
        System.out.println("------------------End of Test---------------------------");
    }

    //20. Find the lat of all the stores

    @Test
    public void extractFindLatOfAllStores() {
        List<Double> lat = response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("lat of all stores are " + lat);
        System.out.println("------------------End of Test---------------------------");

    }
}
