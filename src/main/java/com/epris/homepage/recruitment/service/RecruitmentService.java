package com.epris.homepage.recruitment.service;

import com.epris.homepage.global.service.FileService;
import com.epris.homepage.recruitment.domain.Recruitment;
import com.epris.homepage.recruitment.dto.RecruitmentRequestDto;
import com.epris.homepage.recruitment.dto.RecruitmentResponseDto;
import com.epris.homepage.recruitment.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;
    private final FileService fileService;

    /* 모집 정보 수정 */
    public ResponseEntity<RecruitmentResponseDto> updateRecruitment(RecruitmentRequestDto requestDto) throws IOException{
        /* 모집 정보 가져오기 */
        Recruitment recruitment = findById(1L);

        /* 기존 파일 삭제 */
        String doc = recruitment.getDoc();
        String poster = recruitment.getPoster();
        fileService.deleteImage(doc);
        fileService.deleteImage(poster);

        /* 모집 정보 업데이트 */
        recruitment.updateRecruitment(requestDto.getDoc(), requestDto.getPoster(), requestDto.getNotice(),
                requestDto.getDeadline(),requestDto.getInterview(), requestDto.getAnnouncement(), requestDto.getOrientation());

        return ResponseEntity.status(HttpStatus.OK).body(RecruitmentResponseDto.of(recruitment));
    }

    /* 모집 정보 수정 */
    public ResponseEntity<RecruitmentResponseDto> getRecruitment() throws IOException{
        /* 모집 정보 가져오기 */
        Recruitment recruitment = findById(1L);

        return ResponseEntity.status(HttpStatus.OK).body(RecruitmentResponseDto.of(recruitment));
    }

    /* id로 Recruitment 조회 */
    @Transactional(readOnly = true)
    public Recruitment findById(Long id){
        return recruitmentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("모집 정보가 없습니다."));
    }
}
