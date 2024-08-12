package com.epris.homepage.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImageRequestDto {
    private List<ImageUrl> imageUrlList;
}
