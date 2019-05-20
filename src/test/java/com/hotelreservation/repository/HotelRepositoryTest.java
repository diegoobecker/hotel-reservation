package com.hotelreservation.repository;

import com.hotelreservation.model.Hotel;
import com.hotelreservation.repository.filter.HotelFilter;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

//@Sql(value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(value = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//@RunWith(SpringRunner.class)
//@DataJpaTest
//@TestPropertySource("classpath:application-test.properties")
public class HotelRepositoryTest {

    private static final String CNPJ_VALIDO = "30965242000180";
    private static final String CNPJ_INVALIDO = "23432452000132";

    @Autowired
    HotelRepository hotelRepository;

//    @Test
    public void shouldLookForHotelByCnpj() throws Exception {
        Optional<Hotel> optional = hotelRepository.findByCnpj(CNPJ_VALIDO);

        Assertions.assertThat(optional.isPresent()).isTrue();
    }

//    @Test
    public void shouldNotLookForHotelByNonexistentCnpj() throws Exception {
        Optional<Hotel> optional = hotelRepository.findByCnpj(CNPJ_INVALIDO);

        Assertions.assertThat(optional.isPresent()).isFalse();
    }

//    @Test
    public void shouldFilterHotelByTitle() throws Exception {
        HotelFilter filter = new HotelFilter();
        filter.setTitle("w");

        List<Hotel> hotels = hotelRepository.filter(filter);

        Assertions.assertThat(hotels.size()).isEqualTo(1);
    }

//    @Test
    public void shouldFilterHotelByCnpj() throws Exception {
        HotelFilter filter = new HotelFilter();
        filter.setCnpj("14");

        List<Hotel> hotels = hotelRepository.filter(filter);

        Assertions.assertThat(hotels.size()).isEqualTo(2);
    }

//    @Test
    public void shouldFilterHotelByCnpjAndTitle() throws Exception {
        HotelFilter filter = new HotelFilter();
        filter.setTitle("w");
        filter.setCnpj("14");

        List<Hotel> hotels = hotelRepository.filter(filter);

        Assertions.assertThat(hotels.size()).isEqualTo(1);
    }

//    @Test
    public void shouldFilterHotelByRating() throws Exception {
        HotelFilter filter = new HotelFilter();
        filter.setRating(3);

        List<Hotel> hotels = hotelRepository.filter(filter);

        Assertions.assertThat(hotels.size()).isEqualTo(2);
    }
}
