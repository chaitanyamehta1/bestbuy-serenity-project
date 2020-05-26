package com.bestbuy.testsuite;

import com.bestbuy.constants.EndPoint;
import com.bestbuy.pojo.StoresPojo;
import com.bestbuy.steps.StoresSteps;
import com.bestbuy.testbase.TestBase;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.*;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Created by Chaitanya
 */
@RunWith(SerenityRunner.class)
public class StoresTestWithTags extends TestBase {

    static String name = "Testing Store";
    static String type = "Testing Tools";
    static String address = "268 Alan Lodge";
    static String address2 = "Nether Street";
    static String city = "London";
    static String state = "North";
    static String zip = "525255";
    static double lat = 45.958785;
    static double lng = -90.445596;
    static String hours = "Mon: 9-6; Tue: 9-6; Wed: 9-6; Thurs: 9-6; Fri: 9-6; Sat: 9-6; Sun: 9-6";

    @Steps
    StoresSteps storesSteps;

    @WithTags({
            @WithTag("Features:POSITIVE"),
            @WithTag("Features:REGRESSION")
    })
    @Title("Creating a new Store")
    @Test
    public void test001() {
        storesSteps.createNewStore(name, type, address, address2, city, state, zip, lat, lng, hours);

    }

    @WithTags({
            @WithTag("Features:SANITY"),
            @WithTag("Features:REGRESSION")
    })

    @Title("Getting a created Store")
    @Test
    public void test002() {
        storesSteps.getStoreById().statusCode(200);
    }

    @WithTags({
            @WithTag("Features:SMOKE"),
            @WithTag("Features:REGRESSION")
    })
    @Title("Getting the all store")
    @Test
    public void test003() {
        SerenityRest.rest()
                .given()
                .when()
                .get(EndPoint.GET_ALL_STORES)
                .then();
    }

    @WithTags({
            @WithTag("Features:POSITIVE"),
            @WithTag("Features:REGRESSION")
    })
    @Title("Updating Store information with  name:{0}, type:{1}. address:{2}, address2:{3}, city:{4}, state:{5}, zip:{6}, lat:{7}, lng:{8}, hours:{9}")
    @Test
    public void test004() {

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

        SerenityRest.rest().given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(storesPojo)
                .patch(EndPoint.UPDATE_STORE_BY_ID + "/" + 7)
                .then();
    }

    @WithTags({
            @WithTag("Features: NEGATIVE"),
            @WithTag("Features:REGRESSION")
    })
    @Title("Deleting the store with store ID")
    @Test
    public void test005() {
        SerenityRest.rest()
                .given()
                .when()
                .delete(EndPoint.DELETE_STORE_BY_ID + "/" + 7)
                .then();


    }

}
