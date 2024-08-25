package com.epris.homepage.activity.session.controller;

import com.epris.homepage.activity.session.dto.SessionRequestDto;
import com.epris.homepage.activity.session.dto.SessionResponseDto;
import com.epris.homepage.activity.session.service.SessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sessions")
public class SessionController {
    private final SessionService sessionService;

    @PostMapping
    public ResponseEntity<SessionResponseDto> updateSession(@RequestParam("sessionType")String type,
                                                            @Valid @RequestBody SessionRequestDto requestDto) throws IOException {
        return sessionService.updateSession(type,requestDto);
    }

    @GetMapping
    public ResponseEntity<SessionResponseDto> findSessionBySessionType(@RequestParam("sessionType")String type){
        return sessionService.findSessionBySessionType(type);
    }
}
