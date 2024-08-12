package com.epris.homepage.activity.session.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SessionImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_img_id", updatable = false)
    private Long sessionImgId;

    @Column(name = "session_img_url", updatable = false)
    private String SessionImgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    private Session session;

    @Builder
    public SessionImage(String sessionImgUrl, Session session){
        this.SessionImgUrl = sessionImgUrl;
        this.session = session;
    }
}
