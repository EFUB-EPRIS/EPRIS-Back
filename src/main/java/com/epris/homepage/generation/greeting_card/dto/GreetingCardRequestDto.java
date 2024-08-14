package com.epris.homepage.generation.greeting_card.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GreetingCardRequestDto {
    private String name;
    private String position;
    private String introduce;
    private String cardImg;
}
