package com.epris.homepage.global.dto;

import lombok.*;

import java.util.List;
import java.util.stream.Collectors;


@Getter
@Builder
@AllArgsConstructor
public class ImageResponseDto {

    private List<ImageInfo> imageList;

    public static ImageResponseDto of(List<ImageInfo> imageList){
        return ImageResponseDto.builder()
                .imageList(imageList)
                .build();
    }
}
