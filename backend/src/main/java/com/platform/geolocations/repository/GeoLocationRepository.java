package com.platform.geolocations.repository;

import com.platform.geolocations.domain.dto.NearestLocation;
import com.platform.geolocations.domain.model.GeoLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeoLocationRepository extends JpaRepository<GeoLocation, Integer> {

    @Query(value = "SELECT name as locationName from locations " +
            "ORDER BY ST_Distance_Sphere(position, " +
            "   ST_GeomFromText(CONCAT('Point(', :latitude, ' ', :longitude, ')'), 4326, 'axis-order=lat-long')) ASC " +
            "LIMIT 1",
            nativeQuery = true)
    Optional<NearestLocation> findNearestLocationName(@Param("latitude") Double latitude, @Param("longitude") Double longitude);
}
