package com.epris.homepage.activity.award_info.repository;

import com.epris.homepage.activity.award_info.domain.AwardInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AwardInfoRepository extends JpaRepository<AwardInfo, Long> {
}
