package com.xsoci.backend.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "ai_apis",
    indexes = {
        @Index(name = "idx_ai_api_endpoint", columnList = "ai_api_endpoint")
    },
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_ai_api_endpoint_method",
            columnNames = {"ai_api_endpoint", "ai_api_method"}
        )
    }
)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AiApi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ai_api_id")
    private Long id;
    
    @Column(name = "ai_api_name", nullable = false, length = 255)
    private String aiApiName;

    @Column(name = "ai_api_endpoint", nullable = false, length = 255)
    private String aiApiEndpoint;

    @Column(name = "ai_api_method", nullable = false, length = 10)
    private String aiApiMethod;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "description", length = 255)
    private String description;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "modified_by")
    private User modifiedBy;
}
