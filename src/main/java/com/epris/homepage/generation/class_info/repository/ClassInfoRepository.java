package com.epris.homepage.generation.class_info.repository;

import com.epris.homepage.generation.class_info.domain.ClassInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassInfoRepository extends JpaRepository<ClassInfo, Long> {
}
