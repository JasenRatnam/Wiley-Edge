
package com.mycompany.cars;

import com.mycompany.cars.model.Car;
import com.mycompany.cars.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private ICarService carService;

    @Override
    public void run(String... args) {

        try {
            var car = carService.findByName("Citroen");
            System.out.println(car);

        } catch (EmptyResultDataAccessException e) {
            System.out.println("Car was not found");
        }

        var cars = carService.findAll();

        for (Car car: cars) {
            System.out.println(car);
        }
    }
}