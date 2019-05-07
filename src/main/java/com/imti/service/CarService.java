package com.imti.service;

import com.imti.model.Car;

/**
 * created by imteyaza-1lm on 2019-05-06
 **/
public interface CarService {

  Car getCarDetails(String name);

  Car saveCarDetails(Car car);
}
