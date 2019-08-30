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
            //��ȡ�����ļ�mybatis-config.xml
            InputStream cpnfig = Resources.getResourceAsStream("mybatis-config.xml");
            //���������ļ�����SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(cpnfig);
            //ͨ��SqlSessionFactory����SqlSession
            SqlSession session = sqlSessionFactory.openSession();
            //SqlSessionִ��ӳ���ļ��ж����SQL��������ӳ����
            //com.mybatis.mapper.UserMapper.selectUserByIdΪUserMapper.xml�е������ռ�+select��id
//            ��ѯ�û�
            MyUser myUser = session.selectOne("com.mybatis.mapper.UserMapper.selectUserById", 1);
            System.out.println(myUser);
//            ����û�
            MyUser add = new MyUser();
            add.setUname("�����");
            add.setUsex("��");
            session.insert("com.mybatis.mapper.UserMapper.addUser", add);
            //�޸�һ���û�
            MyUser updatemu = new MyUser();
            updatemu.setUid(1);
            updatemu.setUname("����");
            updatemu.setUsex("Ů");
            session.update("com.mybatis.mapper.UserMapper.updateUser", updatemu);
            //ɾ��һ���û�
            session.delete("com.mybatis.mapper.UserMapper.deleteUser", 3);
            //��ѯ�����û�
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
