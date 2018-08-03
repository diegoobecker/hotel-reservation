package com.hotelreservation.repository;

import com.hotelreservation.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Optional<Hotel> findByCnpj(String cnpj);
}
