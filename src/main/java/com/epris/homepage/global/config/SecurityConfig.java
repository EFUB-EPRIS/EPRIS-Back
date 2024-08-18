package com.epris.homepage.global.config;

import com.epris.homepage.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    /* CORS 설정 */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    /* 특정 HTTP 요청에 대한 웹 기반 보안 구성 */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, TokenProvider tokenProvider) throws Exception {
        return http
                // ui가 아닌 token 인증을 할 것이므로 비활성화
                .httpBasic(AbstractHttpConfigurer::disable)
                // csrf 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                // cors 비활성화
                .cors(AbstractHttpConfigurer::disable)
                //인증 인가 설정
                .authorizeHttpRequests(requests -> {
                    /* 회원가입, 로그인, 액세스토큰 재발급, GET요청은 항상 접근 가능 */
                    requests.requestMatchers("admin/signup","admin/login","token").permitAll();
                    requests.requestMatchers(HttpMethod.GET).permitAll();
                    /* 다른 모든 요청은 막기 */
                    requests.anyRequest().authenticated();
                })
                // jwt를 사용하므로 sateless
                .sessionManagement(
                        sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(new TokenAuthFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /* 패스워드 인코더로 사용할 빈 등록 */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}