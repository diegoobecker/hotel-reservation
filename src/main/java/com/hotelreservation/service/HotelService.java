package com.hotelreservation.service;

import com.hotelreservation.model.Hotel;
import com.hotelreservation.service.exception.HotelNotFoundException;
import com.hotelreservation.service.exception.UniquenessCnpjException;

import java.util.List;

public interface HotelService {

    Hotel save(Hotel hotel) throws UniquenessCnpjException;

    Hotel findByCnpj(String cnpj) throws HotelNotFoundException;

    List<Hotel> getAllHotels() throws HotelNotFoundException;

    void delete(Long code) throws HotelNotFoundException;

    Hotel update(Hotel hotel);
}
