package quickstart.microservises.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import quickstart.microservises.domain.Car;
import quickstart.microservises.service.CarServiceImpl;

import java.util.List;

@RestController
@RequestMapping("cars")
public class CarController {

    @Autowired
    private CarServiceImpl carService;

    @GetMapping
    public List<Car> carList(){
        return carService.getAll();
    }

    @GetMapping("{id}")
    public Car getOne(@PathVariable long id){
        return carService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Car create(@RequestBody Car car){
        return carService.add(car);
    }

    @PutMapping("{id}")
    public Car update(@RequestBody Car car){
         return carService.update(car);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id){
        carService.remove(id);
    }

}


