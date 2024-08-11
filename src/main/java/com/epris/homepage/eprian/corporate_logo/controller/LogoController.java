package com.epris.homepage.eprian.corporate_logo.controller;


import com.epris.homepage.activity.corporate_project.service.CorporateProjectService;
import com.epris.homepage.eprian.corporate_logo.service.LogoService;
import com.epris.homepage.global.dto.ImageRequestDto;
import com.epris.homepage.global.dto.ImageResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LogoController {
    private final LogoService logoService;

    /* 로고 업로드 */
    @PostMapping("/corporate-logo")
    public ResponseEntity<ImageResponseDto> uploadLogoImageList(@RequestParam("type") String type, @RequestBody ImageRequestDto requestDto) throws IOException {
        return logoService.uploadLogoList(type,requestDto);
    }

    /* 타입 별 록 조회 */
    @GetMapping("/corporate-logo")
    public ResponseEntity<ImageResponseDto> fingLogoListByType(@RequestParam("type") String type){
        return logoService.findLogoListByType(type);
    }
}
