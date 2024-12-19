package com.example.rest.web;

import com.example.rest.dto.HotelDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface HotelControllerInterface {
    @GetMapping
    @Operation(

            summary = "Get All",
            description = "Retrieve a paginated list of HotelDTO objects. You can filter by name and type, and specify pagination parameters.",
            parameters = {@Parameter(name = "name", description = "Filter by name of the hotel"),
                    @Parameter(name = "type", description = "Filter by type of the hotel."),
                    @Parameter(name = "page", description = "Page number for pagination."),
                    @Parameter(name = "size", description = "Number of items per page.")}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    ResponseEntity<List<HotelDTO>> getHotels(@RequestParam(required = false) String name, @RequestParam(required = false) String type,
                                             @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size);

    @Operation(
            summary = "Get Hotel By Id",
            description = "Retrieve a specific HotelDTO object by its unique identifier.",
            parameters = {@Parameter(name = "id", description = "Unique hotel identifier.")}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @GetMapping("/{id}")
    ResponseEntity<HotelDTO> getHotel(@PathVariable Long id);

    @Operation(
            summary = "Create New Hotel",
            description = "Create a new hotel by providing the necessary details."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "409", description = "Conflict")
    })

    @PostMapping
    ResponseEntity<Void> createHotel(@RequestBody @Valid HotelDTO hotelDTO);

    @Operation(
            summary = "Update Existing Hotel By Id",
            description = "Update an existing hotel record by its unique identifier."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Success. No Content"),
            @ApiResponse(responseCode = "409", description = "Conflict")
    })
    @PutMapping("/{id}")
    ResponseEntity<Void> updateHotel(@PathVariable Long id, @RequestBody @Valid HotelDTO hotelDTO);

    @Operation(
            summary = "Delete Existing Hotel By Id",
            description = "Delete an existing hotel record by its unique identifier."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteHotel(@PathVariable Long id);
}
