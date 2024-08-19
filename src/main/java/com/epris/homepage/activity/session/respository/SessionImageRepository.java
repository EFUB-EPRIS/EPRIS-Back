package com.epris.homepage.activity.session.respository;

import com.epris.homepage.activity.session.domain.Session;
import com.epris.homepage.activity.session.domain.SessionImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionImageRepository extends JpaRepository<SessionImage, Long> {
    List<SessionImage> findAllBySession(Session session);
    Boolean existsBySessionImgUrl(String SessionImgUrl);
    SessionImage findBySessionImgUrl(String SessionImgUrl);
}
