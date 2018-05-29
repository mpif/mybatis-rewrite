package com.mybatis.test;

import com.mybatis.rewrite.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: ShengzhiCai
 * @Date: 2018-05-29 21:51
 */

public class MybatisStartup {

//    private Logger slf4jLog = LoggerFactory.getLogger(MybatisStartup.class);
    private org.apache.logging.log4j.Logger log4j2Log = LogManager.getLogger(MybatisStartup.class);

    public static void main(String[] args) {
        MybatisStartup mybatisStartup = new MybatisStartup();
        mybatisStartup.startup();
    }

    public void startup() {
        SqlSession openSession = null;
        try {


            log4j2Log.info("dfdfdfdf");

            SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession();
            User user = openSession.selectOne(
                    "com.mybatis.rewrite.dao.UserDao.queryUser", 1L);
            System.out.println(user);

            System.out.println(user.getId());



        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            openSession.close();
        }
    }

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }


}
