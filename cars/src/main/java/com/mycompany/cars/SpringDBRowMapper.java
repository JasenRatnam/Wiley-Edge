
package com.mycompany.cars;

import com.mycompany.cars.model.Car;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

/**
 *
 * @author Jasen Ratnam
 */
public class SpringDBRowMapper {
    public static void main(String[] args) throws SQLException {

        var ds = new SimpleDriverDataSource();
        //ds.setDriver(new com.mysql.jdbc.Driver());
        ds.setUrl("jdbc:mysql://localhost:3306/testdb");
        ds.setUsername("user7");
        ds.setPassword("s$cret");

        var rm = (RowMapper<Car>) (ResultSet result, int rowNum) -> {

            var car = new Car();

            car.setId(result.getLong("id"));
            car.setName(result.getString("name"));
            car.setPrice(result.getInt("price"));

            return car;
        };

        var sql = "SELECT * FROM cars WHERE id=?";
        Long id = 1L;

        var jtm = new JdbcTemplate(ds);
        var car = (Car) jtm.queryForObject(sql, new Object[]{id}, rm);

        System.out.println(car);
    }
}
