package com.iksad.template.rings;

import com.iksad.template.tools.SecurityFilterChainRing;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

@Component
public class AuthorizeHttpRequestsChainRing implements SecurityFilterChainRing {
    @Override
    public void addChainRing(HttpSecurity http) throws Exception {
        // 인가 권한 설정
        http.authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests
                        .requestMatchers("/api/auth/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/posts/**").permitAll()

                        .anyRequest().authenticated()
        );
    }
}
