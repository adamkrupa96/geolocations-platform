package com.platform.geolocations.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.platform.geolocations.domain.dto.LocationDto;
import com.platform.geolocations.utils.WktPointSerializer;
import lombok.Data;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "locations")
@Data
public class GeoLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @NotNull
    @Column(columnDefinition = "GEOMETRY(POINT, 4326)")
    @JsonSerialize(using = WktPointSerializer.class)
    private Point position;

    @Transient
    @JsonIgnore
    private static GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

    public static GeoLocation from(LocationDto locationDto) {
        GeoLocation location = new GeoLocation();
        location.setName(Objects.requireNonNull(locationDto.getName()));

        Coordinate coordinate = new Coordinate(
                Objects.requireNonNull(locationDto.getLongitude()),
                Objects.requireNonNull(locationDto.getLatitude()));

        Point position = geometryFactory.createPoint(coordinate);
        location.setPosition(position);

        return location;
    }
}
