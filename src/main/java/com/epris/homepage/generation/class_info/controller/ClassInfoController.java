package com.epris.homepage.generation.class_info.controller;

import com.epris.homepage.generation.class_info.dto.ClassInfoRequestDto;
import com.epris.homepage.generation.class_info.dto.ClassInfoResponseDto;
import com.epris.homepage.generation.class_info.service.ClassInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/class-info")
public class ClassInfoController {
    private final ClassInfoService classInfoService;

    /* 기수 정보 수정 */
    @PutMapping
    public ResponseEntity<ClassInfoResponseDto> updateClassInfo(@RequestBody ClassInfoRequestDto requestDto) throws IOException {
        return classInfoService.updateClassInfo(requestDto);
    }
}
