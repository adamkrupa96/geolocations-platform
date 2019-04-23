package com.platform.geolocations.controller;

import com.platform.geolocations.domain.dto.LocationsDto;
import com.platform.geolocations.domain.dto.NearestLocation;
import com.platform.geolocations.domain.dto.PositionDto;
import com.platform.geolocations.domain.model.GeoLocation;
import com.platform.geolocations.service.GeoLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/locations")
@Validated
public class GeoLocationController {

    @Autowired
    private GeoLocationService geoLocationService;

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public List<GeoLocation> addLocationsList(@RequestBody @Valid LocationsDto locationsDto) {
        return geoLocationService.saveLocations(locationsDto.getLocations());
    }

    @GetMapping(path = "/search/nearestPoint", params = {"latitude", "longitude"})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public NearestLocation getNearestLocation(
            @Min(-90) @Max(90) @RequestParam(name = "latitude") Double latitude,
            @Min(-180) @Max(180) @RequestParam(name = "longitude") Double longitude) {

        PositionDto position = new PositionDto(latitude, longitude);
        return geoLocationService.getNearestLocationName(position);
    }
}
