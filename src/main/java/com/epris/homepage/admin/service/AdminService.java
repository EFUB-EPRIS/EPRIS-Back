package com.epris.homepage.admin.service;

import com.epris.homepage.admin.domain.Admin;
import com.epris.homepage.admin.dto.LoginRequestDto;
import com.epris.homepage.admin.dto.LoginResponseDto;
import com.epris.homepage.admin.dto.SignupRequestDto;
import com.epris.homepage.admin.repository.AdminRepository;
import com.epris.homepage.admin.repository.RefreshTokenRepository;
import com.epris.homepage.global.exception.CustomException;
import com.epris.homepage.global.exception.ErrorCode;
import com.epris.homepage.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AdminRepository adminRepository;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenService tokenService;

    /* 관리자 계정 생성 */
    public String saveAdmin(SignupRequestDto requestDto){
        /* 관리자 계정이 있는지 확인 */
        if(existsById(1L)){
            throw new CustomException(ErrorCode.ALREADY_EXIST);
        }

        /* 관리자 계정이 없다면 저장 */
        Admin admin = adminRepository.save(Admin.builder()
                .name(requestDto.getName())
                .password(bCryptPasswordEncoder.encode(requestDto.getPassword()))
                .build());

        return "회원가입이 완료되었습니다.";
    }

    /* 로그인 */
    public LoginResponseDto login(LoginRequestDto requestDto){
        /* 관리자 가져오기 */
        Admin admin = findById(1L);

        /* 비밀번호가 일치하는지 확인 */
        if(!bCryptPasswordEncoder.matches(requestDto.getPassword(), admin.getPassword())){
            throw new CustomException(ErrorCode.PASSWORD_MISMATCH);
        }

        /*
        로그인 성공 -> 토큰 생성
        - 액세스토큰: 30분
        - 리프레시토큰: 7일
        */
        String accessToken = tokenProvider.generateAccessToken(admin, Duration.ofMinutes(30));
        String refreshToken = tokenProvider.generateRefreshToken(admin, Duration.ofHours(1));

        /* 리프레시 토큰 저장 */
        tokenService.saveRefreshToken(refreshToken, admin.getAdminId());

        return LoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /*  로그아웃 */
    public String logout(){
        refreshTokenRepository.deleteByAdminId(1L);
        return "로그아웃되었습니다.";
    }

    /* id로 Admin 조회 */
    @Transactional(readOnly = true)
    public Admin findById(Long id) {
        return adminRepository.findById(id).orElseThrow(()-> new CustomException(ErrorCode.NO_CONTENT_EXIST));
    }

    /* 해당 id의 관리자 객체가 있는지 확인 */
    @Transactional(readOnly = true)
    public Boolean existsById(Long id) {
        return adminRepository.existsById(id);
    }
}
