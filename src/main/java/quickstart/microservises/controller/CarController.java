package quickstart.microservises.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import quickstart.microservises.domain.Car;
import quickstart.microservises.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("cars")
public class CarController {
    private final AtomicLong counter = new AtomicLong();
    private List<Car> cars = new ArrayList<Car>();


    @GetMapping
    public List<Car> carList(){
        if (cars.size() == 0){
            counter.set(0);
            cars.add(new Car((int)counter.incrementAndGet(), "bmv", "x3"));
        }
        return cars;
    }

    @GetMapping("{id}")
    public Car getOne(@PathVariable String id){
        return getCar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Car create(@RequestBody Map<String, String> req){
        Car car = new Car((int)counter.incrementAndGet(), req.get("brand"), req.get("model"));
        cars.add(car);
        return car;
    }

    @PutMapping("{id}")
    public Car update(@PathVariable String id, @RequestBody Map<String, String> req){
         Car car = getCar(id);
         car.setBrand(req.get("brand"));
         car.setModel(req.get("model"));
         return car;
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable String id){
        return cars.removeIf(car -> car.getId() == Integer.parseInt(id));
    }

    private Car getCar(String id){
        return cars.stream()
                .filter(car -> car.getId() == Integer.parseInt(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
}


