package com.logiops.logistics.model;

import com.logiops.logistics.model.modelenum.PackageScanAction;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "package_scan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageScan {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "package_id", nullable = false)
    private Package trackedPackage; // Renamed from 'package' (reserved keyword)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Driver who performed the scan

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PackageScanAction action;

    @Column(columnDefinition = "GEOGRAPHY(POINT, 4326)", nullable = false)
    private Point scanLocation;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime scannedAt;


//    public static Point getCurrentLocation(Package pkg, PackageScanRepository scanRepo) {
//        return scanRepo.findTopByTrackedPackageOrderByScannedAtDesc(pkg)
//                .map(PackageScan::getScanLocation)
//                .orElse(null);
//    }
}