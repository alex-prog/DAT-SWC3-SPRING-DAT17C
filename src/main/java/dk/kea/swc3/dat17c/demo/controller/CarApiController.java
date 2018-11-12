package dk.kea.swc3.dat17c.demo.controller;

import dk.kea.swc3.dat17c.demo.CarRepository;
import dk.kea.swc3.dat17c.demo.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CarApiController {
	@Autowired
	private CarRepository carRepository;

	@GetMapping("/car/get/{id}")
	public ResponseEntity<Car> getCar(@PathVariable("id") Long id) {
		if (id < 0) {
			return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(carRepository.getCarById(id), HttpStatus.OK);
	}

	@DeleteMapping("/car/delete/{id}")
	public ResponseEntity<Car> deleteCar(@PathVariable("id") Long id) {
		return new ResponseEntity(null, HttpStatus.FORBIDDEN);
	}

	@GetMapping("/car/get/all")
	public ResponseEntity<ArrayList<Car>> getAllCars() {
		return new ResponseEntity<>((ArrayList<Car>) carRepository.findAll(), HttpStatus.OK);
	}

}
