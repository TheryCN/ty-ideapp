package com.github.therycn.tyideapp;

import com.github.therycn.tyideapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.config.annotation.web.configuration.
     * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
     * annotation.web.builders.HttpSecurity)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Returns 403 instead of redirect if login KO
        http.exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint());

        http.authorizeRequests()
                .antMatchers("/swagger*", "/webjars/**", "/swagger-resources/**", "/configuration/security/**",
                        "/v2/api-docs")
                .permitAll().antMatchers(HttpMethod.POST, "/users/").permitAll().antMatchers("/login*").permitAll()
                .anyRequest().authenticated().and().logout().permitAll()
                .logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))).and().formLogin()
                .defaultSuccessUrl("/users/").and().httpBasic().and().cors()
                .configurationSource(request -> corsConfig()).and().csrf().disable();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.config.annotation.web.configuration.
     * WebSecurityConfigurerAdapter#userDetailsService()
     */
    @Override
    protected UserDetailsService userDetailsService() {
        return userService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private CorsConfiguration corsConfig() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("*");
        corsConfig.addAllowedMethod(HttpMethod.GET);
        corsConfig.addAllowedMethod(HttpMethod.POST);
        corsConfig.addAllowedMethod(HttpMethod.DELETE);
        corsConfig.addAllowedMethod(HttpMethod.PUT);
        corsConfig.addAllowedMethod(HttpMethod.HEAD);
        corsConfig.addAllowedMethod(HttpMethod.PATCH);
        corsConfig.setMaxAge(1800L);
        corsConfig.addAllowedHeader("*");
        corsConfig.setAllowCredentials(true);

        return corsConfig;
    }

}
