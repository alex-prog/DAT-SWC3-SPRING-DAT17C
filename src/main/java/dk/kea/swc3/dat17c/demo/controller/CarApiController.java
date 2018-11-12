package dk.kea.swc3.dat17c.demo.controller;

import dk.kea.swc3.dat17c.demo.CarRepository;
import dk.kea.swc3.dat17c.demo.model.Car;
import dk.kea.swc3.dat17c.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarApiController {

    @Autowired
    private CarRepository carRepo;

    @GetMapping("/car/get/{id}")
    public ResponseEntity<Car> getCar(@PathVariable Long id){
        if(id<=0){
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
        Car c = carRepo.findById(id);
        return new ResponseEntity(c, HttpStatus.OK);
    }

    @DeleteMapping("/car/delete/{id}")
    public ResponseEntity<Car> deleteCar(){
        return new ResponseEntity(null, HttpStatus.FORBIDDEN);
    }
}