package com.example.JSFW_L_A103.infastructor.security;

import com.example.JSFW_L_A103.constant.RoleConstant;
import com.example.JSFW_L_A103.entity.Member;
import com.example.JSFW_L_A103.service.IMemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private IMemberService memberService;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailServiceCustom();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeHttpRequests()
                .requestMatchers("/", "/login", "/logout", "/register").permitAll()
                .requestMatchers("/authentication/**").permitAll()
                .requestMatchers("/static/**").permitAll()
                .requestMatchers("/member/**").hasAnyAuthority(RoleConstant.ROLE_MEMBER)
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler((request, response, authentication) -> {
                    HttpSession session = request.getSession();
                    UserDetailCustom userDetailCustom = (UserDetailCustom) authentication.getPrincipal();
                    session.setAttribute("user", userDetailCustom.getMember());
                    response.sendRedirect("/member/content");
                })
                .failureUrl("/login?error=Invalid Email or Password!")
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling().accessDeniedPage("/authentication/403");
//                .and()
//                .exceptionHandling()
//                .defaultAuthenticationEntryPointFor((request, response, authException) ->
//                        response.sendRedirect("/authentication/500"), new AntPathRequestMatcher("/**"));
        return http.build();


    }

}
