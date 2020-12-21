package quickstart.microservises.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import quickstart.microservises.domain.Car;
import quickstart.microservises.service.CarService;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarController.class)
@Import(CarController.class)
public class CarControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService service;

    @Test
    void findById() throws Exception {
        Car car = createTestCar();

        when(service.findById(1L)).thenReturn(car);

        this.mockMvc.perform(get("/cars/{id}", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(car)));
    }


    @Test
    void create() throws Exception {
        Car car = createTestCar();
        final String json = new ObjectMapper().writeValueAsString(car);

        when(service.create(car)).thenReturn(car);

        this.mockMvc.perform(post("/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void update() throws Exception {
        Car car = createTestCar();
        final String json = new ObjectMapper().writeValueAsString(car);

        when(service.update(1L, car)).thenReturn(car);

        this.mockMvc.perform(put("/cars/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteById() throws Exception {

        this.mockMvc.perform(delete("/cars/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Configuration
    static class TestConfigForSkipLoadJpaRepository {
    }

    public Car createTestCar() {
        Car car = new Car();
        car.setBrand("TestBrand");
        car.setModel("TestModel");

        return car;
    }


}