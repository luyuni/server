package com.itheima.controller;

import com.itheima.dto.R;
import io.jsonwebtoken.lang.Maps;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    List<Map<String, String>> list = Arrays.asList(
            Maps.of("name", "张三").and("age", "12").build(),
            Maps.of("name", "李四").and("age", "13").build(),
            Maps.of("name", "王五").and("age", "14").build()
    );

    int i = 0;

    @GetMapping("/api/user")
    public R user() throws InterruptedException {
        Thread.sleep(1000);
        return R.ok(list.get(i++ % 3));
    }
}
