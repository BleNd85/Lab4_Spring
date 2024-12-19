package com.example.rest.repository;

import com.example.rest.domain.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelRepository {
    Optional<Hotel> findById(Long id);

    void save(Hotel hotel);

    List<Hotel> findAll();

    void deleteById(Long id);
}
