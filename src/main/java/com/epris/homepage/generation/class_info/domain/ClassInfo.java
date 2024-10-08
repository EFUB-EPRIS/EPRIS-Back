package com.epris.homepage.generation.class_info.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClassInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "info_id", updatable = false)
    private Long infoId;

    @Column(nullable = false)
    private String num;

    @Column(name = "phone_num", nullable = false)
    private String phoneNum;

    @Column(name = "phone_num_info", nullable = false)
    private String phoneNumInfo;

    @Column(nullable = false)
    private String email;

    @Column(name = "insta_link", nullable = false)
    private String instaLink;

    @Column(name = "blog_link", nullable = false)
    private String blogLink;

    @Column(name = "news_link", nullable = false)
    private String newsLink;

    @Column(name = "admin_img", nullable = false)
    private String adminImg;

    /* 기수 정보 내용 수정 */
    public void updateClassinfo(String num, String phoneNum, String phoneNumInfo, String email,
                                String instaLink, String blogLink, String newsLink, String adminImg) {
        this.num = num;
        this.phoneNum = phoneNum;
        this.phoneNumInfo = phoneNumInfo;
        this.email = email;
        this.instaLink = instaLink;
        this.blogLink = blogLink;
        this.newsLink = newsLink;
        this.adminImg = adminImg;
    }
}
