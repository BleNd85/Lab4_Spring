package com.example.rest.repository;

import com.example.rest.common.HotelType;
import com.example.rest.domain.Hotel;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryUseRepository implements HotelRepository {
    TreeMap<Long, Hotel> hotels = new TreeMap<>();

    @Override
    public Optional<Hotel> findById(Long id) {
        return Optional.ofNullable(hotels.get(id));
    }

    @Override
    public void save(Hotel hotel) {
        if (hotel.getId() == null) {
            Long id = hotels.lastKey() + 1;
            hotel = new Hotel(id, hotel.getName(), hotel.getAddress(), hotel.getType());
        }
        hotels.put(hotel.getId(), hotel);
    }

    @Override
    public List<Hotel> findAll() {
        return new ArrayList<>(hotels.values());
    }

    @Override
    public void deleteById(Long id) {
        hotels.remove(id);
    }

    public InMemoryUseRepository() {
        hotels.put(1L, new Hotel(1L, "Hotel 1", "Kyiv", HotelType.HOSTEL));
        hotels.put(2L, new Hotel(2L, "Hotel 2", "Prague", HotelType.BUSINESS));
        hotels.put(3L, new Hotel(3L, "Hotel 3", "Vienna", HotelType.BOUTIQUE));
    }
}
