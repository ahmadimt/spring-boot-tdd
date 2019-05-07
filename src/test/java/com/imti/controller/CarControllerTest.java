package com.imti.controller;

import com.imti.exception.CarNotFoundException;
import com.imti.model.Car;
import com.imti.service.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * created by imteyaza-1lm on 2019-05-06
 **/
@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CarService carService;

  @Test
  public void shouldGetCarDetails() throws Exception {

    BDDMockito.
        given(carService.getCarDetails(ArgumentMatchers.anyString()))
        .willReturn(new Car("bmw", "hybrid"));

    mockMvc
        .perform(MockMvcRequestBuilders.get("/cars/bmw"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("name").value("bmw"))
        .andExpect(MockMvcResultMatchers.jsonPath("type").value("hybrid"));
  }

  @Test
  public void shouldThrowCarNotFoundException() throws Exception {
    BDDMockito.given(carService.getCarDetails(ArgumentMatchers.anyString()))
        .willThrow(new CarNotFoundException());
    mockMvc
        .perform(MockMvcRequestBuilders.get("/cars/bmw"))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  public void shouldSaveDateInDB() throws Exception {
    BDDMockito.
        given(carService.saveCarDetails(ArgumentMatchers.any(Car.class)))
        .willReturn(new Car("bmw", "hybrid"));
    ObjectMapper mapper = new ObjectMapper();
    Car car = new Car("bmw", "hybrid");
    mockMvc.perform(MockMvcRequestBuilders.post("/cars")
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        .content(mapper.writeValueAsString(car)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("name").value("bmw"))
        .andExpect(MockMvcResultMatchers.jsonPath("type").value("hybrid"));
  }
}
