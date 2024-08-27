package com.epris.homepage.global.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
@Valid
public class ImageUrl {

    @NotBlank
    private String imageUrl;
}
