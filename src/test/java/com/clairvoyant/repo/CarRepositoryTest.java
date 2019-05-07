package com.clairvoyant.repo;

import com.clairvoyant.model.Car;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * created by imteyaza-1lm on 2019-05-06
 **/
@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTest {

  @Autowired
  private CarRepository carRepository;

  @Test
  public void shouldReturnCarDetailsWithGivenName() {
    carRepository.save(new Car("bmw", "hybrid"));
    Car car = carRepository.findByName("bmw");
    Assertions.assertThat(car.getName()).isEqualTo("bmw");
  }
}
