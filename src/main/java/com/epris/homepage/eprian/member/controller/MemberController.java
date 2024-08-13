package com.epris.homepage.eprian.member.controller;

import com.epris.homepage.eprian.member.dto.MemberNumResponseDto;
import com.epris.homepage.eprian.member.dto.MemberRequestDto;
import com.epris.homepage.eprian.member.dto.MemberResponseDto;
import com.epris.homepage.eprian.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    /* 학회원 정보 수정 */
    @PostMapping
    public ResponseEntity<MemberResponseDto> saveMember(@RequestBody MemberRequestDto memberRequestDto) throws IOException {
        return memberService.saveMember(memberRequestDto);
    }

    /*기수별 모든 학회원 목록 조회 */
    @GetMapping("/byAllNum")
    public ResponseEntity<List<MemberNumResponseDto>> findMemberListByNum(){
        return memberService.findMemberListByNum();
    }


    /* 학회원 정보 삭제 */
    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMember(@PathVariable("memberId") Long memberId) throws IOException {
        return memberService.deleteMember(memberId);
    }
}
