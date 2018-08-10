package com.hotelreservation.resources;

import com.hotelreservation.HotelReservationApplicationTests;
import com.hotelreservation.model.Hotel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.mockito.hamcrest.MockitoHamcrest;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class HotelResourceTest extends HotelReservationApplicationTests {

    @Test
    public void shouldFindHotelByCnpj() throws Exception {
        given()
                .pathParam("cnpj", "30965242000180")
        .get("/hotels/{cnpj}")
        .then()
                .log().body().and()
                .statusCode(HttpStatus.OK.value())
                .body("title", equalTo("Verde Bela"),
                        "address", equalTo("Curitiba"));
    }

    @Test
    public void shouldNotFoundErrorWhenFindHotelByNonexistentCnpj() throws Exception {
        given()
                .pathParam("cnpj", "23432452000132")
        .get("/hotels/{cnpj}")
        .then()
                .log().body().and()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("error",equalTo("Não existe hotel para o CNPJ 23432452000132"));
    }

    @Test
    public void shouldFindAllHotels() throws Exception {
        given()
        .get("/hotels")
        .then()
                .log().body().and()
                .statusCode(HttpStatus.OK.value())
                .body("title[0]", equalTo("Bela Vista"),
                        "address[2]", equalTo("Curitiba"),
                        "cnpj[3]", equalTo("61586557000140"));
    }


    @Test
    public void shoudSaveNewHotel() throws Exception {
        Hotel hotel = new Hotel();
        hotel.setTitle("Santa Isabel");
        hotel.setCnpj("07628471000198");
        hotel.setAddress("Viamão");

        given()
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-type", ContentType.JSON)
                .body(hotel)
        .when()
        .post("/hotels")
        .then()
                .log().headers().and()
                .log().body().and()
                .statusCode(HttpStatus.CREATED.value())
                .body("title", equalTo("Santa Isabel"),
                        "cnpj", equalTo("07628471000198"),
                        "address", equalTo("Viamão"));

    }

    @Test
    public void shouldUniquenessCnpjExceptionWhenSaveHotelWithCpnjExisting() throws Exception {
        Hotel hotel = new Hotel();
        hotel.setTitle("Santa Isabel");
        hotel.setCnpj("30965242000180");
        hotel.setAddress("Viamão");

        given()
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-type", ContentType.JSON)
                .body(hotel)
        .when()
        .post("/hotels")
        .then()
                .log().headers().and()
                .log().body().and()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("error", equalTo("Já existe hotel cadastrado com o CNPJ informado"));
    }
}
