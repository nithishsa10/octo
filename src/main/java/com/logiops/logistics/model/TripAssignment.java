package com.logiops.logistics.model;

import com.logiops.logistics.model.modelenum.TripAssignmentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "trip_assignment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripAssignment {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Assigned driver

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TripAssignmentStatus status;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime assignedAt;

    @Column(updatable = false)
    private LocalDateTime startedAt;

    @Column(updatable = false)
    private LocalDateTime completedAt;

    public Duration getDeliveryDuration() {
        if (startedAt == null || completedAt == null) {
            return null;
        }
        return Duration.between(startedAt, completedAt);
    }
}