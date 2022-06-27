
package com.mycompany.cars.service;

import com.mycompany.cars.model.Car;
import java.util.List;

public interface ICarService {

    Car findByName(String name);
    List<Car> findAll();
}