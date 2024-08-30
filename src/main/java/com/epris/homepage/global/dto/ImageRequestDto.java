package com.epris.homepage.global.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImageRequestDto {

    @NotEmpty
    @Valid
    private List<ImageUrl> imageUrlList;
}
