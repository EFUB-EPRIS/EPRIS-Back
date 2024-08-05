package com.epris.homepage.eprian.corporate_logo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CorporateLogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id", updatable = false)
    private Long brandId;

    @Column(name = "logo_img", nullable = false)
    private String logoImg;

    @Enumerated(EnumType.STRING)
    private LogoType logoType;
}
