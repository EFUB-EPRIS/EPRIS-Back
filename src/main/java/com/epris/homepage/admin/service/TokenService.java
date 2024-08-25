package com.epris.homepage.admin.service;

import com.epris.homepage.admin.domain.Admin;
import com.epris.homepage.admin.domain.RefreshToken;
import com.epris.homepage.admin.dto.TokenResponseDto;
import com.epris.homepage.admin.repository.AdminRepository;
import com.epris.homepage.admin.repository.RefreshTokenRepository;
import com.epris.homepage.global.exception.CustomException;
import com.epris.homepage.global.exception.ErrorCode;
import com.epris.homepage.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@RequiredArgsConstructor
@Transactional
@Service
public class TokenService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AdminRepository adminRepository;

    /* 액세스 토큰 재발급 */
    public TokenResponseDto createNewAccessToken(String refreshToken) {
        /* 유효한 토큰인지 확인 */
        if(!tokenProvider.validateToken(refreshToken)){
            throw new CustomException(ErrorCode.EXPIRED_TOKEN);
        }

        /* 유효하다면 해당 토큰으로 관리자 ID를 찾아 관리자 객체 가져오기 */
        Long adminId = findByRefreshToken(refreshToken).getAdminId();
        Admin admin = findById(adminId);

        /* 액세스 토큰 재발급: 30분 */
        String accessToken = tokenProvider.generateAccessToken(admin, Duration.ofMinutes(30));

        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .build();
    }

    /* 리프레시 토큰 저장 */
    public void saveRefreshToken(String refreshToken, Long adminId) {
        /* 이미 토큰이 존재하면 업데이트 */
        if(existsByAdminId(adminId)){
            RefreshToken existingToken = refreshTokenRepository.findByAdminId(adminId)
                    .orElseThrow(()->new CustomException(ErrorCode.NO_CONTENT_EXIST));
            existingToken.update(refreshToken);
            refreshTokenRepository.save(existingToken);
        } else{
            /* 토큰이 존재하지 않으면 새로 저장 */
            RefreshToken newToken = new RefreshToken(adminId, refreshToken);
            refreshTokenRepository.save(newToken);
        }
    }

    /* 전달받은 리프레시 토큰으로 리프레시토큰 객체 조회 */
    @Transactional(readOnly = true)
    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(()-> new CustomException(ErrorCode.NO_CONTENT_EXIST));
    }

    /* 해당 admin id를 가지고 있는 리프레시 토큰 객체가 있는지 확인 */
    @Transactional(readOnly = true)
    public Boolean existsByAdminId(Long adminId) {
        return refreshTokenRepository.existsByAdminId(adminId);
    }

    /* admin id로 Admin 객체 조회 */
    @Transactional(readOnly = true)
    public Admin findById(Long id) {
        return adminRepository.findById(id).orElseThrow(()-> new CustomException(ErrorCode.NO_CONTENT_EXIST));
    }
}
