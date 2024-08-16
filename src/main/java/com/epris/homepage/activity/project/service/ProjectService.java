package com.epris.homepage.activity.project.service;

import com.epris.homepage.activity.project.domain.Project;
import com.epris.homepage.activity.project.dto.ProjectRequestDto;
import com.epris.homepage.activity.project.dto.ProjectResponseDto;
import com.epris.homepage.activity.project.repository.ProjectRepository;
import com.epris.homepage.global.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final FileService fileService;

    /* 프로젝트 생성 */
    public ResponseEntity<ProjectResponseDto> createProject(ProjectRequestDto requestDto){
        /* 프로젝트 저장 */
        Project project = projectRepository.save(new Project(
                requestDto.getYear(),
                requestDto.getInfo()
        ));

        return ResponseEntity.status(HttpStatus.CREATED).body(ProjectResponseDto.of(project));
    }

}
