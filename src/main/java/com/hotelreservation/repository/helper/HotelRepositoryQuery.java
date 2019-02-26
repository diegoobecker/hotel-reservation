package com.hotelreservation.repository.helper;

import com.hotelreservation.model.Hotel;
import com.hotelreservation.repository.filter.HotelFilter;

import java.util.List;

public interface HotelRepositoryQuery {

    List<Hotel> filter(HotelFilter filter);
}
