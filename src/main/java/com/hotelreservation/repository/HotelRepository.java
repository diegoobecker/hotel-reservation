package com.hotelreservation.repository;

import com.hotelreservation.model.Hotel;

import java.util.Optional;

public interface HotelRepository {
    Hotel save(Hotel hotel);

    Optional<Hotel> findByCnpj(String cnpj);
}
