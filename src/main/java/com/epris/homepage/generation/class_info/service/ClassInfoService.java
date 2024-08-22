package com.epris.homepage.generation.class_info.service;

import com.epris.homepage.generation.class_info.domain.ClassInfo;
import com.epris.homepage.generation.class_info.dto.ClassInfoRequestDto;
import com.epris.homepage.generation.class_info.dto.ClassInfoResponseDto;
import com.epris.homepage.generation.class_info.repository.ClassInfoRepository;
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

@Service
@Transactional
@RequiredArgsConstructor
public class ClassInfoService {
    private final ClassInfoRepository classInfoRepository;
    private final FileService fileService;

    /* 기수 정보 수정 */
    public ResponseEntity<ClassInfoResponseDto> updateClassInfo(ClassInfoRequestDto requestDto) throws IOException {
        /* 기수 정보 가져오기 */
        ClassInfo classInfo = findById(1L);

        /* 기존에 저장되어있던 이미지와 요청 dto의 url이 다를 경우, 기존 url 삭제 */
        if(!classInfo.getAdminImg().isEmpty() && !classInfo.getAdminImg().equals(requestDto.getAdminImg())) {
            fileService.deleteImage(classInfo.getAdminImg());
        }

        /* 기수 정보 업데이트 */
        classInfo.updateClassinfo(requestDto.getNum(), requestDto.getPhoneNum(), requestDto.getPhoneNumInfo(), requestDto.getEmail(),
                requestDto.getInstaLink(), requestDto.getBlogLink(), requestDto.getNewsLink(), requestDto.getAdminImg());

        return ResponseEntity.status(HttpStatus.OK).body(ClassInfoResponseDto.of(classInfo));
    }

    /* 기수 정보 조회 */
    public ResponseEntity<ClassInfoResponseDto> getClassInfo(){
        /* 기수 정보 가져오기 */
        ClassInfo classInfo = findById(1L);

        return ResponseEntity.status(HttpStatus.OK).body(ClassInfoResponseDto.of(classInfo));
    }

    /* id로 ClassInfo 조회 */
    @Transactional(readOnly = true)
    public ClassInfo findById(Long id) {
        return classInfoRepository.findById(id).orElseThrow(()-> new CustomException(ErrorCode.NO_CONTENT_EXIST));
    }
}
