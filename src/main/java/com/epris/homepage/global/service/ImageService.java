package com.epris.homepage.global.service;

import com.epris.homepage.global.dto.ImageInfo;
import com.epris.homepage.global.dto.ImageUrl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final FileService fileService;

    /* 이미지 url list 전달받아 이미지 삭제 */
    public void deleteImageList(List<ImageUrl> imageUrlList) throws IOException {
        for(ImageUrl imageUrl : imageUrlList){
            fileService.deleteImage(imageUrl.getImageUrl());
        }
    }

}
