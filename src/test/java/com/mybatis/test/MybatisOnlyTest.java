package com.mybatis.test;

import com.mybatis.rewrite.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: caishengzhi
 * @date: 2018-05-15 16:43
 */
public class MybatisOnlyTest {

    SqlSession session = null;
    Logger logger = null;

    @Before
    public void before() {
        try {

            System.setProperty("log4j.configurationFile", "classpath:log4j2.xml");

            logger = LogManager.getLogger(MybatisOnlyTest.class);

            Log log = LogFactory.getLog(MybatisOnlyTest.class);

            if(log.isDebugEnabled()) {
                log.debug("mybatis日志");
                System.out.println("mybatis日志system out println........");
            }

            String resource = "mybatis-only.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            session = sqlSessionFactory.openSession();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectAll() {

        try {

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("id", 1L);
            List<User> userlist = session.selectList("com.mybatis.rewrite.dao.UserDao.queryUser", paramMap);

            System.out.println("结果为:");
            System.out.println(userlist);

        }catch(Exception e){
            System.out.print(e);
        } finally {
            session.close();
        }

    }



    @Test
    public void testInsert() {

        try {

            User user = new User();
            user.setName("李四");
            user.setAge(13);
            int insertCount = session.insert("com.mybatis.rewrite.dao.UserDao.insertUser", user);
            System.out.println("insertCount=" + insertCount + ", id=" + user.getId());

        }catch(Exception e){
            System.out.print(e);
        } finally {
            session.close();
        }

    }




}
