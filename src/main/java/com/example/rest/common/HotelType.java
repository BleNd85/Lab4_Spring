package com.example.rest.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HotelType {
    BUDGET("Бюджетний готель"),
    BUSINESS("Бізнес-готель"),
    RESORT("Курортний готель"),
    BOUTIQUE("Бутик-готель"),
    LUXURY("Готель класу люкс"),
    FAMILY("Сімейний готель"),
    ROMANTIC("Романтичний готель"),
    ECO("Еко-готель"),
    HOSTEL("Хостел"),
    MOTEL("Мотель");

    private final String stringValue;
}
