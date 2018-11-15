package dk.kea.swc3.dat17c.demo.controller;

import dk.kea.swc3.dat17c.demo.CarRepository;
import dk.kea.swc3.dat17c.demo.UserRepository;
import dk.kea.swc3.dat17c.demo.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String saveCarToDB(Model model) {
		model.addAttribute("car", new Car());
		model.addAttribute("users", userRepository.getAllByNameNotNull());
		return "save_car_form";
	}

	@GetMapping("/car/update/{car-id}")
	public String updateCarGetMapping(@PathVariable("car-id") Long carId, Model model) {
		model.addAttribute("car", carRepository.getCarById(carId));
		model.addAttribute("users", userRepository.getAllByNameNotNull());
		return "update_car_form";
	}
}
