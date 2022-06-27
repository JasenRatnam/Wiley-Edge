
package com.mycompany.cars;

import com.mycompany.cars.model.Car;
//import com.mysql.jdbc.Driver;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

/**
 *
 * @author Jasen Ratnam
 */
public class SpringDBNamedParameters {
    public static void main(String[] args) throws IOException,
            ClassNotFoundException {

        var prop = new Properties();
        prop.load(new FileInputStream("src/main/resources/db.properties"));

        var ds = new SimpleDriverDataSource();
        //ds.setDriverClass(((Class<Driver>) Class.forName(prop.getProperty("jdbc.driver"))));
        ds.setUrl(prop.getProperty("jdbc.url"));
        ds.setUsername(prop.getProperty("jdbc.username"));
        ds.setPassword(prop.getProperty("jdbc.password"));

        var sql = "SELECT * FROM cars WHERE name LIKE :name";
        var carName = "Volvo";

        var jtm = new NamedParameterJdbcTemplate(ds);

        var namedParams = new MapSqlParameterSource("name", carName);
        var car = (Car) jtm.queryForObject(sql, namedParams,
                BeanPropertyRowMapper.newInstance(Car.class));

        System.out.println(car);
    }
}
