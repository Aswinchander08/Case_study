package com.hexaware.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.hexaware.entity.*;
import com.hexaware.myexceptions.*;
public interface ICarLeaseRepository {
	// Vehicle management
	void addCar(Vehicle car ) throws SQLException, ClassNotFoundException;
	void removeCar(int carID) throws CarNotFoundException,ClassNotFoundException,SQLException;
	List <Vehicle> listAvailableCars() throws SQLException, ClassNotFoundException;
	List <Vehicle> listRentedCars() throws ClassNotFoundException, SQLException;
	Vehicle findCarById(int vehicleID) throws ClassNotFoundException, CarNotFoundException, SQLException;
	
	// Customer management
	void addCustomer(Customer customer) throws ClassNotFoundException, SQLException;
	void removeCustomer(int customerID) throws ClassNotFoundException, SQLException;
	List<Customer> customers() throws ClassNotFoundException, SQLException;
	Customer findCustomerByid(int customerID) throws ClassNotFoundException,SQLException;
	
	//Lease MAnagement
	Lease  createLease(Lease l);
	void returnCar(int leaseID) throws SQLException;
	List<Lease> listActiveLeases();
    List<Lease> listLeaseHistory();
	
    //Payment Handling
    void recordPayment(Lease lease,float amount,int leaseid);
	

}
