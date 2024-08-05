package com.epris.homepage.generation.greeting_card.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
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
}
