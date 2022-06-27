
package com.mycompany.cars.service;

import com.mycompany.cars.model.Car;
import com.mycompany.cars.repository.ICarRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CarService implements ICarService {

    @Autowired
    private ICarRepository carRepository;

    public Car findByName(String name) {

        return carRepository.findCarByName(name);
    }

    public List<Car> findAll() {

        return carRepository.findAll();
    }
}