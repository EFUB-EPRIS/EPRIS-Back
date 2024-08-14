package com.epris.homepage.generation.greeting_card.service;

import com.epris.homepage.generation.greeting_card.domain.GreetingCard;
import com.epris.homepage.generation.greeting_card.dto.GreetingCardRequestDto;
import com.epris.homepage.generation.greeting_card.dto.GreetingCardResponseDto;
import com.epris.homepage.generation.greeting_card.repository.GreetingCardRepository;
import com.epris.homepage.global.exception.CustomException;
import com.epris.homepage.global.exception.ErrorCode;
import com.epris.homepage.global.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class GreetingCardService {
    private final GreetingCardRepository greetingCardRepository;
    private final FileService fileService;

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

    /* 그리팅 카드 수정 */
    public ResponseEntity<GreetingCardResponseDto> updateCard(Long cardId, GreetingCardRequestDto requestDto) throws IOException {
        /* 해당 id 카드 가져오기 */
        GreetingCard card = findById(cardId);

        /* 기존 카드 이미지 삭제 */
        String cardImg = card.getCardImg();
        fileService.deleteImage(cardImg);

        /* 그리팅 카드 업데이트 */
        card.updateCard(requestDto.getName(), requestDto.getPosition(), requestDto.getIntroduce(), requestDto.getCardImg());

        return ResponseEntity.status(HttpStatus.OK).body(GreetingCardResponseDto.of(card));
    }

    /* id로 그리팅 카드 조회 */
    @Transactional(readOnly = true)
    public GreetingCard findById(Long cardId){
        return greetingCardRepository.findById(cardId).orElseThrow(()-> new CustomException(ErrorCode.NO_CONTENT_EXIST));
    }
}
