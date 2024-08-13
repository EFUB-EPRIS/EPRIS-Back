package com.epris.homepage.eprian.member.repository;

import com.epris.homepage.eprian.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
