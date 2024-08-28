package com.epris.homepage.eprian.member.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRequestDto {

    private Boolean isNew;

    @NotBlank
    private String num;

    private Long memberId;

    @NotBlank
    private String name;

    @NotBlank
    private String position;

    @NotBlank
    private String memberInfo;

    @NotBlank
    private String profileUrl;

    private Boolean isActive;
}
