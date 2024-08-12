package com.epris.homepage.activity.corporate_project.dto;

import com.epris.homepage.activity.corporate_project.domain.CorporateProject;
import com.epris.homepage.activity.session.domain.Session;
import com.epris.homepage.activity.session.dto.SessionResponseDto;
import com.epris.homepage.global.dto.ImageInfo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CorporateProjectResponseDto {
    private Long projectId;
    private String projectInfo;
    private List<ImageInfo> imageList;

    public static CorporateProjectResponseDto of(CorporateProject corporateProject, List<ImageInfo> imageList){
        return CorporateProjectResponseDto.builder()
                .projectId(corporateProject.getCorporateProjectId())
                .projectInfo(corporateProject.getProjectInfo())
                .imageList(imageList)
                .build();
    }
}
