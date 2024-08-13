package com.epris.homepage.recruitment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecruitmentRequestDto {
    private String doc;
    private String poster;
    private String notice;
    private String deadline;
    private String interview;
    private String announcement;
    private String orientation;
}
