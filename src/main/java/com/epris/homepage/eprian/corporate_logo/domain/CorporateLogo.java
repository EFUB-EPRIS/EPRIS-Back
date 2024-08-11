package com.epris.homepage.eprian.corporate_logo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

@Entity
@Getter
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

    @Builder
    public CorporateLogo(String logoImg, LogoType logoType){
        this.logoImg = logoImg;
        this.logoType = logoType;
    }
}
