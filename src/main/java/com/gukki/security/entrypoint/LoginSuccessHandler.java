package com.gukki.security.entrypoint;

import com.gukki.security.entity.SecurityUser;
import com.gukki.util.JWTUtil;
import com.gukki.util.ResUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Component
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        log.info(user.toString());
        String token = JWTUtil.genKey(user);
        HashMap<String, Object> map = new HashMap<>();
        map.put("token",token);
        ResUtil.ResponseJSON(response,map);
    }
}
