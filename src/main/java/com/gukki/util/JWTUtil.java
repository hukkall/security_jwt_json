package com.gukki.util;

import com.alibaba.fastjson.JSON;
import com.gukki.security.entity.SecurityUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;
@Slf4j
@Component
public class JWTUtil implements Serializable {
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    private static String key = "imakeytolong";

    //个人猜测第二个参数意思是指定用什么方法处理这段token
    //换言之就是调用什么方法去获取这段token里面的值
    //由T决定返回类型
    //指定好方法，期望将输入的token 转换为输出值
    /*
        猜测resolve.apply等价于：{claims->传入的函数体}
        即，T为函数的返回类型，Claim为传入的函数类型。
     */
    public static <T> T getClaimFromToken(String token, Function<Claims, T> resolve) {
        Claims claims = getAllClaims(token);
        return resolve.apply(claims);
    }
    public static Long getUserID(String token){
        return Long.valueOf(getClaimFromToken(token,Claims::getId));
    }
    //Get All Claims
    public static Claims getAllClaims(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }
    public static String getAClaim(String token,String claimName){
        return getAllClaims(token).get(claimName).toString();
    }
    //获取主题名，也就是用户名
    public static String getUserNameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //获得token过期时间
    public static Date getExpirationDate(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    //查询token 是否过期
    private static Boolean isExpired(String token) {
        Date date = getExpirationDate(token);
        return date.before(new Date());
    }

    //生成一串token给用户
    public static String genKey(SecurityUser details) {
        return doGenkey(details);
    }

    //生成
    private static String doGenkey(SecurityUser details) {
        System.out.println(details.getId());
        return Jwts.builder()
                .setId(String.valueOf(details.getId()))
                .claim("authorities", JSON.toJSONString(details.getAuthorities()))
                .setSubject(details.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 100))
                .signWith(SignatureAlgorithm.HS256, key).compact();
    }

    //校验
    public static Boolean verifyToken(String token, UserDetails details) {
        final String name = details.getUsername();
        return (name.equals(getUserNameFromToken(token)) && !isExpired(token));
    }
}
