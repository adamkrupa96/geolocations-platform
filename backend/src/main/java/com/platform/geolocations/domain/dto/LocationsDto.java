package com.platform.geolocations.domain.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class LocationsDto {

    @NotEmpty
    @Valid
    private List<LocationDto> locations;
}
