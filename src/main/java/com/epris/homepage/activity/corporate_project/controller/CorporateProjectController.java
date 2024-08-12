package com.epris.homepage.activity.corporate_project.controller;


import com.epris.homepage.activity.corporate_project.dto.CorporateProjectRequestDto;
import com.epris.homepage.activity.corporate_project.dto.CorporateProjectResponseDto;
import com.epris.homepage.activity.corporate_project.service.CorporateProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/corporate-project")
public class CorporateProjectController {
    private final CorporateProjectService corporateProjectService;

    @PostMapping
    public ResponseEntity<CorporateProjectResponseDto> updateCorporateProject(@RequestBody CorporateProjectRequestDto requestDto) throws IOException {
        return corporateProjectService.updateCorporateProject(requestDto);
    }
}
