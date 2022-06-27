
package com.mycompany.cars.repository;

import com.mycompany.cars.model.Car;
import java.util.List;

/**
 *
 * @author Jasen Ratnam
 */
public interface ICarRepository {

    void saveCar(Car car);
    Car findCarByName(String name);
    List<Car> findAll();
}