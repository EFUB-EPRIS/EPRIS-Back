package com.epris.homepage.activity.project.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id", updatable = false)
    private Long projectId;

    @Column(nullable = false)
    private String year;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String info;

    @Builder
    public Project(String year, String info) {
        this.year = year;
        this.info = info;
    }
}
