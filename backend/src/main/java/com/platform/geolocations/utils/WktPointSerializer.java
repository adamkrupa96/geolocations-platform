package com.platform.geolocations.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.platform.geolocations.domain.dto.PositionDto;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Point;

import java.io.IOException;

public class WktPointSerializer extends JsonSerializer<Point> {

    @Override
    public void serialize(Point value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        PositionDto position = getPosition(value.getCoordinate());
        gen.writeObject(position);
    }

    private PositionDto getPosition(Coordinate coordinate) {
        return new PositionDto(coordinate.y, coordinate.x);
    }
}