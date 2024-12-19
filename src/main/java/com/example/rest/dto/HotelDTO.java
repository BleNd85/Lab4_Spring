package com.example.rest.dto;

import com.example.rest.dto.validation.ValidHotelType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class HotelDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @Null
    private Long id;

    @Schema(description = "Name of the hotel")
    @NotBlank(message = "Name is mandatory")
    @Size(max = 45, message = "Name can't be longer than 45 symbols")
    private String name;

    @Schema(description = "Address of the hotel")
    @NotBlank(message = "Address is mandatory")
    private String address;

    @Schema(description = "Type of the hotel")
    @NotBlank(message = "Category can't be empty")
    @ValidHotelType
    private String type;
}
