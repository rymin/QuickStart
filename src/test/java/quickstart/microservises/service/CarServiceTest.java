package quickstart.microservises.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import quickstart.microservises.domain.Car;
import quickstart.microservises.exceptions.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class CarServiceTest {

    @TestConfiguration
    static class CarServiceTestConfig{
        @Bean
        public CarService carServiceTest(){
            return new CarService();
        }
    }

    @Autowired
    public CarService service;

    @Test
    public void create() {
        Car createdCar = createTestCar();

        Assert.assertNotEquals(java.util.Optional.ofNullable(createdCar.getId()), 0);
        Assert.assertEquals(createdCar.getBrand(), "TestBrand");
        Assert.assertEquals(createdCar.getModel(), "TestModel");

    }

    @Test
    public void getAll() {
        createTestCar();
        Assert.assertTrue(service.getAll().size()>0);
    }

    @Test
    public void getById() throws NotFoundException {
        Car createdCar = createTestCar();

        Car car = service.findById(createdCar.getId());
        Assert.assertEquals(car.getId(), createdCar.getId());
        Assert.assertEquals(car.getBrand(), "TestBrand");
        Assert.assertEquals(car.getModel(), "TestModel");
    }

    @Test
    public void update() throws NotFoundException {
        Car createdCar = createTestCar();

        Car car = new Car();
        car.setBrand("NewBrand");
        car.setModel("NewModel");

        Car updatedCar = service.update(createdCar.getId(), car);
        Assert.assertEquals(updatedCar.getId(), createdCar.getId());
        Assert.assertEquals(updatedCar.getBrand(), "NewBrand");
        Assert.assertEquals(updatedCar.getModel(), "NewModel");
    }

    @Test(expected = NotFoundException.class)
    public void remove() {
        Car createdCar = createTestCar();

        service.remove(createdCar.getId());
        service.findById(createdCar.getId());
    }

    public Car createTestCar() {
        Car car = new Car();
        car.setBrand("TestBrand");
        car.setModel("TestModel");

        return service.create(car);
    }
}