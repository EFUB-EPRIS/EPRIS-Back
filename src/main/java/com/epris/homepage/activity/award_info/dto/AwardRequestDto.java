package com.epris.homepage.activity.award_info.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AwardRequestDto {

    @NotBlank
    private String awardsInfo;

    @NotBlank
    private String projectNum;

    @NotBlank
    private String awardsNum;
}
