package com.Testng.Asal;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertNotNull;

public class FirstTest {
    Response res;


    @BeforeClass
    public void setup() {

        res = given().baseUri("https://fakestoreapi.com")
                .when().get("products").then().extract().response();
    }

    @Test
    public void testStatusCode() {
        assertNotNull(res);
        res.then().assertThat().statusCode(200);
    }
    @Test
    public void testFirstTitle(){
        res.then().assertThat().body("[0].title",is(equalTo("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops")));
    }
    @Test
    public void verifyCategoryHasItem(){
        res.then().assertThat().body("category",hasItem("men's clothing"));
    }
    @Test
    public void verifyCategoryNotHasItem(){
        res.then().assertThat().body("category",not(hasItem("men' clothing")));
    }
   @Test
    public void verifyCategorySize(){
        res.then().assertThat().body("category",hasSize(20));
    }

    @Test
    public void verifyCategorySizeUsingEqual(){
        res.then().assertThat().body("category.size()",equalTo(20));
    }

    @Test
    public void verifyTheFirstRatingCount(){
        res.then(). assertThat().body("rating[1].count",equalTo(259));
    }

    @Test
    public void verifyHasKey(){
        res.then().assertThat().body("[0]",hasKey("id"));
    }
    @Test
    public void verifyHasItem(){
        res.then().assertThat().body("[0]",hasValue(1));
    }
    @Test
    public void verifyHasEntry(){
        res.then().assertThat().body("[0]",hasEntry("id",1));

    }

    /*
    @Test


   public void test(){
         given().baseUri("https://fakestoreapi.com")
         .when().get("products")
        .then().log().all()
        .assertThat().statusCode(200)
                 .assertThat().body("[0].title",is(equalTo("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops")))
                 .assertThat().body("category",hasItem("men's clothing"))
                 .assertThat().body("category",not(hasItem("men' clothing")))
                 .assertThat().body("category",hasSize(20))
                 .assertThat().body("category.size()",equalTo(20))
                 . assertThat().body("rating[1].count",equalTo(259))
                 .assertThat().body("[0]",hasKey("id"))
                 .assertThat().body("[0]",hasValue(1))
                 .assertThat().body("[0]",hasEntry("id",1));

     }*/
    @Test
    public void printDataResponse(){

        System.out.println(res.asString());
    }
    @Test
    public void printFirstTitle(){
        String title=res.path("[0].title");
        System.out.println("the tile is : "+title);
    }

}
