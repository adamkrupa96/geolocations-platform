package com.platform.geolocations.service;

import com.platform.geolocations.domain.dto.LocationDto;
import com.platform.geolocations.domain.dto.NearestLocation;
import com.platform.geolocations.domain.dto.PositionDto;
import com.platform.geolocations.domain.model.GeoLocation;
import com.platform.geolocations.exception.ApiException;
import com.platform.geolocations.exception.LocationsException;
import com.platform.geolocations.repository.GeoLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeoLocationService {

    private GeoLocationRepository geoLocationRepository;

    public List<GeoLocation> saveLocations(List<LocationDto> locations) {
        if (locations != null && !locations.isEmpty()) {
            List<GeoLocation> geoLocations = locations.stream()
                .map(GeoLocation::from)
                .collect(Collectors.toList());

            return geoLocationRepository.saveAll(geoLocations);
        } else {
            throw new LocationsException("List of locations can not be empty or null");
        }
    }

    public NearestLocation getNearestLocationName(PositionDto positionDto) {
        return geoLocationRepository
                .findNearestLocationName(positionDto.getLatitude(), positionDto.getLongitude())
                .orElseThrow(
                    () -> new ApiException("Did not provide list of locations to compare.")
                );
    }

    @Autowired
    public void setGeoLocationRepository(GeoLocationRepository geoLocationRepository) {
        this.geoLocationRepository = geoLocationRepository;
    }
}
