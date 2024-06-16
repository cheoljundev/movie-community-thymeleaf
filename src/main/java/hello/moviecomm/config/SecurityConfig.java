package hello.moviecomm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity()
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "join").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole(  "USER", "ADMIN")
                        .anyRequest().authenticated()
                );
        http.formLogin(form -> form // 폼 로그인 방식
                .loginPage("/login") //  로그인 페이지
                .loginProcessingUrl("/login") // 로그인 처리 페이지
                .permitAll() // 로그인 페이지는 누구나 접근 가능
        );
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
