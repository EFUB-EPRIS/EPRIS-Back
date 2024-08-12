package com.epris.homepage.activity.corporate_project.repository;

import com.epris.homepage.activity.corporate_project.domain.CorporateProjectImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CorporateProjectLogoRespository extends JpaRepository<CorporateProjectImage, Long> {
}
