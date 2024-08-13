package com.epris.homepage.activity.corporate_project.repository;


import com.epris.homepage.activity.corporate_project.domain.CorporateProject;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CorporateProjectRepository extends JpaRepository<CorporateProject, Long> {
    CorporateProject findByCorporateProjectId(Long corporateProjectId);

}
