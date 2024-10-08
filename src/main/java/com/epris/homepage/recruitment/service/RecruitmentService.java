package com.epris.homepage.recruitment.service;

import com.epris.homepage.global.exception.CustomException;
import com.epris.homepage.global.exception.ErrorCode;
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

        /* 기존에 저장되어있던 문서와 요청 dto의 url이 다를 경우, 기존 url 삭제 */
        if(!recruitment.getDoc().isEmpty() && !recruitment.getDoc().equals(requestDto.getDoc())){
            fileService.deleteImage(recruitment.getDoc());
        }

        /* 기존에 저장되어있던 포스터와 요청 dto의 url이 다를 경우, 기존 url 삭제 */
        if(!recruitment.getPoster().isEmpty() && !recruitment.getPoster().equals(requestDto.getPoster())){
            fileService.deleteImage(recruitment.getPoster());
        }

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
        return recruitmentRepository.findById(id).orElseThrow(()-> new CustomException(ErrorCode.NO_CONTENT_EXIST));
    }
}
