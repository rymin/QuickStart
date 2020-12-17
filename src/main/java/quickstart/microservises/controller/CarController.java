package quickstart.microservises.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quickstart.microservises.domain.Car;
import quickstart.microservises.service.CarService;

@RestController
@RequestMapping("cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<?> carList(){
        return carService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        return carService.get(id);
    }

    @PostMapping("/car")
    public ResponseEntity<?> create(@RequestBody Car car){
        return carService.create(car);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Car car){
         return carService.update(id, car);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        return carService.remove(id);
    }

}


