package com.epris.homepage.eprian.member.repository;

import com.epris.homepage.eprian.member.domain.Member;
import com.epris.homepage.eprian.member.domain.Num;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findAllByNum(Num num);
    List<Member> findAllByPositionAndIsActive(String position,Boolean isActive);
    List<Member> findAllByIsActive(Boolean isActive);
}
