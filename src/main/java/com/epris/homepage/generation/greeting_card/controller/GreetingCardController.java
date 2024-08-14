package com.epris.homepage.generation.greeting_card.controller;

import com.epris.homepage.generation.greeting_card.domain.GreetingCard;
import com.epris.homepage.generation.greeting_card.dto.GreetingCardRequestDto;
import com.epris.homepage.generation.greeting_card.dto.GreetingCardResponseDto;
import com.epris.homepage.generation.greeting_card.service.GreetingCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cards")
public class GreetingCardController {
    private final GreetingCardService greetingCardService;

    /* 그리팅 카드 생성 */
    @PostMapping
    public ResponseEntity<GreetingCardResponseDto> createCard(@RequestBody GreetingCardRequestDto requestDto) {
        return greetingCardService.createCard(requestDto);
    }
}
