package com.xsoci.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "user_tokens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, unique = true, length = 1000)
    private String token;

    @NotBlank
    private String type;

    @NotNull
    private LocalDateTime expiredAt;

    @NotNull
    private LocalDateTime createdAt;
}
