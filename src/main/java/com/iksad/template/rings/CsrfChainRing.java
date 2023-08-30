package com.iksad.template.rings;

import com.iksad.template.tools.SecurityFilterChainRing;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.stereotype.Component;

@Component
public class CsrfChainRing implements SecurityFilterChainRing {
    @Override
    public void addChainRing(HttpSecurity http) throws Exception {
        // CSRF 설정
        http.csrf(AbstractHttpConfigurer::disable);
    }
}
