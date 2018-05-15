package com.mybatis.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: ShengzhiCai
 * @Date: 2018-05-14 22:25
 */

public class AppCxt {


    public static void main(String[] args) {
        AppCxt appCxt = new AppCxt();
        appCxt.startup();
    }

    public void startup() {

        Properties properties = System.getProperties();
        properties.put("env", "std-test");

        String[] xmls = new String[]{
            "spring-context.xml"
        };
        ClassPathXmlApplicationContext xmlCtx = new ClassPathXmlApplicationContext(xmls);
        JdbcTemplate jdbcTemplate = (JdbcTemplate)xmlCtx.getBean("jdbcTemplate");
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList("select * from user");
        if(resultList != null) {
            System.out.println("记录数为:" + resultList.size());
        } else {
            System.out.println("结果为空。。。");
        }






    }


}
