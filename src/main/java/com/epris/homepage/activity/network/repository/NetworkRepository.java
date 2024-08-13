package com.epris.homepage.activity.network.repository;

import com.epris.homepage.activity.network.domain.Network;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NetworkRepository extends JpaRepository<Network, Long> {
}
