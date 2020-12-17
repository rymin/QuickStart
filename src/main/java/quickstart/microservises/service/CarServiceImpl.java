package quickstart.microservises.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quickstart.microservises.domain.Car;
import quickstart.microservises.exceptions.AlreadyExistException;
import quickstart.microservises.exceptions.NotFoundException;
import quickstart.microservises.repository.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService{

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAll(){
        return carRepository.findAll();
    }

    public Car get(long id){
        Optional<Car> car = carRepository.findById(id);

        if(car.isPresent()){
            return car.get();
        }else{
            throw new NotFoundException();
        }
    }

    public Car add(Car car){
        Optional<Car> exist = carRepository.findById(car.getId());

        if (exist.isPresent()){
            throw new AlreadyExistException();
        } else{
            return carRepository.save(car);
        }
    }

    public Car update(Car car){
        Optional<Car> exist = carRepository.findById(car.getId());

        if(exist.isPresent()){
            return carRepository.save(car);
        } else{
            throw new NotFoundException();
        }
    }

    public void remove(long id){
        carRepository.deleteById(id);
    }


}
