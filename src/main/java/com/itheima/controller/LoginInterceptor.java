package com.itheima.controller;

import com.itheima.service.SecretKeyService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.stream.Stream;

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private SecretKeyService secretKeyService;

    @Value("${interceptor.mode}")
    private final InterceptorMode interceptorMode = InterceptorMode.NONE;

    @Value("${interceptor.whiteList}")
    private String[] interceptorWhiteList;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if ("OPTIONS".equals(request.getMethod()) ||
                Stream.of(interceptorWhiteList).anyMatch(p -> p.equals(request.getRequestURI()))) {
            return true;
        }
        Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            System.out.println(name + " " + request.getHeader(name));
        }
        switch (interceptorMode) {
            case JWT : {
                String token = request.getHeader("Authorization");
                if (token == null) {
                    throw new Exception401("未携带 token");
                }
                try {
                    SecretKey secretKey = secretKeyService.getSecretKey();
                    Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
                } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException |
                         IllegalArgumentException e) {
                    throw new Exception401("校验 token 失败");
                }
            }
            case SESSION : {
                Object user = request.getSession().getAttribute("user");
                if (user == null) {
                    throw new Exception401("校验 session 失败");
                }
            }
            case NONE : {
            }
        }
        return true;
    }

    enum InterceptorMode {
        NONE, JWT, SESSION;
    }
}
