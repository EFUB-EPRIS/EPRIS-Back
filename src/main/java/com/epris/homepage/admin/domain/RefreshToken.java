package com.epris.homepage.admin.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "admin_id", nullable = false, unique = true)
    private Long adminId;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    @Builder
    public RefreshToken(Long adminId, String refreshToken) {
        this.adminId = adminId;
        this.refreshToken = refreshToken;
    }

    public RefreshToken update(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }
}
