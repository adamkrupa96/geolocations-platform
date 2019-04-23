package com.platform.geolocations.domain.model;

import com.platform.geolocations.domain.dto.LocationDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class GeoLocationTest {

    @Test
    public void should_return_GeoLocation() {
        String expectedName = "TEST_NAME";
        Double expectedLatitude = 12.123456;
        Double expectedLongitude = 23.456789;

        LocationDto locationDto = new LocationDto();
        locationDto.setName(expectedName);
        locationDto.setLatitude(expectedLatitude);
        locationDto.setLongitude(expectedLongitude);

        GeoLocation geoLocation = GeoLocation.from(locationDto);

        Assert.assertEquals(expectedName, geoLocation.getName());
        Assert.assertEquals(expectedLatitude, geoLocation.getPosition().getY(), 0.0001);
        Assert.assertEquals(expectedLongitude, geoLocation.getPosition().getX(), 0.0001);
    }

    @Test(expected = NullPointerException.class)
    public void should_not_return_GeoLocation_null_pointer_on_name() {
        LocationDto locationDto = new LocationDto();
        locationDto.setLatitude(32.123);
        locationDto.setLongitude(12.345);

        GeoLocation.from(locationDto);
    }

    @Test(expected = NullPointerException.class)
    public void should_not_return_GeoLocation_null_pointer_on_latitude() {
        LocationDto locationDto = new LocationDto();
        locationDto.setName("test");
        locationDto.setLongitude(12.345);

        GeoLocation.from(locationDto);
    }

    @Test(expected = NullPointerException.class)
    public void should_not_return_GeoLocation_null_pointer_on_longitude() {
        LocationDto locationDto = new LocationDto();
        locationDto.setName("test");
        locationDto.setLatitude(12.345);

        GeoLocation.from(locationDto);
    }
}
