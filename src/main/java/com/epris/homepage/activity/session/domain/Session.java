package com.epris.homepage.activity.session.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id", updatable = false)
    private Long sessionId;

    @Enumerated(EnumType.STRING)
    private SessionType sessionType;

    @Column(name = "session_info", updatable = false, columnDefinition = "TEXT")
    private String sessionInfo;
}
