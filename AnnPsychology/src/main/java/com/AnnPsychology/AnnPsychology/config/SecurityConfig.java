package com.AnnPsychology.AnnPsychology.config;

import com.AnnPsychology.AnnPsychology.services.UserPageService.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    /**
     * Сервис управления пользователями
     */
    private final CustomUserDetailsService customUserDetailsService;

    /**
     * Делаем UserDetailsService кастомным
     *
     * @return кастомный UserDetailsService
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return customUserDetailsService;
    }

    /**
     * Настройка фильтра авторизаций
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers("/css/**", "/favicon.ico", "/login").permitAll()
                        .requestMatchers("/registration/**", "/").anonymous()
                        .requestMatchers("/user/**").hasAnyRole("USER")
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                        .requestMatchers("/loginUser").authenticated()
                )
                .formLogin(login -> login
                        .defaultSuccessUrl("/user", true).permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/"));
        return http.build();
    }

    /**
     * Настройка кодирования пароля
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Кодирование пароля
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
