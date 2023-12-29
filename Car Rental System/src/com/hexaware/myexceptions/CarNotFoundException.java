package com.hexaware.myexceptions;

public class CarNotFoundException extends RuntimeException{
	public CarNotFoundException(String m) {
		super(m);
		System.out.println("Car Not Found");
	}

}
