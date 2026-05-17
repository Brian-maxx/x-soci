package com.xsoci.backend.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "apis",
    indexes = {
        @Index(name = "idx_api_endpoint", columnList = "api_endpoint")
    },
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_api_endpoint_method",
            columnNames = {"api_endpoint", "method"}
        )
    }
)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Api {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "api_id")
    private Long id;
    
    @Column(nullable = false, length = 255)
    private String apiName;

    @Column(nullable = false, length = 255)
    private String apiEndpoint;

    @Column(nullable = false, length = 10)
    private String method;

    @Builder.Default
    @Column(nullable = false)
    private Boolean isActive = true;

    @Column(nullable = true, length = 255)
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "modified_by")
    private User modifiedBy;
}
