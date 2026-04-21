package com.logiops.logistics.model;


import org.locationtech.jts.geom.Point;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    @Column(columnDefinition = "GEOGRAPHY(POINT, 4326)")
    private Point location;

    public String getFullAddress() {
        return String.format("%s, %s, %s %s, %s",
                street, city, state, postalCode, country);
    }
}