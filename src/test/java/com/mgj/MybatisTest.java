package com.mgj;

import com.mgj.entity.Users;
import com.mgj.mapper.UsersMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {

    private InputStream inputStream;
    private SqlSession sqlSession;
    private UsersMapper usersMapper;

    @Before
    public void init() throws IOException {
        //读取配置文件
        inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //使用工厂对象来生产SqlSession对象
        sqlSession = sqlSessionFactory.openSession();
        //使用SqlSession来创建一个UsersMapper的代理对象
        usersMapper = sqlSession.getMapper(UsersMapper.class);
    }

    @After
    public void destroy() throws Exception{
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
        inputStream.close();
    }

    //对查询所有用户的测试
    @Test
    public void findAllUser() {
        //使用代理对象来执行方法
        List<Users> list = usersMapper.findAllUser();
        for(Users users : list){
            System.out.println(users);
        }
    }

    @Test
    public void saveUser(){
        Users user=new Users();
        user.setUsercode("马广交");
        user.setUsername("10018");
        user.setDepartment("二开中心");
        System.out.println("保存之前------"+user);
        //执行保存的方法
        usersMapper.saveUser(user);
        System.out.println("保存之后------"+user);

    }

    //对根据username删除用户的测试
    @Test
    public void delUserByUsercode() throws IOException {
        //使用代理对象来执行方法
        usersMapper.delUserByUsercode("10012");
    }

}
