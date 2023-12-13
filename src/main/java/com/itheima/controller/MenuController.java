package com.itheima.controller;

import com.itheima.dto.R;
import com.itheima.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/api/menu")
    public R findAll() {
        return R.ok(menuService.findAll());
    }

    @GetMapping("/api/menu/{username}")
    public R findBy(@PathVariable String username) {
        return R.ok(menuService.findByUser(username));
    }
}
