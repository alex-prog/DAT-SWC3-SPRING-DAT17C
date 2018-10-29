package dk.kea.swc3.dat17c.demo.model;

public class Car {
	private String brand;
	private String colour;
	private Integer seats;

	public Car(String brand, String colour, int seats)
	{
		this.brand = brand.toUpperCase();
		this.colour = colour.toUpperCase();
		this.seats = seats;
	}

	public String getBrand()
	{
		return brand;
	}

	public void setBrand(String brand)
	{
		this.brand = brand;
	}

	public String getColour()
	{
		return colour;
	}

	public void setColour(String colour)
	{
		this.colour = colour;
	}

	public int getSeats()
	{
		return seats;
	}

	public void setSeats(int seats)
	{
		this.seats = seats;
	}
}
