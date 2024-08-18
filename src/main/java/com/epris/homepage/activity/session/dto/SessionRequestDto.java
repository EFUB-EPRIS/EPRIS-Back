package com.epris.homepage.activity.session.dto;

import com.epris.homepage.global.dto.ImageUrl;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SessionRequestDto {
    private String sessionInfo;
    private List<ImageUrl> imageUrlList;
}
