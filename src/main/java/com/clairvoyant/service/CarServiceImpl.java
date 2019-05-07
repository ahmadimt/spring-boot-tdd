package com.clairvoyant.service;

import com.clairvoyant.exception.CarNotFoundException;
import com.clairvoyant.model.Car;
import com.clairvoyant.repo.CarRepository;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * created by imteyaza-1lm on 2019-05-06
 **/
@Service
public class CarServiceImpl implements CarService {

  private final CarRepository carRepository;

  public CarServiceImpl(CarRepository carRepository) {

    this.carRepository = carRepository;
  }

  @Override
  public Car getCarDetails(String name) {
    Car car = carRepository.findByName(name);
    if (Objects.isNull(car)) {
      throw new CarNotFoundException();
    }
    return car;
  }

  @Override
  public Car saveCarDetails(Car car) {
    return carRepository.save(car);
  }
}
