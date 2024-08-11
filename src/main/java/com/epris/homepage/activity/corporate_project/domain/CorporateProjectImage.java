package com.epris.homepage.activity.corporate_project.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CorporateProjectImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "corporate_project_img_id", updatable = false)
    private Long corporateProjectImgId;

    @Column(name = "project_img_url", nullable = false)
    private String projectImgUrl;

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corporate_project_id")
    private CorporateProject corporateProject;
     */
}
