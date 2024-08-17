package com.epris.homepage.activity.award_info.controller;

import com.epris.homepage.activity.award_info.dto.AwardInfoResponseDto;
import com.epris.homepage.activity.award_info.dto.AwardRequestDto;
import com.epris.homepage.activity.award_info.service.AwardInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/award")
public class AwardInfoController {
    private final AwardInfoService awardInfoService;


    /* 수상 내역 수정 */
    @PutMapping
    public ResponseEntity<AwardInfoResponseDto> updateAwardInfo(@RequestBody AwardRequestDto requestDto){
        return awardInfoService.updateAwardInfo(requestDto);
    }

    /* 수상 내역 조회 */
    @GetMapping
    public ResponseEntity<AwardInfoResponseDto> findAwardInfo(){
        return awardInfoService.findAwardInfo();
    }
}
