package com.example.rest.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HotelTypeValidator.class)
public @interface ValidHotelType {
    String message() default "Invalid Hotel Category, it must be: Бюджетний готель, Бізнес-готель, Курортний готель," +
            "Бутик-готель, Готель класу люкс, Сімейний готель, Романтичний готель, Еко-готель, Хостел, Мотель.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
