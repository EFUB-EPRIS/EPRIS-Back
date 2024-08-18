package com.epris.homepage.activity.corporate_project.service;

import com.epris.homepage.activity.corporate_project.domain.CorporateProject;
import com.epris.homepage.activity.corporate_project.domain.CorporateProjectImage;
import com.epris.homepage.activity.corporate_project.dto.CorporateProjectRequestDto;
import com.epris.homepage.activity.corporate_project.dto.CorporateProjectResponseDto;
import com.epris.homepage.activity.corporate_project.repository.CorporateProjectImageRespository;
import com.epris.homepage.activity.corporate_project.repository.CorporateProjectRepository;
import com.epris.homepage.global.dto.ImageInfo;
import com.epris.homepage.global.dto.ImageUrl;
import com.epris.homepage.global.exception.CustomException;
import com.epris.homepage.global.exception.ErrorCode;
import com.epris.homepage.global.service.FileService;
import com.epris.homepage.global.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class CorporateProjectService {

    private final FileService fileService;
    private final ImageService imageService;

    private final CorporateProjectRepository corporateProjectRepository;
    private final CorporateProjectImageRespository corporateProjectImageRespository;
    Long CORPORATE_PROJECT_ID = 1L;

    /* 협력 프로젝트 수정 */
    public ResponseEntity<CorporateProjectResponseDto> updateCorporateProject(CorporateProjectRequestDto requestDto) throws IOException {
        /* 1번 프로젝트에 대한 수정만 반복될 것임 */
        CorporateProject updateCorporateProject = findById(CORPORATE_PROJECT_ID);

        /* 기존 이미지 삭제 */
        deleteCorporateProjectImageList(updateCorporateProject);

        /* 세션 업데이트 */
        updateCorporateProject.update(requestDto.getProjectInfo());
        saveImageList(updateCorporateProject,requestDto.getImageUrlList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(CorporateProjectResponseDto.of(updateCorporateProject,
                        makeImageInfoDto(corporateProjectImageRespository.findAllByCorporateProject(updateCorporateProject))));


    }

    /* 협력 프로젝트 조회 */
    public ResponseEntity<CorporateProjectResponseDto> findCorporateProject() {
        CorporateProject corporateProject = findById(CORPORATE_PROJECT_ID);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CorporateProjectResponseDto.of(corporateProject,
                        makeImageInfoDto(corporateProjectImageRespository.findAllByCorporateProject(corporateProject))));
    }


    /* 협력 프로젝트의 모든 이미지 삭제 */
    private void deleteCorporateProjectImageList(CorporateProject corporateProject) throws IOException {
        List<CorporateProjectImage> corporateProjectImageList = corporateProjectImageRespository.findAllByCorporateProject(corporateProject);
        for(CorporateProjectImage corporateProjectImage : corporateProjectImageList){
            fileService.deleteImage(corporateProjectImage.getProjectImgUrl());
            corporateProjectImageRespository.delete(corporateProjectImage);
        }
    }

    /* Id 로 프로젝트 조회 */
    public CorporateProject findById(Long id){
        return corporateProjectRepository.findById(id)
                .orElseThrow(()->new CustomException(ErrorCode.NO_CONTENT_EXIST));
    }

    /* 프로젝트의 새로운 이미지 저장 */
    public void saveImageList(CorporateProject corporateProject, List<ImageUrl> imageUrlList){
        for(ImageUrl imageUrl : imageUrlList){
            corporateProjectImageRespository.save(new CorporateProjectImage(corporateProject,imageUrl.getImageUrl()));
        }
    }

    /* imageInfo dto 생성 */
    public List<ImageInfo> makeImageInfoDto(List<CorporateProjectImage> imageList){
        List<ImageInfo> imageInfoList = new ArrayList<>();
        for(CorporateProjectImage corporateProjectImage : imageList){
            imageInfoList.add(ImageInfo.of(corporateProjectImage.getCorporateProjectImgId(), corporateProjectImage.getProjectImgUrl()));
        }
        return imageInfoList;
    }
}
