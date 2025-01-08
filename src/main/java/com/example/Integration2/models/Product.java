package com.example.Integration2.models;

public class Product {
	private String name;
	private String color;
	private String manufactureDate;
	private String expiryDate;
	private double price;

	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(String manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product{" +
				"name='" + name + '\'' +
				", color='" + color + '\'' +
				", manufactureDate='" + manufactureDate + '\'' +
				", expiryDate='" + expiryDate + '\'' +
				", price=" + price +
				'}';
	}
}