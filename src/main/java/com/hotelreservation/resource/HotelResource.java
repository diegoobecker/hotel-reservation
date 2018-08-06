package com.hotelreservation.resource;

import com.hotelreservation.model.Hotel;
import com.hotelreservation.service.HotelService;
import com.hotelreservation.service.exception.HotelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotels")
public class HotelResource {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/{cnpj}")
    public ResponseEntity<Hotel> getByCnpj(@PathVariable("cnpj") String cnpj) throws HotelNotFoundException {

        Hotel hotel = hotelService.findByCnpj(cnpj);

        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<Error> handleHotelNotFoundException(HotelNotFoundException e) {
        return new ResponseEntity<>(new Error(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    class Error {
        private String error;

        public Error(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }
}
