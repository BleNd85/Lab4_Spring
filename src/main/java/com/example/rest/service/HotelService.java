package com.example.rest.service;

import com.example.rest.dto.HotelDTO;

import java.util.List;

public interface HotelService {
    List<HotelDTO> getAllHotels(String name, String type, int page, int size);

    HotelDTO getHotelById(Long id);

    void createHotel(HotelDTO hotelDTO);

    void updateHotel(Long id, HotelDTO hotelDTO);

    void deleteHotel(Long id);
}
