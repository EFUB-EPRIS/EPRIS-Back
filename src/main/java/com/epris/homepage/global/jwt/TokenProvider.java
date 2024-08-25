package com.epris.homepage.global.jwt;

import com.epris.homepage.admin.domain.Admin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TokenProvider {
    private final JwtProperties jwtProperties;

    public String generateAccessToken(Admin admin, Duration expiredAt){
        Date now = new Date();
        return makeToken(admin, new Date(now.getTime() + expiredAt.toMillis()));
    }

    public String generateRefreshToken(Admin admin, Duration expiredAt){
        Date now = new Date();
        return makeToken(admin, new Date(now.getTime() + expiredAt.toMillis()));
    }

    /* JWT 토큰 생성 */
    public String makeToken(Admin admin, Date expiredMs){
        Date now = new Date();

        return Jwts.builder()
                /* 헤더 - 토큰 타입: JWT */
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                /* 내용 - 토큰 발급자 */
                .setIssuer(jwtProperties.getIssuer())
                /* 내용 - 토큰이 발급된 시간: 현재 시간 */
                .setIssuedAt(now)
                /* 내용 - 토큰 만료 시간: expiredMs 변수값 */
                .setExpiration(expiredMs)
                /* 내용 - 토큰 제목: 관리자 이름 */
                .setSubject(admin.getName())
                /* 내용 - 클레임 id: 관리자 id */
                .claim("id", admin.getAdminId())
                /* 서명 - 시크릿키와 함께 해시값을 HS256 방식으로 암호화 */
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretkey())
                .compact();
    }

    /* 토큰이 만료됐는지 확인 */
    public boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDate(token);
        return expiration.before(new Date());
    }

    /* JWT 토큰 유효성 검증 */
    public boolean validateToken(String token){
        try{
            /* secretKey를 사용해 토큰 복호화 */
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretkey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e){
            /* 복호화 과정에서 에러가 나면 유효하지 않은 토큰 */
            return false;
        }
    }

    /* 토큰 기반으로 인증 정보 가져오기 */
    public Authentication getAuthentication(String token){
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(
                new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new UsernamePasswordAuthenticationToken(
                new User(claims.getSubject(),"",authorities),token,authorities);
    }

    /* 클레임 가져오기 */
    private Claims getClaims(String token){
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretkey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /* 토큰의 만료 날짜 가져오기 */
    public Date getExpirationDate(String token){
        return getClaims(token).getExpiration();
    }
}
