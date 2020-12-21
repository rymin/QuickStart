package quickstart.microservises.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.h2.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quickstart.microservises.domain.Car;
import quickstart.microservises.exceptions.NotFoundException;
import quickstart.microservises.service.CarService;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Car> carList(){ return carService.getAll(); }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Car getOne(@PathVariable Long id){
        return carService.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Car create(@RequestBody Car car){
        return carService.create(car);
    }

    @PutMapping("{id}")
    public Car update(@PathVariable Long id, @RequestBody Car car){
         return carService.update(id, car);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id){
        carService.remove(id);
    }

    @ExceptionHandler({NotFoundException.class})
    public void handleResourceNotFoundException(HttpServletResponse response) {
        response.setStatus(HttpStatus.PRECONDITION_FAILED.value());
    }

}

