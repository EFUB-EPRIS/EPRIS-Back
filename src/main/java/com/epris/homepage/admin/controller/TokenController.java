package com.epris.homepage.admin.controller;

import com.epris.homepage.admin.dto.TokenRequestDto;
import com.epris.homepage.admin.dto.TokenResponseDto;
import com.epris.homepage.admin.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenController {
    private final TokenService tokenService;

    /* 액세스 토큰 재발급 */
    @PostMapping("/token")
    public ResponseEntity<TokenResponseDto> createNewAccessToken(@RequestBody TokenRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(tokenService.createNewAccessToken(requestDto.getRefreshToken()));
    }
}
