package com.epris.homepage.global.Controller;

import com.epris.homepage.global.dto.FileRequestDto;
import com.epris.homepage.global.dto.FileResponseDto;
import com.epris.homepage.global.service.FileService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

@RestController
@RequiredArgsConstructor    // 생성자를 통한 의존관계 주입
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    @PostMapping
    public ResponseEntity<FileResponseDto> getPreSignedUrl(@RequestBody FileRequestDto requestDto) throws Exception {
        //String fileUrl = fileService.getPresignedUrl(requestDto.getFileName());
        String fileUrl = fileService.getPreSignedUrl(requestDto.getFileName());
        //String fileUrl = fileService.getPresignedUrl(requestDto.getFileName());
        //String fileUrl = fileService.getPresignUrl(requestDto.getFileName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(FileResponseDto.of(fileUrl));
    }
}
