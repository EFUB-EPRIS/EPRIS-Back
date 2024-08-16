package com.epris.homepage.generation.greeting_card.dto;

import com.epris.homepage.generation.greeting_card.domain.GreetingCard;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class GreetingCardResponseDto {
    private Long cardId;
    private String name;
    private String position;
    private String introduce;
    private String cardImg;

    public static GreetingCardResponseDto of(GreetingCard greetingCard) {
        return GreetingCardResponseDto.builder()
                .cardId(greetingCard.getCardId())
                .name(greetingCard.getName())
                .position(greetingCard.getPosition())
                .introduce(greetingCard.getIntroduce())
                .cardImg(greetingCard.getCardImg())
                .build();
    }
}
