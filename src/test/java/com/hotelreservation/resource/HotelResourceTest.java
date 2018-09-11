package com.hotelreservation.resource;

import com.hotelreservation.HotelReservationApplicationTests;
import com.hotelreservation.model.Hotel;
import com.hotelreservation.repository.filter.HotelFilter;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.containsInAnyOrder;

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

    @Test
    public void shouldDeleteHotel() throws Exception {
        given()
                .pathParam("code", 3L)
        .when()
        .delete("/hotels/{code}")
        .then()
                .log().headers().and()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturnNotFoundHotelWhenCodeIsNotExists() throws Exception {
        given()
                .pathParam("code", 7L)
        .when()
        .delete("/hotels/{code}")
        .then()
                .log().headers().and()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("error", equalTo("Hotel não encontrado"));
    }

    @Test
    public void shouldUpdateHotel() throws Exception {
        Hotel hotel = new Hotel();
        hotel.setCode(4l);
        hotel.setTitle("Bela Vista Alterado");
        hotel.setCnpj("61586557000140");

        given()
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-type", ContentType.JSON)
                .body(hotel)
        .when()
        .put("/hotels")
        .then()
                .log().headers().and()
                .log().body().and()
                .statusCode(HttpStatus.OK.value())
                .body("title", equalTo("Bela Vista Alterado"));
    }

    @Test
    public void shouldFilterHotelByTitle() throws Exception {
        HotelFilter filter = new HotelFilter();
        filter.setTitle("w");

        given()
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-type", ContentType.JSON)
                .body(filter)
        .when()
        .post("/hotels/filter")
        .then()
                .log().body()
                .and()
                .statusCode(HttpStatus.OK.value())
                .body("title", containsInAnyOrder("Bela Vista Tower"));


    }
}
