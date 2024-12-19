package com.example.rest.web;

import com.example.rest.dto.HotelDTO;
import com.example.rest.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/hotels")
public class HotelController implements HotelControllerInterface {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    @Override
    public ResponseEntity<List<HotelDTO>> getHotels(@RequestParam(required = false) String name, @RequestParam(required = false) String type,
                                                    @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(hotelService.getAllHotels(name, type, page, size));
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<HotelDTO> getHotel(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    @PostMapping
    @Override
    public ResponseEntity<Void> createHotel(@RequestBody @Valid HotelDTO hotelDTO) {
        hotelService.createHotel(hotelDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Void> updateHotel(@PathVariable Long id, @RequestBody @Valid HotelDTO hotelDTO) {
        hotelService.updateHotel(id, hotelDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.ok().build();
    }
}
