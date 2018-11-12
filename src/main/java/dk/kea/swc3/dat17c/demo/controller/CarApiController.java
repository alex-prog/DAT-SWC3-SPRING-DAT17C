package dk.kea.swc3.dat17c.demo.controller;

import dk.kea.swc3.dat17c.demo.CarRepository;
import dk.kea.swc3.dat17c.demo.UserRepository;
import dk.kea.swc3.dat17c.demo.model.Car;
import dk.kea.swc3.dat17c.demo.model.User;
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
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/car/get")
	public ResponseEntity<ArrayList<Car>> getAllCars() {
		return new ResponseEntity<>((ArrayList<Car>) carRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping("/car/new")
	public ResponseEntity<Car> saveNewCar(@RequestParam(defaultValue = "NO_BRAND") String brand,
	                                      @RequestParam(defaultValue = "NO_COLOUR") String colour,
	                                      @RequestParam(defaultValue = "-1") Integer seats,
	                                      @RequestParam(defaultValue = "-1") Integer speed,
	                                      @RequestParam(defaultValue = "-1") Long user_id)
	{
		User user = userRepository.findById(user_id);
		if (user_id == -1) {
			user = userRepository.findById(0L); //Long
		}
		return new ResponseEntity<>(carRepository.save(new Car(brand, colour, seats, speed, user)), HttpStatus.OK);
	}

	@GetMapping("/car/get/{id}")
	public ResponseEntity<Car> getCar(@PathVariable("id") Long id) {
		if (!carRepository.exists(id)) {
			return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(carRepository.getCarById(id), HttpStatus.OK);
	}

	@PutMapping("/car/update/{car_id}")
	public ResponseEntity update(@PathVariable("car_id") Long car_id,
	                             @RequestParam(defaultValue = "NO_BRAND") String brand,
	                             @RequestParam(defaultValue = "NO_COLOUR") String colour,
	                             @RequestParam(defaultValue = "-1") Integer seats,
	                             @RequestParam(defaultValue = "-1") Integer speed,
	                             @RequestParam(defaultValue = "-1") Long user_id
	)
	{
		Car car = carRepository.findOne(car_id);
		car.setBrand(brand);
		car.setColour(colour);
		car.setSeats(seats);
		car.setSpeed(speed);
		User user = userRepository.findById(user_id);
		if (user_id == -1) {
			user = userRepository.findById(0L); //Long
		}
		car.setUser(user);
		return new ResponseEntity<>(carRepository.save(car), HttpStatus.OK);
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
