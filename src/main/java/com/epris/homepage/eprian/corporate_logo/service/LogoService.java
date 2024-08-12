package com.epris.homepage.eprian.corporate_logo.service;

import com.epris.homepage.eprian.corporate_logo.domain.CorporateLogo;
import com.epris.homepage.eprian.corporate_logo.domain.LogoType;
import com.epris.homepage.eprian.corporate_logo.repository.LogoRepository;
import com.epris.homepage.global.dto.ImageInfo;
import com.epris.homepage.global.dto.ImageRequestDto;
import com.epris.homepage.global.dto.ImageResponseDto;
import com.epris.homepage.global.dto.ImageUrl;
import com.epris.homepage.global.exception.CustomException;
import com.epris.homepage.global.exception.ErrorCode;
import com.epris.homepage.global.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogoService {
    private final LogoRepository logoRepository;
    private final FileService fileService;

    /* 새로운 로고 업로드 */
    public ResponseEntity<ImageResponseDto> uploadLogoList(String type, ImageRequestDto requestDto) throws IOException {
        LogoType logoType = getLogoType(type);

        /* 기존 로고 삭제 */
        if(!logoRepository.findAllByLogoType(logoType).isEmpty()){
            deleteLogo(logoRepository.findAllByLogoType(logoType));
        }

        /* 새로운 로고 저장 */
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ImageResponseDto.of(uploadNewLogoList(logoType,requestDto)));
    }

    /* 타입 별 로고 목록 조회 */
    public ResponseEntity<ImageResponseDto> findLogoListByType(String type) {
        LogoType logoType = getLogoType(type);
        List<CorporateLogo> logoList = logoRepository.findAllByLogoType(logoType);
        if(logoList.isEmpty()) throw new CustomException(ErrorCode.NO_CONTENT_EXIST);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ImageResponseDto.of(makeImageInfoDto(logoRepository.findAllByLogoType(logoType))));

    }


    /* 로고 삭제 */
    public void deleteLogo(List<CorporateLogo> logoList) throws IOException {
        for(CorporateLogo corporateLogo:logoList){
            fileService.deleteImage(corporateLogo.getLogoImg());
            logoRepository.delete(corporateLogo);
        }
    }

    /* 로고 저장 */
    public List<ImageInfo> uploadNewLogoList(LogoType type, ImageRequestDto requestDto){
        List<ImageInfo> newLogoList = new ArrayList<>();
        for(ImageUrl imageUrl : requestDto.getImageUrlList()){
            CorporateLogo newLogo = logoRepository.save(new CorporateLogo(imageUrl.getImageUrl(),type));
            newLogoList.add(ImageInfo.of(newLogo.getBrandId(),newLogo.getLogoImg()));
        }
        return newLogoList;
    }

    /* 로고 타입 변환 */
    public LogoType getLogoType(String type){
        if(type.equals("project")) return LogoType.CORPORATE;
        else return LogoType.ALUMNI;
    }

    /* imageInfo dto 생성 */
    public List<ImageInfo> makeImageInfoDto(List<CorporateLogo> logoList){
        List<ImageInfo> imageInfoList = new ArrayList<>();
        for(CorporateLogo corporateLogo:logoList){
            imageInfoList.add(ImageInfo.of(corporateLogo.getBrandId(),corporateLogo.getLogoImg()));
        }
        return imageInfoList;
    }


}
