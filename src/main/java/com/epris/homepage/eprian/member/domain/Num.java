package com.epris.homepage.eprian.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Num {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_id", updatable = false)
    private Long numId;

    @Column(name = "num_info", nullable = false)
    private String numInfo;
}
