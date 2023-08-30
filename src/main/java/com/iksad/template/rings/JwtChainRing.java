package com.iksad.template.rings;

import com.iksad.template.filter.JwtAuthorizationFilter;
import com.iksad.template.tools.SecurityFilterChainRing;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtChainRing implements SecurityFilterChainRing {
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    @Override
    public void addChainRing(HttpSecurity http) throws Exception {
        // 기본 설정인 Session 방식은 사용하지 않고 JWT 방식을 사용하기 위한 설정
        http.sessionManagement((sessionManagement) ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        // 필터 관리
        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
