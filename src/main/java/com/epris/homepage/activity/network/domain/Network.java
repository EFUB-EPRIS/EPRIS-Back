package com.epris.homepage.activity.network.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Network {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "network_id", updatable = false)
    private Long networkId;

    @Enumerated(EnumType.STRING)
    private NetworkType networkType;

    @Column(name = "network_img", updatable = false)
    private String networkImg;

    @Column(name = "network_info", updatable = false, columnDefinition = "TEXT")
    private String networkInfo;
}