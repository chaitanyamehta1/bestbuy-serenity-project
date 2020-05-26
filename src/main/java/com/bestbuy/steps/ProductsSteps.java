package com.bestbuy.steps;

import com.bestbuy.constants.EndPoint;
import com.bestbuy.pojo.ProductsPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static io.restassured.RestAssured.given;


/**
 * Created by Chaitanya
 */
public class ProductsSteps {

    static long productId;

    @Step("Getting the information of the all the products by id:{0}")
    public ValidatableResponse getAllProductsById() {
        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(EndPoint.GET_ALL_PRODUCTS + "/" + productId)
                .then();
    }


    @Step("Creating a new product with name:{0}, type:{1}, upc:{2}, price:{3}, description{4}, model:{5}")
    public void createNewProduct(String name, String type, String upc,
                                 int price, String description, String model) {


        ProductsPojo productsPojo = new ProductsPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setUpc(upc);
        productsPojo.setPrice(price);
        productsPojo.setDescription(description);
        productsPojo.setModel(model);

        Response response = given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(productsPojo)
                .post(EndPoint.POST_A_PRODUCT)
                .then()
                .log().all()
                .statusCode(201)
                .extract().response();
        productId = response.body().jsonPath().getLong("id");

    }

    @Step("Getting the information of the product created by id:{0}")
    public ValidatableResponse getProductById() {
        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(EndPoint.GET_SINGLE_PRODUCT_BY_ID + "/" + productId)
                .then().log().all();


    }

    @Step("Updating Product information with  name : {1} , type : {2} , upc : {3} , price : {4} , description : {5} , model : {6} ")

    public ValidatableResponse updateProduct(String name, String type, String upc, int price, String description, String model) {

        ProductsPojo productsPojo = new ProductsPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setUpc(upc);
        productsPojo.setPrice(price);
        productsPojo.setDescription(description);
        productsPojo.setModel(model);

        return SerenityRest.rest().given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(productsPojo)
                .patch(EndPoint.UPDATE_PRODUCT_BY_ID + "/" + productId)
                .then().log().all();


    }

    @Step("Deleting the Product information with productId : {0} ")

    public ValidatableResponse deleteProduct() {
        return SerenityRest.rest()
                .given()
                .when()
                .delete(EndPoint.DELETE_PRODUCT_BY_ID + "/" + productId)
                .then().log().all();


    }
}
