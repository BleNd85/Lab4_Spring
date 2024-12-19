package com.example.rest.service;

import com.example.rest.domain.Hotel;
import com.example.rest.dto.HotelDTO;
import com.example.rest.repository.HotelRepository;
import com.example.rest.service.exception.HotelNotFoundException;
import com.example.rest.service.mapper.HotelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImplementation implements HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    public HotelServiceImplementation(HotelRepository hotelRepository, HotelMapper hotelMapper) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
    }

    @Override
    public List<HotelDTO> getAllHotels(String name, String type, int page, int size) {
        List<Hotel> hotels = hotelRepository.findAll().stream()
                .filter(hotel -> (name == null || hotel.getName().contains(name)) &&
                        (type == null || hotelMapper.toTypeString(hotel.getType()).equalsIgnoreCase(type)))
                .skip((long) page * size)
                .limit(size)
                .toList();
        return hotelMapper.toHotelDTOList(hotels);
    }

    @Override
    public HotelDTO getHotelById(Long id) {
        return hotelMapper.toHotelDTO(hotelRepository.findById(id).orElseThrow(() -> new HotelNotFoundException(id)));
    }

    @Override
    public void createHotel(HotelDTO hotelDTO) {
        hotelRepository.save(hotelMapper.toHotel(hotelDTO));
    }

    @Override
    public void updateHotel(Long id, HotelDTO hotelDTO) {
        hotelRepository.findById(id).orElseThrow(() -> new HotelNotFoundException(id));
        Hotel hotel = hotelMapper.toHotel(hotelDTO);
        hotel.setId(id);
        hotelRepository.save(hotel);
    }

    @Override
    public void deleteHotel(Long id) {
        hotelRepository.findById(id).orElseThrow(() -> new HotelNotFoundException(id));
        hotelRepository.deleteById(id);
    }
}
