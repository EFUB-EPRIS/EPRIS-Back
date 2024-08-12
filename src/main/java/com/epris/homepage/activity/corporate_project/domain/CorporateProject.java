package com.epris.homepage.activity.corporate_project.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CorporateProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "corporate_project_id", updatable = false)
    private Long corporateProjectId;

    @Column(name = "project_info", nullable = false, columnDefinition = "TEXT")
    private String projectInfo;


    /* 프로젝트 정보 수정 */
    public void update(String projectInfo){
        this.projectInfo = projectInfo;
    }
}
