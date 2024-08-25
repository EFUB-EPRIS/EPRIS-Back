package com.epris.homepage.activity.network.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NetworkReqeustDto {

    @NotBlank
    private String networkInfo;

    @NotBlank
    private String imageUrl;
}
