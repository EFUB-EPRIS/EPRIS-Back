package com.epris.homepage.activity.award_info.controller;

import com.epris.homepage.activity.award_info.dto.AwardInfoResponseDto;
import com.epris.homepage.activity.award_info.dto.AwardRequestDto;
import com.epris.homepage.activity.award_info.service.AwardInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/award")
public class AwardInfoController {
    private final AwardInfoService awardInfoService;

    @PutMapping
    public ResponseEntity<AwardInfoResponseDto> updateAwardInfo(@RequestBody AwardRequestDto requestDto){
        return awardInfoService.updateAwardInfo(requestDto);
    }
}
