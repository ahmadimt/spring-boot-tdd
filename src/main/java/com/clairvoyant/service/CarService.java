package com.clairvoyant.service;

import com.clairvoyant.model.Car;

/**
 * created by imteyaza-1lm on 2019-05-06
 **/
public interface CarService {

  Car getCarDetails(String name);

  Car saveCarDetails(Car car);
}
