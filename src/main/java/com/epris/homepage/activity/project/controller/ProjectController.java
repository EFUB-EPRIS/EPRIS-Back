package com.epris.homepage.activity.project.controller;

import com.epris.homepage.activity.project.dto.ProjectRequestDto;
import com.epris.homepage.activity.project.dto.ProjectResponseDto;
import com.epris.homepage.activity.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;

    /* 프로젝트 생성 */
    @PostMapping
    public ResponseEntity<ProjectResponseDto> createProject(@RequestBody ProjectRequestDto requestDto){
        return projectService.createProject(requestDto);
    }
}
