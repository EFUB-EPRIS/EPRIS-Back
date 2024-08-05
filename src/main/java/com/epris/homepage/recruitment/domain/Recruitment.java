package com.epris.homepage.recruitment.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruitment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruitment_id", updatable = false)
    private Long recruitmentId;

    @Column(nullable = false)
    private String doc;

    @Column(nullable = false)
    private String poster;

    @Column(nullable = false)
    private String notice;

    @Column(nullable = false)
    private String deadline;

    @Column(nullable = false)
    private String interview;

    @Column(nullable = false)
    private String announcement;

    @Column(nullable = false)
    private String orientation;
}
