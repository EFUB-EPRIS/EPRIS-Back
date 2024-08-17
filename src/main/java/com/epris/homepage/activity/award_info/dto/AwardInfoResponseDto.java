package com.epris.homepage.activity.award_info.dto;

import com.epris.homepage.activity.award_info.domain.AwardInfo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AwardInfoResponseDto {
    private Long awardsInfoId;
    private String awardsInfo;
    private String projectNum;
    private String awardsNum;

    public static AwardInfoResponseDto of(AwardInfo awardInfo){
        return AwardInfoResponseDto.builder()
                .awardsInfoId(awardInfo.getAwardsId())
                .awardsInfo(awardInfo.getAwardsInfo())
                .projectNum(awardInfo.getProjectNum())
                .awardsNum(awardInfo.getAwardsNum())
                .build();
    }
}
