package com.epris.homepage.activity.project.service;

import com.epris.homepage.activity.project.domain.Project;
import com.epris.homepage.activity.project.dto.ProjectRequestDto;
import com.epris.homepage.activity.project.dto.ProjectResponseDto;
import com.epris.homepage.activity.project.repository.ProjectRepository;
import com.epris.homepage.global.exception.CustomException;
import com.epris.homepage.global.exception.ErrorCode;
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

    /* 프로젝트 수정 */
    public ResponseEntity<ProjectResponseDto> updateProject(Long projectId, ProjectRequestDto requestDto){
        /* 해당 id 프로젝트 가져오기 */
        Project project = findById(projectId);

        /* 프로젝트 업데이트 */
        project.updateProject(requestDto.getYear(), requestDto.getInfo());

        return ResponseEntity.status(HttpStatus.OK).body(ProjectResponseDto.of(project));
    }

    /* id로 프로젝트 조회 */
    @Transactional(readOnly = true)
    public Project findById(Long projectId){
        return projectRepository.findById(projectId).orElseThrow(()-> new CustomException(ErrorCode.NO_CONTENT_EXIST));
    }

}
