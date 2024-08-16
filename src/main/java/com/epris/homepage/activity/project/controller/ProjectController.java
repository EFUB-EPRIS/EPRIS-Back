package com.epris.homepage.activity.project.controller;

import com.epris.homepage.activity.project.dto.ProjectRequestDto;
import com.epris.homepage.activity.project.dto.ProjectResponseDto;
import com.epris.homepage.activity.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    /* 프로젝트 생성 */
    @PostMapping
    public ResponseEntity<ProjectResponseDto> createProject(@RequestBody ProjectRequestDto requestDto){
        return projectService.createProject(requestDto);
    }

    /* 프로젝트 수정 */
    @PutMapping("{project_id}")
    public ResponseEntity<ProjectResponseDto> updateProject(@PathVariable Long project_id,
                                                            @RequestBody ProjectRequestDto requestDto){
        return projectService.updateProject(project_id, requestDto);
    }

    /* 프로젝트 삭제 */
    @DeleteMapping("{project_id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long project_id){
        return projectService.deleteProject(project_id);
    }

    /* 프로젝트 목록 조회 */
    @GetMapping
    public ResponseEntity<List<ProjectResponseDto>> getAllProjects(){
        return projectService.getAllProject();
    }
}
