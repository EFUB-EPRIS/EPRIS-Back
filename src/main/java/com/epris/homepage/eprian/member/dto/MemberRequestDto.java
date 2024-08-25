package com.epris.homepage.eprian.member.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRequestDto {

    @NotBlank
    private Boolean isNew;

    @NotBlank
    private String num;

    @NotBlank
    private Long memberId;

    @NotBlank
    private String name;

    @NotBlank
    private String position;

    @NotBlank
    private String memberInfo;

    @NotBlank
    private String profileUrl;

    @NotBlank
    private Boolean isActive;
}
