package com.epris.homepage.activity.award_info.service;

import com.epris.homepage.activity.award_info.domain.AwardInfo;
import com.epris.homepage.activity.award_info.dto.AwardInfoResponseDto;
import com.epris.homepage.activity.award_info.dto.AwardRequestDto;
import com.epris.homepage.activity.award_info.repository.AwardInfoRepository;
import com.epris.homepage.global.exception.CustomException;
import com.epris.homepage.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class AwardInfoService {
    private final AwardInfoRepository awardInfoRepository;
    Long AWARD_INFO_ID = 1L;

    /* 수상 내역&통계 수정 */
    public ResponseEntity<AwardInfoResponseDto> updateAwardInfo(AwardRequestDto requestDto) {
        AwardInfo updateAwardInfo = findAwardInfoById(AWARD_INFO_ID);
        updateAwardInfo.update(requestDto.getAwardsInfo(),requestDto.getProjectNum(),requestDto.getAwardsNum());
        return ResponseEntity.status(HttpStatus.OK)
                .body(AwardInfoResponseDto.of(updateAwardInfo));
    }

    /* 수상 내역 조회 */
    public ResponseEntity<AwardInfoResponseDto> findAwardInfo() {
        AwardInfo awardInfo = findAwardInfoById(AWARD_INFO_ID);
        return ResponseEntity.status(HttpStatus.OK)
                .body(AwardInfoResponseDto.of(awardInfo));
    }

    /* id 로 awardInfo 조회 */
    public AwardInfo findAwardInfoById(Long awardInfoId){
        return awardInfoRepository.findById(awardInfoId)
                .orElseThrow(()->new CustomException(ErrorCode.NO_CONTENT_EXIST));
    }
}
