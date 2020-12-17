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

    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(carRepository.findAll());
    }


    public ResponseEntity<?> get(long id){
        Optional<Car> entity = carRepository.findById(id);

        if(entity.isPresent()){
            return ResponseEntity.status(HttpStatus.FOUND).body(entity);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new NotFoundException().getMessage());
        }
    }

    public ResponseEntity<?> create(Car car){
        car.setId(null);
        Car createdCar = carRepository.save(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCar);
    }

    public ResponseEntity<?> update(Long id, Car car){
        Optional<Car> entity = carRepository.findById(id);

        if(entity.isPresent()){
            entity.get().setModel(car.getModel());
            entity.get().setBrand(car.getBrand());
            Car updatedCar = carRepository.save(entity.get());
            return ResponseEntity.status(HttpStatus.OK).body(updatedCar);
        } else{
            return ResponseEntity.status(HttpStatus.OK).body(new NotFoundException().getMessage());
        }
    }

    public ResponseEntity<?> remove(long id){
        Optional<Car> entity = carRepository.findById(id);
        if(entity.isPresent()){
            carRepository.delete(entity.get());
            return ResponseEntity.status(HttpStatus.OK).body(id);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new NotFoundException().getMessage());
        }
    }

}
