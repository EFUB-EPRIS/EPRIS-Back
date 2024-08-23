package com.epris.homepage.activity.session.service;

import com.epris.homepage.activity.session.domain.Session;
import com.epris.homepage.activity.session.domain.SessionImage;
import com.epris.homepage.activity.session.domain.SessionType;
import com.epris.homepage.activity.session.dto.SessionRequestDto;
import com.epris.homepage.activity.session.dto.SessionResponseDto;
import com.epris.homepage.activity.session.respository.SessionImageRepository;
import com.epris.homepage.activity.session.respository.SessionRespository;
import com.epris.homepage.global.dto.ImageInfo;
import com.epris.homepage.global.dto.ImageUrl;
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
public class SessionService {
    private final SessionRespository sessionRespository;
    private final SessionImageRepository sessionImageRepository;

    private final FileService fileService;


    /* 세션 업데이트 */
    public ResponseEntity<SessionResponseDto> updateSession(String type, SessionRequestDto requestDto) throws IOException {
        SessionType sessionType = getSessionType(type);
        Session updateSession = findSessionByType(sessionType);

        /* 기존 세션 이미지 */
        List<SessionImage> deleteSessionImageList = sessionImageRepository.findAllBySession(updateSession);
        for(ImageUrl url : requestDto.getImageUrlList()){
            /* 만약 유지되어야 할 이미지의 경우, 삭제 목록에서 제외 */
            if(sessionImageRepository.existsBySessionImgUrl(url.getImageUrl())){
                deleteSessionImageList.remove(sessionImageRepository.findBySessionImgUrl(url.getImageUrl()));
            }
            /* 새롭게 저장해야 할 이미지의 경우, 저장 */
            else{
                sessionImageRepository.save(new SessionImage(url.getImageUrl(),updateSession));
            }
        }
        /* 기존 이미지 삭제*/
        if(!deleteSessionImageList.isEmpty())deleteSessionImageList(deleteSessionImageList);

        /* 세션 업데이트 */
        updateSession.update(requestDto.getSessionInfo());

        return ResponseEntity.status(HttpStatus.OK)
                .body(SessionResponseDto.of(updateSession,makeImageInfoDto(sessionImageRepository.findAllBySession(updateSession))));
    }

    /* 세션의 타입으로 세션 조회 */
    public ResponseEntity<SessionResponseDto> findSessionBySessionType(String type) {
        SessionType sessionType = getSessionType(type);
        Session session = findSessionByType(sessionType);

        return ResponseEntity.status(HttpStatus.OK)
                .body(SessionResponseDto.of(session,makeImageInfoDto(sessionImageRepository.findAllBySession(session))));
    }


    /* 세션 타입 조회 */
    public SessionType getSessionType(String type){
        if(type.equals("pr")) return SessionType.PRCASE;
        else if(type.equals("issue")) return SessionType.ISSUE;
        else return SessionType.TREND;
    }

    /* 타입 별 세션 조회 */
    /* 설계 상, 각 타입 당 존재하는 세션은 1개 뿐임. 따라서 목록의 첫 객체를 반환함.*/
    public Session findSessionByType(SessionType sessionType){
        return sessionRespository.findAllBySessionType(sessionType).get(0);
    }


    /* 세션의 기존 이미지 삭제 */
    public void deleteSessionImageList(List<SessionImage> sessionImageList) throws IOException {
        for(SessionImage sessionImage : sessionImageList){
            fileService.deleteImage(sessionImage.getSessionImgUrl());
            sessionImageRepository.delete(sessionImage);
        }
    }

    /* 세션의 새로운 이미지 저장 */
    public void saveImageList(Session session, List<ImageUrl> imageUrlList){
        for(ImageUrl imageUrl : imageUrlList){
           sessionImageRepository.save(new SessionImage(imageUrl.getImageUrl(), session));
        }
    }

    /* imageInfo dto 생성 */
    public List<ImageInfo> makeImageInfoDto(List<SessionImage> imageList){
        List<ImageInfo> imageInfoList = new ArrayList<>();
        for(SessionImage sessionImage : imageList){
            imageInfoList.add(ImageInfo.of(sessionImage.getSessionImgId(),sessionImage.getSessionImgUrl()));
        }
        return imageInfoList;
    }
}
