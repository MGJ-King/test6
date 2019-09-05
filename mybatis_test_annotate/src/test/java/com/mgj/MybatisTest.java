package com.mgj;

import com.mgj.entity.Users;
import com.mgj.mapper.UsersMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {

    @Test
    public void findAllUsers() throws IOException {
        //读取配置文件
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //使用工厂对象来生产SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //使用SqlSession来创建一个UsersMapper的代理对象
        UsersMapper usersMapper = sqlSession.getMapper(UsersMapper.class);
        //使用代理对象来执行方法
        List<Users> list = usersMapper.findAllUser();
        for(Users users : list){
            System.out.println(users);
        }
        //释放资源
        sqlSession.close();
        inputStream.close();
    }

}
