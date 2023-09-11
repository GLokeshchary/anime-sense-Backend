package com.animesense.productservice.config;
import com.animesense.productservice.filter.JwtAuthFilter;
import com.animesense.productservice.service.CustomUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig implements WebMvcConfigurer {

    @Autowired
    private JwtAuthFilter authFilter;

    @Bean
    public UserDetailsService  userDetailsService(){
        return new CustomUserDetailsService();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf().disable()
                .cors().configurationSource(new
                                                    CorsConfigurationSource() {
                                                        @Override
                                                        public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                                                            CorsConfiguration cfg=new CorsConfiguration();
                                                            cfg.setAllowedOrigins(Arrays.asList(
                                                                    "http://localhost:3000","https://anime-sense.vercel.app"
                                                            ));
                                                            cfg.setAllowedMethods(Collections.singletonList("*"));
                                                            cfg.setAllowCredentials(true);
                                                            cfg.setAllowedHeaders(Collections.singletonList("*"));
                                                            cfg.setExposedHeaders(Arrays.asList("Authorization"));
                                                            cfg.setMaxAge(3600L);
                                                            return cfg;
                                                        }
                                                    }).and()
                .authorizeHttpRequests()
                .requestMatchers("/auth/**").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/reviews/**").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/product/**").permitAll().and()
                .authorizeHttpRequests().requestMatchers("/orders/**")
                .authenticated().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)

                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
