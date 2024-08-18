package com.epris.homepage.admin.repository;

import com.epris.homepage.admin.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByAdminId(Long adminId);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    /* adminId로 리프레시 토큰 삭제 */
    void  deleteByAdminId(Long adminId);

    /* adminId로 리프레시 토큰 존재 여부 확인 */
    boolean existsByAdminId(Long adminId);
}
