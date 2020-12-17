package quickstart.microservises.service;

import quickstart.microservises.domain.Car;

import java.util.List;

public interface CarService {

    public List<Car> getAll();

    public Car get(long id);

    public Car add(Car car);

    public Car update(Car car);

    public void remove(long id);

}
