package dk.kea.swc3.dat17c.demo.controller;

import dk.kea.swc3.dat17c.demo.CarRepository;
import dk.kea.swc3.dat17c.demo.UserRepository;
import dk.kea.swc3.dat17c.demo.model.Car;
import dk.kea.swc3.dat17c.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/cars")
	public String viewAllCars(Model model,
	                          @RequestParam(defaultValue = "{{user}}")
			                          String name,
	                          @RequestParam(defaultValue = "-1")
			                          Integer age,
	                          @RequestParam(defaultValue = "F")
			                          Character gender)
	{
		model.addAttribute("cars", carRepository.getAllByBrandIsNotNull());
		return "list_of_cars";
	}

	@GetMapping("/car/save")
	public String saveCarToDB(Model model){
		List<User> users = userRepository.getAllByNameNotNull();
		model.addAttribute("car", new Car());
		model.addAttribute("users", users);
		return "save_car_form";
	}

	@PostMapping("/car/save")
	public String saveIt(Car car, @RequestParam(defaultValue = "NO_NAME", name = "selected-user") String username
	){
		car.setUser(userRepository.findByName(username));
		if (car.getUser() == null){
			User user = new User(username, -1, 'N');
			car.setUser(user);
			userRepository.save(user);
		}
		carRepository.save(car);
		return "redirect:/cars";
	}
}
