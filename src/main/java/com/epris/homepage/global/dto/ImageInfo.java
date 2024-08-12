package com.epris.homepage.global.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

@Getter
@Builder
@AllArgsConstructor
public class ImageInfo {
    private Long imageId;
    private String imageUrl;

    public static ImageInfo of(Long imageId, String imageUrl){
        return ImageInfo.builder()
                .imageId(imageId)
                .imageUrl(imageUrl)
                .build();
    }
}
