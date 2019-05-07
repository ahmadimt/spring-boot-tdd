package com.imti.service;

import com.imti.exception.CarNotFoundException;
import com.imti.model.Car;
import com.imti.repo.CarRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * created by imteyaza-1lm on 2019-05-06
 **/
@RunWith(SpringRunner.class)
public class CarServiceTest {

  @TestConfiguration
  static class TestConfig {

    @Bean
    public CarService carService(CarRepository carRepository) {
      return new CarServiceImpl(carRepository);
    }
  }

  @Autowired
  private CarService carService;

  @MockBean
  private CarRepository carRepository;

  @Test
  public void shouldReturnCarDetails() {
    BDDMockito.given(carRepository.findByName(ArgumentMatchers.anyString()))
        .willReturn(new Car("bmw", "hybrid"));
    Car details = carService.getCarDetails("bmw");
    Assertions.assertThat(details.getName()).isEqualTo("bmw");
    Assertions.assertThat(details.getType()).isEqualTo("hybrid");
  }


  @Test
  public void shouldSaveCarDetails(){
    BDDMockito.given(carRepository.save(ArgumentMatchers.any(Car.class)))
        .willReturn(new Car("bmw", "hybrid"));
    Car carDetails = carService.saveCarDetails(new Car("bmw", "hybrid"));
    Assertions.assertThat(carDetails.getName()).isEqualTo("bmw");
    Assertions.assertThat(carDetails.getType()).isEqualTo("hybrid");
  }

  @Test
  public void shouldThrowCarNotFoundException() {
    BDDMockito.given(carRepository.findByName("bmw")).willReturn(null);

    Assertions.assertThat(Assertions.catchThrowable(() -> carService.getCarDetails("bmw")))
        .isInstanceOf(CarNotFoundException.class)
        .hasNoCause();
  }
}
