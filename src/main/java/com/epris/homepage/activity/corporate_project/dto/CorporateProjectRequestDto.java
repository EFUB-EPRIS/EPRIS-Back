package com.epris.homepage.activity.corporate_project.dto;

import com.epris.homepage.global.dto.ImageUrl;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CorporateProjectRequestDto {
    private String projectInfo;
    List<ImageUrl> imageUrlList;
    List<ImageUrl> imageUrlListToDelete;
}
