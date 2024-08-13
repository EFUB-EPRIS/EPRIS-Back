package com.epris.homepage.activity.network.service;

import com.epris.homepage.activity.network.domain.Network;
import com.epris.homepage.activity.network.dto.NetworkReqeustDto;
import com.epris.homepage.activity.network.dto.NetworkResponseDto;
import com.epris.homepage.activity.network.repository.NetworkRepository;
import com.epris.homepage.global.exception.CustomException;
import com.epris.homepage.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NetworkService {

    private final NetworkRepository networkRepository;

    Long NETWORK_ID_NIGHT = 1L;
    Long NETWORK_ID_LECTURE = 2L;


    /* 네트워크 수정 */
    public ResponseEntity<NetworkResponseDto> updateNetwork(String type, NetworkReqeustDto reqeustDto) {
        Network updateNetwork = findNetworkByType(type);
        updateNetwork.update(reqeustDto.getImageUrl(),reqeustDto.getNetworkInfo());
        return ResponseEntity.status(HttpStatus.OK)
                .body(NetworkResponseDto.of(updateNetwork));
    }

    /* 네트워크 타입으로 네트워크 조회 */
    public Network findNetworkByType(String type){
        Long networkId = null;
        if(type.equals("night")) networkId = NETWORK_ID_NIGHT;
        else networkId = NETWORK_ID_LECTURE;
        return findNetworkByNetworkId(networkId);
    }

    /* 네트워크 id로 네트워크 조회 */
    public Network findNetworkByNetworkId(Long networkId){
        return networkRepository.findById(networkId)
                .orElseThrow(()-> new CustomException(ErrorCode.NO_CONTENT_EXIST));
    }

}
