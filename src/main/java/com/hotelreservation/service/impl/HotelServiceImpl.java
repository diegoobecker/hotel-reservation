package com.hotelreservation.service.impl;

import com.hotelreservation.model.Hotel;
import com.hotelreservation.repository.HotelRepository;
import com.hotelreservation.service.HotelService;
import com.hotelreservation.service.exception.UniquenessCnpjException;

import java.util.Optional;

public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Hotel save(Hotel hotel) throws UniquenessCnpjException {

        Optional<Hotel> optional = hotelRepository.findByCnpj(hotel.getCnpj());

        if(optional.isPresent()) {
            throw new UniquenessCnpjException();
        }

        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel findByCnpj(String cnpj) {
        return hotelRepository.findByCnpj(cnpj).get();
    }
}
