package com.poly.da.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    private final UserDetailsService khachHangDetailsService;
    private final UserDetailsService nhanVienDetailsService;
    
    private final CustomLoginSuccessHandler customLoginSuccessHandler;

    public SecurityConfig(
            @Qualifier("khachHangDetailsService") UserDetailsService khachHangDetailsService,
            @Qualifier("nhanVienDetailsService") UserDetailsService nhanVienDetailsService,
            CustomLoginSuccessHandler customLoginSuccessHandler) { 
        this.khachHangDetailsService = khachHangDetailsService;
        this.nhanVienDetailsService = nhanVienDetailsService;
        this.customLoginSuccessHandler = customLoginSuccessHandler; 
    }
    
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    

    @Bean
    public AuthenticationProvider khachHangAuthProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(khachHangDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    

    @Bean
    public AuthenticationProvider nhanVienAuthProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(nhanVienDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }


    @Bean
    public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/admin/**") 
            .authenticationProvider(nhanVienAuthProvider()) 
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/login").permitAll()
                .requestMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_NHANVIEN") 
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/admin/login")
                .loginProcessingUrl("/admin/login")
                .successHandler(customLoginSuccessHandler) 
                .failureUrl("/admin/login?error")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/admin/logout")
                .logoutSuccessUrl("/admin/login?logout")
                .permitAll()
            )
            .csrf(csrf -> csrf.disable()); 
            
        return http.build();
    }

  
    @Bean
    public SecurityFilterChain clientFilterChain(HttpSecurity http) throws Exception {
        http
            .authenticationProvider(khachHangAuthProvider()) 
            .authorizeHttpRequests(auth -> auth
            		.requestMatchers("/admin/login").permitAll()
                .requestMatchers("/", "/login", "/register", "/css/**", "/js/**", "/images/**", "/api/**", "/force-change-password", "/update-password").permitAll() 
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login") 
                .loginProcessingUrl("/login")
                .successHandler(customLoginSuccessHandler) 
                .failureUrl("/login?error")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )
            .csrf(csrf -> csrf.disable());

        return http.build();
    }
}