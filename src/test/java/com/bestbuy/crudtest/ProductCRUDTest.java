package com.bestbuy.crudtest;


import com.bestbuy.steps.ProductsSteps;
import com.bestbuy.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Chaitanya
 */
@RunWith(SerenityRunner.class)
public class ProductCRUDTest extends TestBase {

    static String name = "Nokia New Mobile";
    static String type = "Nokia";
    static String upc = "87545464785";
    static double price = 20.99;
    static String description = "This is the latest high-tech design in mobile phones";
    static String model = "NKA13243UK";
    static long id;

    @Steps
    ProductsSteps productsSteps;

    @Title("This test is creating a new products and verify it is created")
    @Test
    public void test001() {
        productsSteps.createNewProduct(name, type, upc, (int) price, description, model);

    }

    @Title("This test is getting a created product by ID")
    @Test
    public void test002() {
        productsSteps.getProductById().statusCode(200);

    }

    @Title("This test will update the product information and verify the updated information")
    @Test
    public void test003() {
        name = name + "_Changed";
        price = 89.99;
        upc = upc + "_added";
        productsSteps.updateProduct(name, type, upc, (int) price, description, model).statusCode(200);
        productsSteps.getProductById().body("name", equalTo(name));

    }

    @Title("This test will delete the product information and verify the product is deleted ")
    @Test
    public void test004() {
        productsSteps.deleteProduct().statusCode(200);
        productsSteps.getProductById().statusCode(404);
    }
}