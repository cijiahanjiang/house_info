package edu.bupt.crawler.house.info.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * liujun 2018/5/4 下午4:36
 * liujun36@maoyan.com
 **/
@RestController
public class TestDB {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/test/db")
    public String test() {

        String sql = "select * from house_info";
        System.out.println(jdbcTemplate.queryForList(sql).size());
        return "Sa";
    }
}
