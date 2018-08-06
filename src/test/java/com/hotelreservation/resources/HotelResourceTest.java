package com.hotelreservation.resources;

import com.hotelreservation.HotelReservationApplicationTests;
import io.restassured.RestAssured;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.mockito.hamcrest.MockitoHamcrest;
import org.springframework.http.HttpStatus;

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
}
