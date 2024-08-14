package com.epris.homepage.eprian.member.dto;

import com.epris.homepage.eprian.member.domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponseDto {
    private Long memberId;
    private String num;
    private String name;
    private String position;
    private String memberInfo;
    private String profileUrl;
    private Boolean isActive;

    public static MemberResponseDto of(Member member){
        return MemberResponseDto.builder()
                .memberId(member.getMemberId())
                .num(member.getNum().getNumInfo())
                .name(member.getName())
                .position(member.getPosition())
                .memberInfo(member.getMemberInfo())
                .profileUrl(member.getProfileImg())
                .isActive(member.getIsActive())
                .build();
    }

}
