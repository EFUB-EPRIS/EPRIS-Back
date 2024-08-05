package com.epris.homepage.eprian.corporate_logo.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LogoType {
    CORPORATE(1,"협력기업","협력 프로젝트를 함께한 기업의 로고"),
    ALUMNI(2, "취업기업","졸업한 선배들이 취업한 기업의 로고");


    private final Integer id;
    private final String title;
    private final String description;
}
