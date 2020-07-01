package com.gukki.config;

import com.gukki.security.entrypoint.AccessDenied;
import com.gukki.security.entrypoint.LoginSuccessHandler;
import com.gukki.security.entrypoint.UnAuthorizedHandler;
import com.gukki.security.evaluator.CustomEvaluator;
import com.gukki.security.filter.JSONAuthFilter;
import com.gukki.security.filter.JWTAuthFilter;
import com.gukki.security.provider.CustomAuthProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AccessDenied accessDenied;
    @Autowired
    UnAuthorizedHandler unAuthorizedHandler;
    @Autowired
    CustomAuthProvider provider;
    @Autowired
    JSONAuthFilter filter;
    @Autowired
    CustomEvaluator evaluator;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(evaluator);
        web.expressionHandler(handler);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(unAuthorizedHandler)
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDenied)
                .and()
                .cors()
                .and()
                .csrf()
                .disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().cacheControl();
        http.addFilterAt(filter, UsernamePasswordAuthenticationFilter.class);
        http.addFilter(new JWTAuthFilter(authenticationManager()));
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
