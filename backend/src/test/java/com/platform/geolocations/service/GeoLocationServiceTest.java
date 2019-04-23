package com.platform.geolocations.service;

import com.platform.geolocations.domain.dto.PositionDto;
import com.platform.geolocations.exception.LocationsException;
import com.platform.geolocations.exception.LocationsListNotProvidedException;
import com.platform.geolocations.repository.GeoLocationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class GeoLocationServiceTest {

    @Mock
    private GeoLocationRepository geoLocationRepository;

    @InjectMocks
    private GeoLocationService geoLocationService;

    @Test(expected = LocationsException.class)
    public void should_not_save_locations_list_is_null() {

        geoLocationService.saveLocations(null);
    }

    @Test(expected = LocationsException.class)
    public void should_not_save_locations_list_is_empty() {

        geoLocationService.saveLocations(Arrays.asList());
    }

    @Test(expected = LocationsListNotProvidedException.class)
    public void should_not_found_nearest_location_provided_list_is_empty() {
        Mockito.when(geoLocationRepository.findNearestLocationName(Mockito.anyDouble(), Mockito.anyDouble()))
                .thenReturn(Optional.empty());

        PositionDto testPosition = new PositionDto(12.123456, 34.456789);

        geoLocationService.getNearestLocationName(testPosition);
    }
}
