package quickstart.microservises.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import quickstart.microservises.domain.Car;

import java.util.Optional;

@SpringBootTest
class CarRepositoryTest {

    @Autowired
    CarRepository repository;

    @Test
    void findById(){
        Optional<Car> car = repository.findById(10L);
        Assertions.assertTrue(car.isPresent());
        Assertions.assertEquals(car.get().getBrand(), "bmw");
    }
}
