package com.epris.homepage.activity.corporate_project.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CorporateProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "corporate_project_id", updatable = false)
    private Long corporateProjectId;

    @Column(name = "project_info", nullable = false, columnDefinition = "TEXT")
    private String projectInfo;
}
