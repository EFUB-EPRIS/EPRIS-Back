package com.epris.homepage.eprian.member.service;

import com.epris.homepage.eprian.member.domain.Member;
import com.epris.homepage.eprian.member.domain.Num;
import com.epris.homepage.eprian.member.dto.MemberNumResponseDto;
import com.epris.homepage.eprian.member.dto.MemberRequestDto;
import com.epris.homepage.eprian.member.dto.MemberResponseDto;
import com.epris.homepage.eprian.member.repository.MemberRepository;
import com.epris.homepage.eprian.member.repository.NumRepository;
import com.epris.homepage.global.exception.CustomException;
import com.epris.homepage.global.exception.ErrorCode;
import com.epris.homepage.global.service.FileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.apache.http.client.methods.RequestBuilder.delete;


@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final NumRepository numRepository;
    private final MemberRepository memberRepository;

    private final FileService fileService;

    /* 학회원 정보 저장 */
    public ResponseEntity<MemberResponseDto> saveMember(MemberRequestDto memberRequestDto) throws IOException {
        Member member = null;
        /* 신규 학회원인 경우 */
        if(memberRequestDto.getIsNew().equals(Boolean.TRUE)){
            member = createMember(memberRequestDto);
        }
        /* 기존 회원에 대한 수정인 경우 */
        else{
            member = updateMember(memberRequestDto);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(MemberResponseDto.of(member));
    }

    /* 새로운 학회원 생성 */
    public Member createMember(MemberRequestDto requestDto){
        /* 새로운 기수의 회원인 경우, 기수 생정 후 저장 */
        if(numRepository.existsByNumInfo(requestDto.getNum()).equals(Boolean.FALSE)){
            createNum(requestDto.getNum());
        }
        Num num = numRepository.findByNumInfo(requestDto.getNum());
        return memberRepository.save(new Member(requestDto.getName(), requestDto.getPosition(), requestDto.getMemberInfo(),
                requestDto.getProfileUrl(), requestDto.getIsActive(), num));
    }

    /* 기수별 모든 학회원 목록 조회 */
    public ResponseEntity<List<MemberNumResponseDto>> findMemberListByNum() {
        List<Num> numList = numRepository.findAll();
        List<MemberNumResponseDto> memberNumResponseDtoList = new ArrayList<>();
        for(Num num : numList){
            List<Member> memberList = memberRepository.findAllByNum(num);
            memberNumResponseDtoList.add(MemberNumResponseDto.of(num,memberList));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(memberNumResponseDtoList);

    }

    /* 운영진 학회원 목록 조회 */
    public ResponseEntity<List<MemberResponseDto>> findExecutives() {
        List<MemberResponseDto> responseDtoList = new ArrayList<>();
        responseDtoList.addAll(findMemberByPosition("학회장").stream().map(MemberResponseDto::of).collect(Collectors.toList()));
        responseDtoList.addAll(findMemberByPosition("기획부장").stream().map(MemberResponseDto::of).collect(Collectors.toList()));
        responseDtoList.addAll(findMemberByPosition("운영부장").stream().map(MemberResponseDto::of).collect(Collectors.toList()));
        responseDtoList.addAll(findMemberByPosition("홍보부장").stream().map(MemberResponseDto::of).collect(Collectors.toList()));

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseDtoList);
    }

    /* 직위로 학회원 조회 */
    public List<Member> findMemberByPosition(String position){
        return memberRepository.findAllByPositionAndIsActive(position,Boolean.TRUE);
    }

    /* 기존 학회원 정보 수정 */
    public Member updateMember(MemberRequestDto requestDto) throws IOException {
        Member member = memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(()->new CustomException(ErrorCode.NO_CONTENT_EXIST));

        /* 프로필을 업데이트 할 경우(요청 url 과 DB 에 저장된 url 이 다른 경우), 기존 프로필은 삭제 */
        if(!member.getProfileImg().isBlank() && !member.getProfileImg().equals(requestDto.getProfileUrl())) fileService.deleteImage(member.getProfileImg());
        Num num = numRepository.findByNumInfo(requestDto.getNum());
        member.update(requestDto.getName(), requestDto.getPosition(), requestDto.getMemberInfo(),
                requestDto.getProfileUrl(), requestDto.getIsActive(), num);

        return member;
    }

    /* 현재 활동중인 학회원 목록 조회 */
    public ResponseEntity<List<MemberResponseDto>> findActiveMemberList() {
        /* 현재 활동중인 학회원 목록 */
        List<Member> memberList = memberRepository.findAllByIsActive(Boolean.TRUE);

        /* 목록에서 운영진 학회원 제외 */
        memberList.removeAll(findMemberByPosition("학회장"));
        memberList.removeAll(findMemberByPosition("기획부장"));
        memberList.removeAll(findMemberByPosition("홍보부장"));
        memberList.removeAll(findMemberByPosition("운영부장"));

        return ResponseEntity.status(HttpStatus.OK)
                .body(memberList.stream().map(MemberResponseDto::of).collect(Collectors.toList()));
    }

    /* 기수별 수료 학회원 목록 조회 */
    public ResponseEntity<List<MemberResponseDto>> findAlumniMemberListByNum(String num) {
        Num findNum = numRepository.findByNumInfo(num);
        return ResponseEntity.status(HttpStatus.OK)
                .body(memberRepository.findAllByNumAndIsActive(findNum,Boolean.FALSE)
                        .stream().map(MemberResponseDto::of).collect(Collectors.toList()));
    }


    /* 새로운 기수 생성 */
    public void createNum(String num){
        numRepository.save(new Num(num));
    }


    /* 학회원 삭제 */
    public ResponseEntity deleteMember(Long memberId) throws IOException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new CustomException(ErrorCode.NO_CONTENT_EXIST));
        fileService.deleteImage(member.getProfileImg());
        memberRepository.delete(member);
        return ResponseEntity.status(HttpStatus.OK)
                .body("삭제했습니다.");
    }

    /* 학회원 목록 삭제 */
    public void deleteMemberList(List<Member> memberList) throws IOException {
        for(Member member : memberList){
            fileService.deleteImage(member.getProfileImg());
            memberRepository.delete(member);
        }
    }

    /* 기수별 전체 학회원 삭제 */
    public ResponseEntity deleteMemberListByNum(String num) throws IOException {

        /* 기수 조회 */
        Num deleteNum = numRepository.findByNumInfo(num);
        if(deleteNum.getNumInfo().isBlank()) throw new CustomException(ErrorCode.INVALID_NUM);

        /* 기수로 회원 조회 */
        List<Member> memberList = memberRepository.findAllByNum(deleteNum);

        /* 기수별 회원 삭제 */
        if(!memberList.isEmpty()) deleteMemberList(memberList);

        /* 기수 정보 삭제 */
        numRepository.delete(deleteNum);

        return ResponseEntity.status(HttpStatus.OK)
                .body("삭제했습니다.");
    }
}
