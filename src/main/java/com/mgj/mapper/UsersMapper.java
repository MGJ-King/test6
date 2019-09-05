package com.mgj.mapper;

import com.mgj.entity.Users;

import java.util.List;

public interface UsersMapper {
    //查询所有的users
    List<Users> findAllUser();

    //保存users
    void saveUser(Users user);

    //根据id删除users
    void delUserByUsercode(String usercode);
}
