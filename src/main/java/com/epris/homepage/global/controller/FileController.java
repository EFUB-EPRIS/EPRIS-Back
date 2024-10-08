package com.epris.homepage.global.controller;

import com.epris.homepage.global.dto.FileRequestDto;
import com.epris.homepage.global.dto.FileResponseDto;
import com.epris.homepage.global.dto.ImageUrl;
import com.epris.homepage.global.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    @PostMapping
    public ResponseEntity<FileResponseDto> getPreSignedUrl(@RequestBody FileRequestDto requestDto) throws Exception {
        String fileUrl = fileService.getPreSignedUrl(requestDto.getFileName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(FileResponseDto.of(fileUrl));
    }

    @DeleteMapping
    public ResponseEntity deleteImage(@RequestBody ImageUrl imageUrl) throws IOException {
        fileService.deleteImage(imageUrl.getImageUrl());
        return ResponseEntity.status(HttpStatus.OK)
                .body("삭제 완료");
    }
}
