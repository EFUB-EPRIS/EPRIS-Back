package com.epris.homepage.generation.greeting_card.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GreetingCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id", updatable = false)
    private Long cardId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String introduce;

    @Column(name = "card_img", nullable = false)
    private String cardImg;

    @Builder
    public GreetingCard(String name, String position, String introduce, String cardImg) {
        this.name = name;
        this.position = position;
        this.introduce = introduce;
        this.cardImg = cardImg;
    }
}
