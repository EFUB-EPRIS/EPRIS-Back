package com.epris.homepage.admin.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponseDto {
    private String accessToken;

    @Builder
    public LoginResponseDto(final String accessToken) {
        this.accessToken = accessToken;
    }
}
