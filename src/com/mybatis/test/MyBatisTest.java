package com.mybatis.test;

import com.mybatis.po.MyUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class MyBatisTest {
    public static void main(String[] args) {
        try {
            //读取配置文件mybatis-config.xml
            InputStream cpnfig = Resources.getResourceAsStream("mybatis-config.xml");
            //根据配置文件构建SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(cpnfig);
            //通过SqlSessionFactory创建SqlSession
            SqlSession session = sqlSessionFactory.openSession();
            //SqlSession执行映射文件中定义的SQL，并返回映射结果
            //com.mybatis.mapper.UserMapper.selectUserById为UserMapper.xml中的命名空间+select的id
//            查询用户
            MyUser myUser = session.selectOne("com.mybatis.mapper.UserMapper.selectUserById", 1);
            System.out.println(myUser);
//            添加用户
            MyUser add = new MyUser();
            add.setUname("栾桂明");
            add.setUsex("男");
            session.insert("com.mybatis.mapper.UserMapper.addUser", add);
            //修改一个用户
            MyUser updatemu = new MyUser();
            updatemu.setUid(1);
            updatemu.setUname("张三");
            updatemu.setUsex("女");
            session.update("com.mybatis.mapper.UserMapper.updateUser", updatemu);
            //删除一个用户
            session.delete("com.mybatis.mapper.UserMapper.deleteUser", 3);
            //查询所有用户
            List<MyUser> listMu = session.selectList("com.mybatis.mapper.UserMapper.selectAllUser");
            for (MyUser user : listMu) {
                System.out.println(user);
            }
            session.commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
