package com.example.rest.service.exception;

public class HotelNotFoundException extends RuntimeException {
    private static final String HOTEL_NOT_FOUND_MESSAGE = "Hotel with id %s not found";

    public HotelNotFoundException(Long id) {
        super(String.format(HOTEL_NOT_FOUND_MESSAGE, id));
    }
}
