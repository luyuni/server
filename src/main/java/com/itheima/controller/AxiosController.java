package com.itheima.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class AxiosController {

    @GetMapping("/api/a1")
    public String a1() {
        return "get request";
    }

    @PostMapping("/api/a2")
    public String a2() {
        return "post request";
    }

    @PostMapping("/api/a3")
    public String a3(@RequestHeader("Authorization") String authorization) {
        System.out.println("authorization 头 " + authorization);
        return "post request";
    }

    @PostMapping("/api/a4")
    public String a4(String name, Integer age) {
        System.out.println("name: " + name + " age:" + age);
        return "post request";
    }

    @PostMapping("/api/a5")
    public String a5(A5 a5) {
        System.out.println(a5);
        return "post request";
    }

    @PostMapping("/api/a5json")
    public String a5json(@RequestBody A5 a5) {
        System.out.println(a5);
        return "post request";
    }

    @Data
    class A5 {
        private String name;
        private Integer age;
        public A5(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
    }

    @PostMapping("/api/a6set")
    public String a6set(HttpSession session) {
        System.out.println("========== a6 set ==========");
        System.out.println(session.getId());
        session.setAttribute("name", "张三");
        return "post request";
    }

    @PostMapping("/api/a6get")
    public String a6get(HttpSession session) {
        System.out.println("========== a6 get ==========");
        System.out.println(session.getId());
        System.out.println("name: " + session.getAttribute("name"));
        return "post request";
    }


}
