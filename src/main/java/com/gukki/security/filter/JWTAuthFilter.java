package com.gukki.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.gukki.security.entity.SecurityUser;
import com.gukki.util.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Slf4j
//认证
//流程：检查请求头有无认证信息，有->从Token中获取用户信息，获取角色组->组装成一个用户信息类（继承了UserDetails）—>封装成一个认证类->提交至安全上下文。
public class JWTAuthFilter extends BasicAuthenticationFilter {

    public JWTAuthFilter(AuthenticationManager manager) {
        super(manager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("进入了Token认证");
        String header = request.getHeader("Authorization");
        if (null != header && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            //Get name
            String name = JWTUtil.getUserNameFromToken(token);
            //Get ID
            Long ID = JWTUtil.getUserID(token);
            //获取角色
            if (!name.isEmpty() && (ID != null)) {
                try {
                    List<GrantedAuthority> authorityList = new ArrayList<>();
                    String authority = JWTUtil.getAClaim(token, "authorities");
                    if (!authority.isEmpty()) {
                        List<Map<String, String>> authorityMap = JSONObject.parseObject(authority, List.class);
                        for (Map<String, String> map : authorityMap) {
                            if (!map.isEmpty()) {
                                authorityList.add(new SimpleGrantedAuthority(map.get("authority")));
                            }
                        }
                        SecurityUser user = new SecurityUser(ID, name, authorityList);
                        UsernamePasswordAuthenticationToken token1 = new UsernamePasswordAuthenticationToken(user, null, authorityList);
                        SecurityContextHolder.getContext().setAuthentication(token1);
                    }
                } catch (ExpiredJwtException e) {
                    logger.error(e.getMessage());
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
