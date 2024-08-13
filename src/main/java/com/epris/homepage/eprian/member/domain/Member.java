package com.epris.homepage.eprian.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", updatable = false)
    private Long memberId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String position;

    @Column(name = "member_info", nullable = false)
    private String memberInfo;

    @Column(name = "profile_img", nullable = false)
    private String profileImg;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "num_id")
    private Num num;

    @Builder
    public Member(String name,String position, String memberInfo, String profileImg, Boolean isActive, Num num){
        this.name = name;
        this.position = position;
        this.memberInfo = memberInfo;
        this.profileImg = profileImg;
        this.isActive = isActive;
        this.num = num;
    }

    public void update(String name, String position, String memberInfo, String profileImg, Boolean isActive, Num num){
        this.name = name;
        this.position = position;
        this.memberInfo = memberInfo;
        this.profileImg = profileImg;
        this.isActive = isActive;
        this.num = num;
    }
}
