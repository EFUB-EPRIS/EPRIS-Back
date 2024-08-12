package com.epris.homepage.recruitment.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
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

    /* 모집 정보 수정 */
    public void updateRecruitment(String doc, String poster, String notice, String deadline,
                                  String interview, String announcement, String orientation) {
        this.doc = doc;
        this.poster = poster;
        this.notice = notice;
        this.deadline = deadline;
        this.interview = interview;
        this.announcement = announcement;
        this.orientation = orientation;
    }
}
