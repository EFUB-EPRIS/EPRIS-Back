package com.epris.homepage.generation.greeting_card.service;

import com.epris.homepage.generation.greeting_card.domain.GreetingCard;
import com.epris.homepage.generation.greeting_card.dto.GreetingCardRequestDto;
import com.epris.homepage.generation.greeting_card.dto.GreetingCardResponseDto;
import com.epris.homepage.generation.greeting_card.repository.GreetingCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GreetingCardService {
    private final GreetingCardRepository greetingCardRepository;

    /* 그리팅 카드 생성 */
    public ResponseEntity<GreetingCardResponseDto> createCard(GreetingCardRequestDto requestDto){
        /* 그리팅 카드 저장 */
        GreetingCard card =  greetingCardRepository.save(new GreetingCard(
                requestDto.getName(),
                requestDto.getPosition(),
                requestDto.getIntroduce(),
                requestDto.getCardImg()
        ));

        return ResponseEntity.status(HttpStatus.CREATED).body(GreetingCardResponseDto.of(card));
    }
}
