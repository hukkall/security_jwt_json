package com.gukki.security.filter;

import com.alibaba.fastjson.JSON;
import com.gukki.security.entrypoint.AccessDenied;
import com.gukki.security.entrypoint.LoginFailureHandler;
import com.gukki.security.entrypoint.LoginSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;

@Component
@Slf4j
public class JSONAuthFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Autowired
    LoginSuccessHandler successHandler;
    @Autowired
    LoginFailureHandler failureHandler;
    @Autowired
    AccessDenied accessDenied;
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("进入了JSON登陆许可校验");
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE) || request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            UsernamePasswordAuthenticationToken token = null;
            try(InputStream inputStream = request.getInputStream()) {
                Map<String,String> map = JSON.parseObject(inputStream, Charset.defaultCharset(),Map.class);
                token = new UsernamePasswordAuthenticationToken(map.get("username"),map.get("password"));
                setFilterProcessesUrl("/login");
                setAuthenticationFailureHandler(failureHandler);
                setAuthenticationSuccessHandler(successHandler);
            } catch (IOException e) {
                logger.error(e.getMessage());
                token = new UsernamePasswordAuthenticationToken("","");
            }finally {
                setDetails(request,token);
                return this.getAuthenticationManager().authenticate(token);
            }
        }
        else{
            return super.attemptAuthentication(request,response);
        }
    }
}
