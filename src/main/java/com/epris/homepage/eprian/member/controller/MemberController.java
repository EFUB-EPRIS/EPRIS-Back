package com.epris.homepage.eprian.member.controller;

import com.epris.homepage.eprian.member.dto.MemberNumResponseDto;
import com.epris.homepage.eprian.member.dto.MemberRequestDto;
import com.epris.homepage.eprian.member.dto.MemberResponseDto;
import com.epris.homepage.eprian.member.service.MemberService;
import jakarta.validation.Valid;
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
    public ResponseEntity<MemberResponseDto> saveMember(@Valid @RequestBody MemberRequestDto memberRequestDto) throws IOException {
        return memberService.saveMember(memberRequestDto);
    }

    /*기수별 모든 학회원 목록 조회 */
    @GetMapping("/byAllNum")
    public ResponseEntity<List<MemberNumResponseDto>> findMemberListByNum(){
        return memberService.findMemberListByNum();
    }

    /* 운영진 학회원 목록 조회 */
    @GetMapping("/executives")
    public ResponseEntity<List<MemberResponseDto>> findExecutives(){
        return memberService.findExecutives();
    }

    /* 활동중인 학회원 목록 조회 */
    @GetMapping("/isActive")
    public ResponseEntity<List<MemberResponseDto>> findActiveMemberList(){
        return memberService.findActiveMemberList();
    }

    /* 기수별 수료 학회원 목록 조회 */
    @GetMapping("/alumni")
    public ResponseEntity<List<MemberResponseDto>> findAlumniMemberListByNum(@RequestParam("num")String num){
        return memberService.findAlumniMemberListByNum(num);
    }


    /* 학회원 정보 삭제 */
    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMember(@PathVariable("memberId") Long memberId) throws IOException {
        return memberService.deleteMember(memberId);
    }

    /* 특정 기수 학회원 전체 삭제 */
    @DeleteMapping
    public ResponseEntity deleteMemberListByNum(@RequestParam("num")String num) throws IOException {
        return memberService.deleteMemberListByNum(num);
    }
}
