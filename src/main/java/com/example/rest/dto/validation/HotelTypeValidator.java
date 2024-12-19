package com.example.rest.dto.validation;

import com.example.rest.common.HotelType;
import com.example.rest.service.mapper.HotelMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class HotelTypeValidator implements ConstraintValidator<ValidHotelType, String> {
    private final HotelMapper hotelMapper;

    @Override
    public boolean isValid(String type, ConstraintValidatorContext context) {
        return Stream.of(HotelType.values())
                .anyMatch(hotelType -> hotelMapper.toTypeString(hotelType).equalsIgnoreCase(type));
    }
}
