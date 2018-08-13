package com.hotelreservation.service;

import com.hotelreservation.model.Hotel;
import com.hotelreservation.repository.HotelRepository;
import com.hotelreservation.service.exception.HotelNotFoundException;
import com.hotelreservation.service.exception.UniquenessCnpjException;
import com.hotelreservation.service.impl.HotelServiceImpl;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ListAssert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class HotelServiceTest {

    private static final String TITLE = "Bela Vista Porto Alegre";
    private static final String CNPJ = "01745475/000170";

    @MockBean
    private HotelRepository hotelRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private HotelService hotelService;

    private Hotel hotel;

    private List<Hotel> hotelList = Arrays.asList();

    @Before
    public void setUp() throws Exception {
        hotelService = new HotelServiceImpl(hotelRepository);

        hotel = new Hotel();
        hotel.setCode(3L);
        hotel.setTitle(TITLE);
        hotel.setCnpj(CNPJ);

        hotelList = Arrays.asList(hotel);

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
    public void shouldReturnHotelDataInNotFoundException() throws Exception {
        expectedException.expect(HotelNotFoundException.class);
        expectedException.expectMessage("Não existe hotel para o CNPJ " + CNPJ);

        hotelService.findByCnpj(CNPJ);
    }

    @Test
    public void shouldLookHotelByCnpj() throws Exception {
        when(hotelRepository.findByCnpj(CNPJ)).thenReturn(Optional.of(hotel));

        Hotel hotelTest = hotelService.findByCnpj(CNPJ);

        verify(hotelRepository).findByCnpj(CNPJ);

        Assertions.assertThat(hotelTest).isNotNull();
    }

    @Test
    public void shoudReturnListOfTheAllHotels() throws Exception {
        when(hotelRepository.findAll()).thenReturn(hotelList);

        List<Hotel> hotelList = hotelService.getAllHotels();

        Assertions.assertThat(hotelList).isNotNull();
        Assertions.assertThat(hotelList.size()).isNotEqualTo(0);

    }

    @Test(expected = HotelNotFoundException.class)
    public void shouldReturnHotelNotFoundExeceptionWhenListIsEmpty() throws Exception {
        when(hotelRepository.findAll()).thenReturn(Arrays.asList());

        List<Hotel> hotelList = hotelService.getAllHotels();

        Assertions.assertThat(hotelList).isNotNull();
        Assertions.assertThat(hotelList.size()).isEqualTo(0);
    }

    @Test
    public void shouldDeleteHotel() throws Exception {
        hotelService.delete(hotel.getCode());

        verify(hotelRepository).deleteById(hotel.getCode());
    }
}
