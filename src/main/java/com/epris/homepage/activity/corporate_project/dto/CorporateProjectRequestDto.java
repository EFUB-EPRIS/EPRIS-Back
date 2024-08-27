package com.epris.homepage.activity.corporate_project.dto;

import com.epris.homepage.global.dto.ImageUrl;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CorporateProjectRequestDto {

    @NotBlank
    private String projectInfo;

    @NotEmpty
    List<ImageUrl> imageUrlList;
}
