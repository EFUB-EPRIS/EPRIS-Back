package com.epris.homepage.eprian.member.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRequestDto {
    private Boolean isNew;
    private String num;
    private Long memberId;
    private String name;
    private String position;
    private String memberInfo;
    private String profileUrl;
    private Boolean isActive;
}
