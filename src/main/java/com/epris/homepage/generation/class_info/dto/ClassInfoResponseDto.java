package com.epris.homepage.generation.class_info.dto;

import com.epris.homepage.generation.class_info.domain.ClassInfo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ClassInfoResponseDto {
    private String num;
    private String phoneNum;
    private String phoneNumInfo;
    private String email;
    private String instaLink;
    private String blogLink;
    private String newsLink;
    private String adminImg;

    public static ClassInfoResponseDto of(ClassInfo classInfo) {
        return ClassInfoResponseDto.builder()
                .num(classInfo.getNum())
                .phoneNum(classInfo.getPhoneNum())
                .phoneNumInfo(classInfo.getPhoneNumInfo())
                .email(classInfo.getEmail())
                .instaLink(classInfo.getInstaLink())
                .blogLink(classInfo.getBlogLink())
                .newsLink(classInfo.getNewsLink())
                .adminImg(classInfo.getAdminImg())
                .build();
    }
}
