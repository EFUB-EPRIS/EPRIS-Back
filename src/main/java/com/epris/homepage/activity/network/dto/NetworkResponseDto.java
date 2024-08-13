package com.epris.homepage.activity.network.dto;

import com.epris.homepage.activity.network.domain.Network;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class NetworkResponseDto {
    private Long networkId;
    private String networkType;
    private String networkInfo;
    private String networkImg;

    public static NetworkResponseDto of(Network network){
        return NetworkResponseDto.builder()
                .networkId(network.getNetworkId())
                .networkType(network.getNetworkType().toString())
                .networkInfo(network.getNetworkInfo())
                .networkImg(network.getNetworkImg())
                .build();
    }
}
