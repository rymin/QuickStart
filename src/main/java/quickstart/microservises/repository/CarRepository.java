package quickstart.microservises.repository;


import org.springframework.data.repository.CrudRepository;
import quickstart.microservises.domain.Car;

import java.util.List;
import java.util.Optional;


public interface CarRepository extends  CrudRepository<Car,Long>{

}
