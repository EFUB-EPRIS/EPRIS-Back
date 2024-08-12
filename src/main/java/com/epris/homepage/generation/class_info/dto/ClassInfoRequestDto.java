package com.epris.homepage.generation.class_info.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClassInfoRequestDto {
    private String num;
    private String phoneNum;
    private String phoneNumInfo;
    private String email;
    private String instaLink;
    private String blogLink;
    private String newsLink;
    private String adminImg;
}
