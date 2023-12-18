package com.itheima.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 既用于新增, 也用于更新
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentSaveDto {
    private String name;
    private String sex;
    private int age;
}
