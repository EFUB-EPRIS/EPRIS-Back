package com.epris.homepage.activity.network.controller;

import com.epris.homepage.activity.network.dto.NetworkReqeustDto;
import com.epris.homepage.activity.network.dto.NetworkResponseDto;
import com.epris.homepage.activity.network.service.NetworkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/networks")
public class NetworkController {

    private final NetworkService networkService;

    @PostMapping
    public ResponseEntity<NetworkResponseDto> updateNetwork(@RequestParam("type")String type, @RequestBody NetworkReqeustDto reqeustDto){
        return networkService.updateNetwork(type,reqeustDto);
    }

    @GetMapping
    public ResponseEntity<List<NetworkResponseDto>>findNetworkList(){
        return networkService.findNetworkList();
    }
}
