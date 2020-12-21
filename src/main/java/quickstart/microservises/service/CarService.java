package quickstart.microservises.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import quickstart.microservises.domain.Car;
import quickstart.microservises.exceptions.AlreadyExistException;
import quickstart.microservises.exceptions.NotFoundException;
import quickstart.microservises.repository.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Car findById(Long id) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent())
            return car.get();
        else
            throw new NotFoundException();
    }

    public List<Car> getAll() {
        return carRepository.findAll();
    }

    public Car create(Car car) {
        car.setId(null);
        return carRepository.save(car);

    }

    public Car update(Long id, Car car) {
        Car foundedCar = findById(id);
        foundedCar.setBrand(car.getBrand());
        foundedCar.setModel(car.getModel());
        return carRepository.save(foundedCar);
    }

    public void remove(long id) {
        Car foundedCar = findById(id);
        carRepository.delete(foundedCar);
    }

}
