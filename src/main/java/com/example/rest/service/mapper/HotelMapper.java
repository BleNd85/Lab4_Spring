package com.example.rest.service.mapper;

import com.example.rest.common.HotelType;
import com.example.rest.domain.Hotel;
import com.example.rest.dto.HotelDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Stream;

@Mapper(componentModel = "spring")
public interface HotelMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "type", source = "type", qualifiedByName = "toTypeString")
    HotelDTO toHotelDTO(Hotel hotel);

    List<HotelDTO> toHotelDTOList(List<Hotel> hotels);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "type", source = "type", qualifiedByName = "toTypeType")
    Hotel toHotel(HotelDTO hotelDTO);

    @Named("toTypeString")
    default String toTypeString(HotelType hotelType) {
        return hotelType.getStringValue();
    }

    @Named("toTypeType")
    default HotelType stringToEnum(String typeString) {
        return Stream.of(HotelType.values())
                .filter(type -> type.getStringValue().equalsIgnoreCase(typeString))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(typeString + " is not a valid HotelType"));
    }
}
