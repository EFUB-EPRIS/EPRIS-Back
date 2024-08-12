package com.epris.homepage.recruitment.controller;

import com.epris.homepage.recruitment.dto.RecruitmentRequestDto;
import com.epris.homepage.recruitment.dto.RecruitmentResponseDto;
import com.epris.homepage.recruitment.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruitment")
public class RecruitmentController {
    private final RecruitmentService recruitmentService;

    /* 모집 정보 수정 */
    @PutMapping
    public ResponseEntity<RecruitmentResponseDto> updateRecruitment(@RequestBody RecruitmentRequestDto requestDto) throws IOException{
        return recruitmentService.updateRecruitment(requestDto);
    }
}
