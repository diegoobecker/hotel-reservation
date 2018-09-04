package com.hotelreservation.repository;

import com.hotelreservation.model.Hotel;
import com.hotelreservation.repository.helper.HotelRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Long>, HotelRepositoryQuery {

    Optional<Hotel> findByCnpj(String cnpj);
}
