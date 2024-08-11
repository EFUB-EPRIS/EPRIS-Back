package com.epris.homepage.eprian.corporate_logo.repository;

import com.epris.homepage.eprian.corporate_logo.domain.CorporateLogo;
import com.epris.homepage.eprian.corporate_logo.domain.LogoType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogoRepository extends JpaRepository<CorporateLogo, Long> {
    List<CorporateLogo> findAllByLogoType(LogoType logoType);

}
