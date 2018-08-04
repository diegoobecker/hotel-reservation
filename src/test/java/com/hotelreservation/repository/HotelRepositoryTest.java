package com.hotelreservation.repository;

import com.hotelreservation.model.Hotel;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@Sql(value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class HotelRepositoryTest {

    @Autowired
    HotelRepository hotelRepository;

    @Test
    public void shouldLookForHotelByCnpj() throws Exception {
        Optional<Hotel> optional = hotelRepository.findByCnpj("30965242000180");

        Assertions.assertThat(optional.isPresent()).isTrue();
    }

    @Test
    public void shouldNotLookForHotelByNonexistentCnpj() throws Exception {
        Optional<Hotel> optional = hotelRepository.findByCnpj("23432452000132");

        Assertions.assertThat(optional.isPresent()).isFalse();
    }
}
