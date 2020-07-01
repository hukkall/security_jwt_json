package com.gukki.security.entrypoint;

import com.gukki.util.ResUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if(exception instanceof UsernameNotFoundException){
            ResUtil.ResponseJSON(response,ResUtil.CustomResult(500,"UserNameNotFound"));
            log.error(exception.getMessage());
        }else if(exception instanceof BadCredentialsException){
            ResUtil.ResponseJSON(response,ResUtil.CustomResult(500,"Password Error"));
            log.error(exception.getMessage());
        }else {
            ResUtil.Fail();
            log.error(exception.getMessage());
        }
    }
}
