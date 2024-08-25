package com.epris.homepage.activity.session.dto;

import com.epris.homepage.global.dto.ImageUrl;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SessionRequestDto {

    @NotBlank
    private String sessionInfo;

    @NotBlank
    private List<ImageUrl> imageUrlList;
}
