package com.logiops.logistics.model;

import com.logiops.logistics.model.modelenum.PackageStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "package")
@Data
public class Package {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private UUID tripId;

    @Column
    private String description;

    @Column(nullable = false)
    private Double weight;

    private String dimensions;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(
            name = "location",
            column = @Column(columnDefinition = "GEOGRAPHY(POINT, 4326)")
        )
    })
    private Address destinationAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "package_status")
    private PackageStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @CreationTimestamp
    private LocalDateTime updatedAt;
}
