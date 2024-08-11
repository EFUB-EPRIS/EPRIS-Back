package com.epris.homepage.activity.session.respository;

import com.epris.homepage.activity.session.domain.Session;
import com.epris.homepage.activity.session.domain.SessionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRespository extends JpaRepository<Session, Long> {
    List<Session> findAllBySessionType(SessionType sessionType);
}
