package com.xsoci.backend.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "accessibilities",
    indexes = {
        @Index(name = "idx_access_path", columnList = "access_path")
    },
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_role_accessibility",
            columnNames = {"role_id", "access_path", "method"}
        )
    }
)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Accessibility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "access_id")
    private Long id;

    @Column(name = "access_path", nullable = false, length = 255)
    private String accessPath;

    @Column(name = "method", nullable = false, length = 10)
    private String method;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

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
