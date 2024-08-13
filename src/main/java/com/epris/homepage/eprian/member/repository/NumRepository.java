package com.epris.homepage.eprian.member.repository;

import com.epris.homepage.eprian.member.domain.Num;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NumRepository extends JpaRepository<Num, Long> {
    Num findByNumInfo(String numInfo);
    Boolean existsByNumInfo(String numInfo);
}
