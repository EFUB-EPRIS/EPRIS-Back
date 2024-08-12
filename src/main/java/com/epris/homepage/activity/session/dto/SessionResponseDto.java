package com.epris.homepage.activity.session.dto;

import com.epris.homepage.activity.session.domain.Session;
import com.epris.homepage.global.dto.ImageInfo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SessionResponseDto {
    private Long sessionId;
    private String sessionType;
    private String sessionInfo;
    private List<ImageInfo> imageList;

    public static SessionResponseDto of(Session session, List<ImageInfo> imageList){
        return SessionResponseDto.builder()
                .sessionId(session.getSessionId())
                .sessionType(session.getSessionType().toString())
                .sessionInfo(session.getSessionInfo())
                .imageList(imageList)
                .build();
    }
}
