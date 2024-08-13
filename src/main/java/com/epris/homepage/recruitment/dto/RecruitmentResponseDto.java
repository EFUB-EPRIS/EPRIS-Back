package com.epris.homepage.recruitment.dto;

import com.epris.homepage.recruitment.domain.Recruitment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitmentResponseDto {
    private String doc;
    private String poster;
    private String notice;
    private String deadline;
    private String interview;
    private String announcement;
    private String orientation;

    public static RecruitmentResponseDto of(Recruitment recruitment){
        return RecruitmentResponseDto.builder()
                .doc(recruitment.getDoc())
                .poster(recruitment.getPoster())
                .notice(recruitment.getNotice())
                .deadline(recruitment.getDeadline())
                .interview(recruitment.getInterview())
                .announcement(recruitment.getAnnouncement())
                .orientation(recruitment.getOrientation())
                .build();
    }
}
