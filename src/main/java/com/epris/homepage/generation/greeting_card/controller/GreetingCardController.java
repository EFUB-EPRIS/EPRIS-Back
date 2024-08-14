package com.epris.homepage.generation.greeting_card.controller;

import com.epris.homepage.generation.greeting_card.domain.GreetingCard;
import com.epris.homepage.generation.greeting_card.dto.GreetingCardRequestDto;
import com.epris.homepage.generation.greeting_card.dto.GreetingCardResponseDto;
import com.epris.homepage.generation.greeting_card.service.GreetingCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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

    /* 그리팅 카드 수정 */
    @PutMapping("{card_id}")
    public ResponseEntity<GreetingCardResponseDto> updateCard(@PathVariable Long card_id,
                                                              @RequestBody GreetingCardRequestDto requestDto) throws IOException {
        return greetingCardService.updateCard(card_id, requestDto);
    }

    /* 그리팅 카드 삭제 */
    @DeleteMapping("{card_id}")
    public ResponseEntity<String> deleteCard(@PathVariable Long card_id) throws IOException{
        return greetingCardService.deleteCard(card_id);
    }
}
