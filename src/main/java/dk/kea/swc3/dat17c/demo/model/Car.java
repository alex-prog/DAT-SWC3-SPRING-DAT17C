package dk.kea.swc3.dat17c.demo.model;

import javax.persistence.*;

@Entity
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String brand;
	private String colour;
	private Integer seats;
	private Integer speed;

	@OneToOne
	private User user;

	public Car() {
	}

	public Car(String brand, String colour, Integer seats, Integer speed, User user) {
		this.brand = brand;
		this.colour = colour;
		this.seats = seats;
		this.speed = speed;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer seats) {
		this.seats = seats;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Car{" +
				"id=" + id +
				", brand='" + brand + '\'' +
				", colour='" + colour + '\'' +
				", seats=" + seats +
				", speed=" + speed +
				", user=" + user +
				'}';
	}
}
