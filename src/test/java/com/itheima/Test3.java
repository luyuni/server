package com.itheima;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Test3 {

    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student(1, "张三", 80, "1班"),
                new Student(2, "李四", 88, "1班"),
                new Student(4, "赵六", 90, "2班")
        );
        students.stream()
                .collect(Collectors.groupingBy(
                        Student::getClazz,
                        Collectors.toCollection(TreeSet::new)
                )).forEach((k,v)->{
                    System.out.println(k + " " + v);
                });
    }

    static class Student implements Comparable<Student> {
        private int id;
        private String name;
        private double score;
        private String clazz;

        public Student(int id, String name, double score, String clazz) {
            this.id = id;
            this.name = name;
            this.score = score;
            this.clazz = clazz;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getClazz() {
            return clazz;
        }

        public void setClazz(String clazz) {
            this.clazz = clazz;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", score=" + score +
                    '}';
        }

        @Override
        public int compareTo(Student o) {
            return Integer.compare(this.id, o.getId());
        }
    }

    static class User {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

    static class A {
        @Autowired
        private B b;

        public B getB() {
            return b;
        }
    }

    static class B {
        @Autowired
        private A a;

        public A getA() {
            return a;
        }
    }

}

class ApplicationContext {
    private final Map<Class<?>, Object> cache1 = new HashMap<>();
    private final Map<Class<?>, Object> cache2 = new HashMap<>();

    public <T> T getBeanFromCache(Class<T> clazz) {
        Object bean = cache1.get(clazz);
        if (bean == null) {
            bean = cache2.get(clazz);
        }
        return clazz.cast(bean);
    }

    public <T> T getBean(Class<T> clazz) {
        T bean = getBeanFromCache(clazz);
        if (bean != null) {
            return bean;
        }
        try {
            // 创建对象
            Constructor<T> cons = clazz.getDeclaredConstructor();
            bean = cons.newInstance();
            cache2.put(clazz, bean);
            // 依赖注入
            inject(clazz, bean);
            cache2.remove(clazz);
            // 成品放入一级缓存
            cache1.put(clazz, bean);
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private <T> void inject(Class<T> clazz, T bean) {
        Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Autowired.class))
                .forEach(f -> {
                    // 根据 field 类型, 获取依赖对象并注入
                    Class<?> type = f.getType();
                    Object dependency = this.getBean(type);
                    f.setAccessible(true);
                    try {
                        f.set(bean, dependency);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}



