
package com.mycompany.cars;

import com.mycompany.cars.model.Car;
import java.sql.SQLException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

/**
 *
 * @author Jasen Ratnam
 */
public class SpringBeanPropertyRowMapper {
     public static void main(String[] args) throws SQLException {

        var dataSource = new SimpleDriverDataSource();

        //dataSource.setDriver(new com.mysql.jdbc.Driver());
        dataSource.setUrl("jdbc:mysql://localhost:3306/testdb");
        dataSource.setUsername("user7");
        dataSource.setPassword("s$cret");

        var sql = "SELECT * FROM cars WHERE id=?";
        Long id = 1L;

        var jtm = new JdbcTemplate(dataSource);

        var car = (Car) jtm.queryForObject(sql, new Object[]{id},
                 BeanPropertyRowMapper.newInstance(Car.class));

        System.out.println(car);
    }
}
