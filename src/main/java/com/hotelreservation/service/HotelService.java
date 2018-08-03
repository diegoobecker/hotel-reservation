package com.hotelreservation.service;

import com.hotelreservation.model.Hotel;
import com.hotelreservation.service.exception.UniquenessCnpjException;

public interface HotelService {

    Hotel save(Hotel hotel) throws UniquenessCnpjException;

    Hotel findByCnpj(String cnpj);
}
