package quickstart.microservises.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import quickstart.microservises.domain.Car;
import quickstart.microservises.exceptions.NotFoundException;
import quickstart.microservises.repository.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Transactional(readOnly = true)
    public Car findById(Long id) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent())
            return car.get();
        else
            throw new NotFoundException();
    }

    @Transactional(readOnly = true)
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Transactional
    public Car create(Car car) {
        car.setId(null);
        return carRepository.save(car);

    }

    @Transactional
    public Car update(Long id, Car car) {
        Car foundedCar = findById(id);
        foundedCar.setBrand(car.getBrand());
        foundedCar.setModel(car.getModel());
        return carRepository.save(foundedCar);
    }

    @Transactional
    public void remove(long id) {
        Car foundedCar = findById(id);
        carRepository.delete(foundedCar);
    }

}
