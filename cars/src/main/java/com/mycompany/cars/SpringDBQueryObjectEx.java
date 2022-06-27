
package com.mycompany.cars;

import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

/**
 *
 * @author Jasen Ratnam
 */
public class SpringDBQueryObjectEx {
    public static void main(String[] args) throws SQLException {

        var ds = new SimpleDriverDataSource();
        //ds.setDriver(new com.mysql.jdbc.Driver());
        ds.setUrl("jdbc:mysql://localhost:3306/testdb");
        ds.setUsername("user7");
        ds.setPassword("s$cret");

        var sql = "SELECT COUNT(*) FROM cars";

        var jtm = new JdbcTemplate(ds);
        int numOfCars = jtm.queryForObject(sql, Integer.class);

        System.out.format("There are %d cars in the table", numOfCars);
    }

}
