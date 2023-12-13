package com.itheima.controller;

import com.itheima.dto.LoginDto;
import com.itheima.dto.R;
import com.itheima.dto.UserInfoDto;
import com.itheima.service.SecretKeyService;
import com.itheima.service.UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private SecretKeyService secretKeyService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/loginJwt")
    public R loginJwt(@RequestBody LoginDto dto) {
        userService.login(dto.getUsername(), dto.getPassword());
        SecretKey secretKey = secretKeyService.getSecretKey();
        String token = Jwts.builder().signWith(secretKey).setSubject(dto.getUsername()).compact();
        Map<String, String> ret = new HashMap<>();
        ret.put("token", token);
        return R.ok(ret);
    }

    @PostMapping("/api/loginSession")
    public R loginSession(@RequestBody LoginDto dto, HttpSession session) {
        userService.login(dto.getUsername(), dto.getPassword());
        session.setAttribute("user", dto.getUsername());
        return R.ok("登录成功");
    }

    @GetMapping("/api/info/{username}")
    public R findUserInfo(@PathVariable String username) {
        return R.ok(userService.findUserInfo(username));
    }

    @PostMapping("/api/info")
    public R updateUserInfo(@RequestBody UserInfoDto dto) {
        userService.updateUserInfo(dto);
        return R.ok("用户信息更新成功");
    }
}
