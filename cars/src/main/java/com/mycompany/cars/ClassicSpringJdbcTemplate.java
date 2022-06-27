
package com.mycompany.cars;

import com.mycompany.cars.model.Car;
import com.mycompany.cars.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.zetcode")
public class ClassicSpringJdbcTemplate {

    public static void main(String[] args) {

        var ctx = new AnnotationConfigApplicationContext(ClassicSpringJdbcTemplate.class);
        var app = ctx.getBean(ClassicSpringJdbcTemplate.class);

        app.run();

        ctx.close();
    }

    @Autowired
    private ICarService carService;

    private void run() {

        /*
        System.out.println("Fetching a car with Id 3");
        Long id = 3L;
        var car = (Car) carService.findById(id);
        System.out.println(car);

        System.out.println("Fetching all cars");
        var cars = carService.all();
        cars.forEach(System.out::println);
        */
    }
}