package com.platform.geolocations.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class PositionDto {

    @NotNull
    @Min(-90) @Max(90)
    private Double latitude;

    @NotNull
    @Min(-180) @Max(180)
    private Double longitude;
}