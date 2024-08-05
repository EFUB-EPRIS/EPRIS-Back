package com.epris.homepage.eprian.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", updatable = false)
    private Long memberId;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Position position;

    @Column(name = "member_info", nullable = false)
    private String memberInfo;

    @Column(name = "profile_img", nullable = false)
    private String profileImg;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "num_id")
    private Num num;
}
