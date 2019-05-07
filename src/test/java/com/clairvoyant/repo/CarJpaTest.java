package com.clairvoyant.repo;

import com.clairvoyant.model.Car;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * created by imteyaza-1lm on 2019-05-07
 **/
@RunWith(SpringRunner.class)
@DataJpaTest
public class CarJpaTest {

  @Autowired
  private TestEntityManager tem;

  @Test
  public void shouldSaveCarWithJpa() {
    Car car = tem.persistFlushFind(new Car("bmw", "hybrid"));
    Assertions.assertThat(car.getId()).isGreaterThan(0);
    Assertions.assertThat(car.getName()).isEqualTo("bmw");
    Assertions.assertThat(car.getType()).isEqualTo("hybrid");
  }
}
