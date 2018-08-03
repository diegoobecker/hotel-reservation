package com.hotelreservation.service;

import com.hotelreservation.model.Hotel;
import com.hotelreservation.repository.HotelRepository;
import com.hotelreservation.service.exception.HotelNotFoundException;
import com.hotelreservation.service.exception.UniquenessCnpjException;
import com.hotelreservation.service.impl.HotelServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class HotelServiceTest {

    private static final String TITLE = "Bela Vista Porto Alegre";
    private static final String CNPJ = "01.745.475/0001-70";
    @MockBean
    private HotelRepository hotelRepository;

    private HotelService hotelService;

    private Hotel hotel;

    @Before
    public void setUp() throws Exception {
        hotelService = new HotelServiceImpl(hotelRepository);

        hotel = new Hotel();
        hotel.setTitle(TITLE);
        hotel.setCnpj(CNPJ);

        when(hotelRepository.findByCnpj(CNPJ)).thenReturn(Optional.empty());
    }

    @Test
    public void shouldSaveHotelInRepository() throws Exception {
        hotelService.save(hotel);

        verify(hotelRepository).save(hotel);
    }

    @Test(expected = UniquenessCnpjException.class)
    public void shouldNotSaveHotelWithTheSameCnpj() throws Exception {
        when(hotelRepository.findByCnpj(CNPJ)).thenReturn(Optional.of(hotel));

        hotelService.save(hotel);
    }

    @Test(expected = HotelNotFoundException.class)
    public void shouldReturnExceptionWhenNotExistsHotelByCnpj() throws Exception {
        hotelService.findByCnpj(CNPJ);
    }

    @Test
    public void shouldLookHotelByCnpj() throws Exception {
        when(hotelRepository.findByCnpj(CNPJ)).thenReturn(Optional.of(hotel));

        Hotel hotelTest = hotelService.findByCnpj(CNPJ);

        verify(hotelRepository).findByCnpj(CNPJ);

        Assertions.assertThat(hotelTest).isNotNull();
    }
}
