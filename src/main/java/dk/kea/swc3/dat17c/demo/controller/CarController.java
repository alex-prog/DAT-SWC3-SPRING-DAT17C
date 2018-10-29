package dk.kea.swc3.dat17c.demo.controller;

import dk.kea.swc3.dat17c.demo.model.Car;
import dk.kea.swc3.dat17c.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {
	@GetMapping("/cars")
	public String viewAllCars(Model model,
	                          @RequestParam(defaultValue = "{{user}}")
			                          String name,
	                          @RequestParam(defaultValue = "-1")
			                          Integer age,
	                          @RequestParam(defaultValue = "F")
			                          Character gender)
	{
		Car car1 = new Car("Ford", "blue", 5);
		Car car2 = new Car("Volvo", "grey", 5);
		Car car3 = new Car("Kia", "red", 5);
		Car car4 = new Car("Mazda", "blue", 5);

		List<Car> cars = new ArrayList<>();
		cars.add(car1);
		cars.add(car2);
		cars.add(car3);
		cars.add(car4);
		model.addAttribute("cars", cars);
		model.addAttribute("user", new User("Muddi", 21, 'M'));
		return "list_of_cars";
	}
}
