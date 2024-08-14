package com.epris.homepage.eprian.member.dto;

import com.epris.homepage.eprian.member.domain.Member;
import com.epris.homepage.eprian.member.domain.Num;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberNumResponseDto {
    private String num;
    private List<MemberResponseDto> memberList;

    public static MemberNumResponseDto of(Num num, List<Member> memberList){
        return MemberNumResponseDto.builder()
                .num(num.getNumInfo())
                .memberList(memberList.stream().map(MemberResponseDto::of).collect(Collectors.toList()))
                .build();
    }
}
