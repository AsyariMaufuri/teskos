package com.example.TrollMarket.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class MvcSecurityConfiguration  {
    @Bean
    //@Order(2)
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(request -> request
                        .requestMatchers("/resources/**","/css/**", "/js/**", "/fonts/**", "/image/**", "/account/**", "/account/login-form", "/account/login-index").permitAll()
                        .requestMatchers("/merchandise", "/merchandise/**").hasAuthority("seller")
                        .requestMatchers("/history", "/history/**").hasAuthority("admin")
                        .requestMatchers("/admin", "/admin/**").hasAuthority("admin")
                        .requestMatchers("/shipment", "/shipment/**").hasAuthority("admin")
                        .requestMatchers("/cart", "/cart/**").hasAuthority("buyer")
                        .requestMatchers("/shop", "/shop/**").hasAuthority("buyer")
                        .requestMatchers("/profile").hasAnyAuthority("buyer", "seller")
                .anyRequest().authenticated()
        ).formLogin(login -> login
                .loginPage("/account/login") // controller
                .loginProcessingUrl("/authenticating") // from action login
                .failureUrl("/account/login?error=true")
                .defaultSuccessUrl("/",true)
        ).logout(logout -> logout
                .logoutUrl("/logout") // from button
        ).exceptionHandling(exception -> exception
                .accessDeniedPage("/account/access-denied")
        );
        return httpSecurity.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
