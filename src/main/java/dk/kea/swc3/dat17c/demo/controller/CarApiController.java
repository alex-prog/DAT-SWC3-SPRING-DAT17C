package dk.kea.swc3.dat17c.demo.controller;

import dk.kea.swc3.dat17c.demo.CarRepository;
import dk.kea.swc3.dat17c.demo.UserRepository;
import dk.kea.swc3.dat17c.demo.model.Car;
import dk.kea.swc3.dat17c.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CarApiController {
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/car/get")
	public ResponseEntity<ArrayList<Car>> getAllCars() {
		return new ResponseEntity<>((ArrayList<Car>) carRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping("/car/new")
	public ResponseEntity<Car> saveNewCar(Car car)
	{
		User user = userRepository.findById(car.getUser().getId());
		if (car.getUser().getId() == -1) {
			user = userRepository.findById(0L); //Long
		}
		car.setUser(user);
		return new ResponseEntity<>(carRepository.save(car), HttpStatus.OK);
	}

	@GetMapping("/car/get/{id}")
	public ResponseEntity<Car> getCar(@PathVariable("id") Long id) {
		if (!carRepository.exists(id)) {
			return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(carRepository.getCarById(id), HttpStatus.OK);
	}

	@PutMapping("/car/update/{car_id}")
	public ResponseEntity update(
			@PathVariable("car_id") Long carId,
			Car car)
	{
		Car updateCar = carRepository.findOne(carId);
		updateCar.setBrand(car.getBrand());
		updateCar.setColour(car.getColour());
		updateCar.setSeats(car.getSeats());
		updateCar.setSpeed(car.getSpeed());
		System.out.println(car.getUser().getId());
		User user = userRepository.findById(car.getUser().getId());
		if (car.getUser().getId() == -1) {
			user = userRepository.findById(0L); //Long
		}
		updateCar.setUser(user);
		System.out.println(updateCar);
		return new ResponseEntity<>(carRepository.save(updateCar), HttpStatus.OK);
	}

	@DeleteMapping("/car/delete/{id}")
	public ResponseEntity deleteCar(@PathVariable("id") Long id) {
		if (!carRepository.exists(id)) {
			return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
		}
		carRepository.delete(id);
		return new ResponseEntity<>("Deleted car", HttpStatus.OK);
	}
}
