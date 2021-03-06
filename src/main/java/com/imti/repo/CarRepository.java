package com.imti.repo;

import com.imti.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by imteyaza-1lm on 2019-05-06
 **/
public interface CarRepository extends JpaRepository<Car, Long> {

  Car findByName(String name);
}
