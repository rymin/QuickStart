package quickstart.microservises.repository;


import org.springframework.data.repository.CrudRepository;
import quickstart.microservises.domain.Car;

import java.util.List;


public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findAll();
}
