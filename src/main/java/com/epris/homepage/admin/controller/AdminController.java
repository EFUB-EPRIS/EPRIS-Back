package com.epris.homepage.admin.controller;

import com.epris.homepage.admin.dto.LoginRequestDto;
import com.epris.homepage.admin.dto.LoginResponseDto;
import com.epris.homepage.admin.dto.SignupRequestDto;
import com.epris.homepage.admin.repository.AdminRepository;
import com.epris.homepage.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final AdminRepository adminRepository;

    /* 관리자 계정 생성 */
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.saveAdmin(requestDto));
    }

    /* 관리자 페이지 로그인 */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.login(requestDto));
    }

}
