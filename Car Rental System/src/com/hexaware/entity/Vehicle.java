package com.hexaware.entity;

public class Vehicle {
	     int vehicleID;
	     String model;
	     String year;
	     String make;
	     float dailyrate;
	     String status;
	     int passengerCapacity;
	     int engineCapacity;

	    public Vehicle() {
	        
	    }

	    public Vehicle(int vehicleid,String make, String model, String year, float dailyRate,
	                   String status, int passengerCapacity, int engineCapacity) {
	        this.vehicleID = vehicleID;
	        this.make = make;
	        this.model = model;
	        this.year = year;
	        this.dailyrate = dailyRate;
	        this.status = status;
	        this.passengerCapacity = passengerCapacity;
	        this.engineCapacity = engineCapacity;

	   
	}

		public int getVehicleID() {
			return vehicleID;
		}

		public void setVehicleID(int vehicleID) {
			this.vehicleID = vehicleID;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public String getYear() {
			return year;
		}

		public void setYear(String year) {
			this.year = year;
		}

		public String getMake() {
			return make;
		}

		public void setMake(String make) {
			this.make = make;
		}

		public float getDailyrate() {
			return dailyrate;
		}

		public void setDailyrate(float dailyrate) {
			this.dailyrate = dailyrate;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public int getPassengerCapacity() {
			return passengerCapacity;
		}

		public void setPassengerCapacity(int passengerCapacity) {
			this.passengerCapacity = passengerCapacity;
		}

		public int getEngineCapacity() {
			return engineCapacity;
		}

		public void setEngineCapacity(int engineCapacity) {
			this.engineCapacity = engineCapacity;
		}

		@Override
		public String toString() {
			return "Vehicle [vehicleID=" + vehicleID + ", model=" + model + ", year=" + year + ", make=" + make
					+ ", dailyrate=" + dailyrate + ", status=" + status + ", passengerCapacity=" + passengerCapacity
					+ ", engineCapacity=" + engineCapacity + "]"+"\n";
		}

}
