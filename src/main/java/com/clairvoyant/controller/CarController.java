package com.clairvoyant.controller;

import com.clairvoyant.exception.CarNotFoundException;
import com.clairvoyant.model.Car;
import com.clairvoyant.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by imteyaza-1lm on 2019-05-06
 **/
@RestController
public class CarController {

  private final CarService carService;

  @Autowired
  public CarController(CarService carService) {
    this.carService = carService;
  }

  @GetMapping(path = "/cars/{name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  private Car getCarDetails(@PathVariable String name) {
    return carService.getCarDetails(name);
  }


  @PostMapping(path = "/cars", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  private Car getCarDetails(@RequestBody Car car) {
    return carService.saveCarDetails(car);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.NOT_FOUND)
  private void carNotFoundExceptionHandler(CarNotFoundException carNotFoundException) {

  }
}
