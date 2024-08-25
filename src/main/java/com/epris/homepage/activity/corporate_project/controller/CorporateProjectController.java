package com.epris.homepage.activity.corporate_project.controller;


import com.epris.homepage.activity.corporate_project.dto.CorporateProjectRequestDto;
import com.epris.homepage.activity.corporate_project.dto.CorporateProjectResponseDto;
import com.epris.homepage.activity.corporate_project.service.CorporateProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/corporate-project")
public class CorporateProjectController {
    private final CorporateProjectService corporateProjectService;

    @PostMapping
    public ResponseEntity<CorporateProjectResponseDto> updateCorporateProject(@Valid @RequestBody CorporateProjectRequestDto requestDto) throws IOException {
        return corporateProjectService.updateCorporateProject(requestDto);
    }

    @GetMapping
    public ResponseEntity<CorporateProjectResponseDto> findCorporateProject(){
        return corporateProjectService.findCorporateProject();
    }
}
