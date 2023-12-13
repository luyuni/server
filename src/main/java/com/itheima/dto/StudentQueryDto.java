package com.itheima.dto;

import lombok.Data;

@Data
public class StudentQueryDto {
    private String name;
    private String sex;
    private Integer[] age;
    private int page;
    private int size;

    public StudentQueryDto(
            // 对于年龄 1,19    =>   [1, 19]
            String name, String sex, Integer[] age,
            int page, int size) {

    }
    public int offset() {
        return (page - 1) * size;
    }

    public int limit() {
        return size;
    }
}
