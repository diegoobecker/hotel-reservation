package com.hotelreservation.service.impl;

import com.hotelreservation.model.Hotel;
import com.hotelreservation.repository.HotelRepository;
import com.hotelreservation.service.HotelService;
import com.hotelreservation.service.exception.HotelNotFoundException;
import com.hotelreservation.service.exception.UniquenessCnpjException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Hotel save(Hotel hotel) throws UniquenessCnpjException {

        Optional<Hotel> optional = hotelRepository.findByCnpj(hotel.getCnpj());

        if(optional.isPresent()) {
            throw new UniquenessCnpjException("Já existe hotel cadastrado com o CNPJ informado");
        }

        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel findByCnpj(String cnpj) throws HotelNotFoundException {
        Optional<Hotel> optional = hotelRepository.findByCnpj(cnpj);
        return optional.orElseThrow(() -> new HotelNotFoundException("Não existe hotel para o CNPJ " + cnpj));
    }

    @Override
    public List<Hotel> getAllHotels() throws HotelNotFoundException {
        if(hotelRepository.findAll().isEmpty()) {
            throw new HotelNotFoundException("Não existem hoteis para listar");
        }
        return hotelRepository.findAll();
    }

    @Override
    public void delete(Long code) throws HotelNotFoundException {
        if(!hotelRepository.findById(code).isPresent()) {
            throw new HotelNotFoundException("Hotel não encontrado");
        }

        hotelRepository.deleteById(code);
    }
}
