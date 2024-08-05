package com.epris.homepage.activity.award_info.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AwardInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "awards_id", updatable = false)
    private Long awardsId;

    @Column(name = "awards_info", nullable = false, columnDefinition = "TEXT")
    private String awardsInfo;

    @Column(name = "project_num", nullable = false)
    private String projectNum;

    @Column(name = "awards_num", nullable = false)
    private String awardsNum;
}
