package com.example.AdminPanelV2.config;

import com.example.AdminPanelV2.services.CustomUserDetailsService;
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
        customUserDetailsService.creteAdmin("Roman", "sup.makulin@mail.ru", 25, "123", "https://static-cdn4-2.vigbo.tech/u19297/22269/blog/4426958/5938479/78187796/1000-Ekaterina_Nasyrova-cd6df61497aa632ed6447b4f502104e0.JPG");
        return customUserDetailsService;
    }

    /**
     * Настройка фильтра авторизаций
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/css/**", "/favicon.ico", "/login").permitAll()
                        .requestMatchers("/registration", "/", "/api/**", "/registration-user").anonymous()
                        .requestMatchers("/user/**").hasAnyRole("USER")
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                        .requestMatchers("/loginUser").authenticated()
                )
                .formLogin(login -> login
                        .defaultSuccessUrl("/loginUser", true).permitAll())
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
