package com.epris.homepage.activity.corporate_project.service;

import com.epris.homepage.activity.corporate_project.domain.CorporateProjectImage;
import com.epris.homepage.activity.corporate_project.repository.CorporateProjectLogoRespository;
import com.epris.homepage.global.dto.ImageRequestDto;
import com.epris.homepage.global.dto.ImageResponseDto;
import com.epris.homepage.global.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.io.IOException;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class CorporateProjectService {

    private final CorporateProjectLogoRespository corporateProjectLogoRespository;
    private final FileService fileService;
    /* 프로젝트 로고 업로드 */
    public ResponseEntity<ImageResponseDto> uploadProjectLogo(ImageRequestDto requestDto) throws IOException {
        /* 기존 이미지 삭제 */
        List<CorporateProjectImage> oldImageList = corporateProjectLogoRespository.findAll();
        deleteProjectLogo(oldImageList);

        /* 새로운 이미지 저장 */
        return ResponseEntity.status(HttpStatus.OK)
                .body(null);
    }



    /* 프로젝트 로고 삭제 */
    public void deleteProjectLogo(List<CorporateProjectImage> projectImageList) throws IOException {
        if(!projectImageList.isEmpty()){
            for(CorporateProjectImage projectImage : projectImageList){
                fileService.deleteImage(projectImage.getProjectImgUrl());
                corporateProjectLogoRespository.delete(projectImage);
            }
        }
    }

}
