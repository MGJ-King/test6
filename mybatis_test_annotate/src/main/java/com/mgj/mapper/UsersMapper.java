package com.mgj.mapper;

import com.mgj.entity.Users;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UsersMapper {

    @Select("select * from users")
    List<Users> findAllUser();

}
