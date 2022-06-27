
package com.mycompany.cars;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Driver;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author Jasen Ratnam
 */
public class SpringDBQueryForList {
    public static void main(String[] args) throws IOException,
            ClassNotFoundException {

        var prop = new Properties();
        prop.load(new FileInputStream("src/main/resources/db.properties"));

        var ds = new SimpleDriverDataSource();

        ds.setDriverClass(((Class<Driver>) Class.forName(prop.getProperty("jdbc.driver"))));
        ds.setUrl(prop.getProperty("jdbc.url"));
        ds.setUsername(prop.getProperty("jdbc.username"));
        ds.setPassword(prop.getProperty("jdbc.password"));

        var sql = "SELECT * FROM cars";

        var jtm = new JdbcTemplate(ds);
        var rows = (List<Map<String, Object>>) jtm.queryForList(sql);

        rows.forEach(System.out::println);
    }
}
