package com.bestbuy.steps;

import com.bestbuy.constants.EndPoint;
import com.bestbuy.pojo.StoresPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static io.restassured.RestAssured.given;

/**
 * Created by Chaitanya
 */
public class StoresSteps {

    static long storeId;


    @Step("Getting the all store created by id:{0}")
    public ValidatableResponse getAllStoreById() {
        return SerenityRest.rest()
                .given()
                .when()
                .get(EndPoint.GET_ALL_STORES + "/" + storeId)
                .then();
    }


    @Step("Creating a new store with name:{0}, type:{1}. address:{2}, address2:{3}, city:{4}, state:{4}, zip:{5}, lat:{6}, lng:{7}, hours:{8}")

    public void createNewStore(String name, String type, String address, String address2,
                               String city, String state, String zip, double lat, double lng,
                               String hours) {

        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);

        Response response = given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(storesPojo)
                .post(EndPoint.POST_A_STORE)
                .then()
                .log()
                .all()
                .statusCode(201)
                .extract()
                .response();
        storeId = response.body().jsonPath().getLong("id");


    }

    @Step("Getting the information of the store created by id:{0}")
    public ValidatableResponse getStoreById() {
        return SerenityRest.rest()
                .given()
                .when()
                .get(EndPoint.GET_SINGLE_STORE_BY_ID + "/" + storeId)
                .then().log().all();
    }

    @Step("Updating Store information with  name:{0}, type:{1}. address:{2}, address2:{3}, city:{4}, state:{5}, zip:{6}, lat:{7}, lng:{8}, hours:{9}")

    public ValidatableResponse updateStore(String name, String type, String address, String address2,
                                           String city, String state, String zip, double lat, double lng,
                                           String hours) {

        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);

        return SerenityRest.rest().given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(storesPojo)
                .patch(EndPoint.UPDATE_STORE_BY_ID + "/" + storeId)
                .then();
    }

    @Step("Deleting the store with store Id : {0} ")

    public ValidatableResponse deleteStore() {
        return SerenityRest.rest()
                .given()
                .when()
                .delete(EndPoint.DELETE_STORE_BY_ID + "/" + storeId)
                .then().log().all();


    }


}
