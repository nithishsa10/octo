package com.logiops.logistics.model;

import com.logiops.logistics.model.modelenum.TripStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.geo.Point;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "trip")
@Data
public class Trip {
    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "trip_status")
    private TripStatus status;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(
            name = "location",
            column = @Column(columnDefinition = "GEOGRAPHY(POINT, 4326)")
        )
    })
    private Address pickupLocation;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(
            name = "location",
            column = @Column(columnDefinition = "GEOGRAPHY(POINT, 4326)")
        )
    })
    private Point deliveryLocation;

    @CreationTimestamp
    private LocalDateTime createAt;

    @CreationTimestamp
    private LocalDateTime updateAt;

}
