package com.platform.geolocations.domain.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LocationDto {

    @NotBlank
    private String name;

    @NotNull
    @Min(-90) @Max(90)
    private Double latitude;

    @NotNull
    @Min(-180) @Max(180)
    private Double longitude;
}
