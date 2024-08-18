package com.epris.homepage.activity.award_info.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
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

    @Builder
    public AwardInfo(String awardsInfo, String projectNum, String awardsNum){
        this.awardsInfo = awardsInfo;
        this.projectNum = projectNum;
        this.awardsNum = awardsNum;
    }

    public void update(String awardsInfo, String projectNum, String awardsNum){
        this.awardsInfo = awardsInfo;
        this.projectNum = projectNum;
        this.awardsNum = awardsNum;
    }
}
