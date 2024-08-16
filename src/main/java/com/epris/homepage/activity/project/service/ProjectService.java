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

import java.util.List;
import java.util.stream.Collectors;

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

    /* 프로젝트 삭제 */
    public ResponseEntity<String> deleteProject(Long projectId){
        /* 해당 id 프로젝트 가져오기 */
        Project project = findById(projectId);

        /* 프로젝트 삭제 */
        projectRepository.delete(project);

        return ResponseEntity.status(HttpStatus.OK).body("성공적으로 삭제되었습니다.");
    }

    /* 프로젝트 목록 조회 */
    public ResponseEntity<List<ProjectResponseDto>> getAllProject(){
        /* 모든 프로젝트 리스트 가져오기 */
        List<Project> projects = projectRepository.findAll();

        /* 프로젝트 객체들을 ResponseDto로 변환 */
        List<ProjectResponseDto> projectResponseDtos = projects.stream()
                .map(ProjectResponseDto::of)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(projectResponseDtos);
    }

    /* id로 프로젝트 조회 */
    @Transactional(readOnly = true)
    public Project findById(Long projectId){
        return projectRepository.findById(projectId).orElseThrow(()-> new CustomException(ErrorCode.NO_CONTENT_EXIST));
    }

}
