package com.example.rest.domain;

import com.example.rest.common.HotelType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hotel {
    private Long id;
    private String name;
    private String address;
    private HotelType type;
}
