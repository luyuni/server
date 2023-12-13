package com.itheima.dto;

import lombok.Data;

// 既用于新增, 也用于更新
@Data
public class StudentSaveDto {
    private String name;
    private String sex;
    private int age;
    public StudentSaveDto(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
}
