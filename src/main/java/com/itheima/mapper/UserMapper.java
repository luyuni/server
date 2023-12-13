package com.itheima.mapper;

import com.itheima.dto.UserInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select count(*) from user where username=#{username} and password=#{password}")
    boolean login(@Param("username") String username, @Param("password") String password);

    @Select("select * from user_info where username=#{username}")
    UserInfoDto findUserInfo(String username);

    @Update("update user_info set name=#{name}, sex=#{sex} where username=#{username}")
    void updateUserInfo(UserInfoDto dto);
}
