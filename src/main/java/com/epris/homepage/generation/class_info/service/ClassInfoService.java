package com.epris.homepage.generation.class_info.service;

import com.epris.homepage.generation.class_info.domain.ClassInfo;
import com.epris.homepage.generation.class_info.dto.ClassInfoRequestDto;
import com.epris.homepage.generation.class_info.dto.ClassInfoResponseDto;
import com.epris.homepage.generation.class_info.repository.ClassInfoRepository;
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

    /* 기수 정보 수정 */
    public ResponseEntity<ClassInfoResponseDto> updateClassInfo(ClassInfoRequestDto requestDto) throws IOException {
        /* 기수 정보 가져오기 */
        ClassInfo classInfo = findById(1L);

        /* 기수 정보 업데이트 */
        classInfo.updateClassinfo(requestDto.getNum(), requestDto.getPhoneNum(), requestDto.getPhoneNumInfo(), requestDto.getEmail(),
                requestDto.getInstaLink(), requestDto.getBlogLink(), requestDto.getNewsLink(), requestDto.getAdminImg());

        return ResponseEntity.status(HttpStatus.OK)
                .body(ClassInfoResponseDto.of(classInfo));
    }


    /* id로 ClassInfo 조회 */
    @Transactional(readOnly = true)
    public ClassInfo findById(Long id) {
        return classInfoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("기수 정보가 없습니다."));
    }
}
