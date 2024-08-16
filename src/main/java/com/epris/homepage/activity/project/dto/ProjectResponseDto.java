package com.epris.homepage.activity.project.dto;

import com.epris.homepage.activity.project.domain.Project;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectResponseDto {
    private Long projectId;
    private String year;
    private String info;

    public static ProjectResponseDto of(Project project) {
        return ProjectResponseDto.builder()
                .projectId(project.getProjectId())
                .year(project.getYear())
                .info(project.getInfo())
                .build();
    }
}
