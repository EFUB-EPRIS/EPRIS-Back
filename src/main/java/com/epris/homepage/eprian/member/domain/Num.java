package com.epris.homepage.eprian.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Num {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_id", updatable = false)
    private Long numId;

    @Column(name = "num_info", nullable = false)
    private String numInfo;

    @Builder
    public Num(String numInfo){
        this.numInfo = numInfo;
    }
}
