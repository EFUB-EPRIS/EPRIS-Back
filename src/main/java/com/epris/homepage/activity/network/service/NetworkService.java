package com.epris.homepage.activity.network.service;

import com.epris.homepage.activity.network.domain.Network;
import com.epris.homepage.activity.network.dto.NetworkReqeustDto;
import com.epris.homepage.activity.network.dto.NetworkResponseDto;
import com.epris.homepage.activity.network.repository.NetworkRepository;
import com.epris.homepage.global.exception.CustomException;
import com.epris.homepage.global.exception.ErrorCode;
import com.epris.homepage.global.service.FileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class NetworkService {

    private final NetworkRepository networkRepository;
    private final FileService fileService;

    Long NETWORK_ID_NIGHT = 1L;
    Long NETWORK_ID_LECTURE = 2L;


    /* 네트워크 수정 */
    public ResponseEntity<NetworkResponseDto> updateNetwork(String type, NetworkReqeustDto reqeustDto) throws IOException {
        Network updateNetwork = findNetworkByType(type);

        /* 이미지 url로 null이 들어와서는 안됨. */
        if(reqeustDto.getImageUrl().equals("")) throw new CustomException(ErrorCode.IMAGE_CANNOT_BE_NULL);

        /* 기존에 저장되어있던 이미지와 요청 dto의 url이 다른 경우, 기존 url은 삭제 */
        if(!updateNetwork.getNetworkImg().equals("") && !updateNetwork.getNetworkImg().equals(reqeustDto.getImageUrl())) fileService.deleteImage(updateNetwork.getNetworkImg());
        updateNetwork.update(reqeustDto.getImageUrl(),reqeustDto.getNetworkInfo());
        return ResponseEntity.status(HttpStatus.OK)
                .body(NetworkResponseDto.of(updateNetwork));
    }

    /* 네트워크 전체 목록 조회 */
    public ResponseEntity<List<NetworkResponseDto>> findNetworkList() {
        List<Network> networkList = networkRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(networkList.stream().map(NetworkResponseDto::of).collect(Collectors.toList()));
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
