package com.hexaware.dao;

import java.sql.*;
import java.util.*;

import com.hexaware.entity.Customer;
import com.hexaware.entity.Lease;
import com.hexaware.entity.Vehicle;
import com.hexaware.util.DBConnection;
import com.hexaware.myexceptions.*;

public class CarLeaseRepositoryImpl implements ICarLeaseRepository {
	private Connection connection;
	PreparedStatement ps;
	ResultSet rs;
	Statement s;
	/*public  CarLeaseRepositoryImpl() throws SQLException, ClassNotFoundException {
        this.connection = DBConnection.getConnection();
    }*/
	private List<Vehicle> vehiclelist=new ArrayList<>();
	private List<Customer> customerlist=new ArrayList<>();
	private List<Lease> leaselist=new ArrayList<>();	
	public CarLeaseRepositoryImpl(Connection connection2) {
		// TODO Auto-generated constructor stub
		this.connection=connection2;
	}

	@Override
	public void addCar(Vehicle car) throws SQLException, ClassNotFoundException {
		connection=DBConnection.getConnection();
		String sq="insert into vehicle (make,model,year_of_make,dailyrate,status_availability,passengercapacity,enginecapacity) values(?,?,?,?,?,?,?)";
		ps=connection.prepareStatement(sq);
		//ps.setInt(1, car.getVehicleID());
		ps.setString(1, car.getMake());
		ps.setString(2,car.getModel());
		ps.setString(3, car.getYear());
		ps.setFloat(4,car.getDailyrate());
		ps.setString(5, car.getStatus());
		ps.setInt(6, car.getPassengerCapacity());
		ps.setInt(7, car.getEngineCapacity());
		int noofrows=ps.executeUpdate();
		System.out.println(noofrows + " Record Inserted Successfully");
		connection.close();
	}

	@Override
	public void removeCar(int carID) throws ClassNotFoundException,SQLException {
		// TODO Auto-generated method stub
		try {
		connection=DBConnection.getConnection();
		String sq="delete from vehicle where vehicleid=?";
		ps=connection.prepareStatement(sq);
		ps.setInt(1, carID);
		int noofrows=ps.executeUpdate();
		System.out.println(noofrows+" Deleted Sucsessfully");
		}catch(CarNotFoundException e)
		{
			throw new CarNotFoundException("Vehicle not found with ID: " + carID);
		}
		connection.close();
		
	}

	@Override
	public List<Vehicle> listAvailableCars() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		connection=DBConnection.getConnection();
		List<Vehicle> availablecars=new ArrayList<>();
		String sq="select * from vehicle where status_availability='Available'";
		s=connection.createStatement();
		rs=s.executeQuery(sq);
		while(rs.next())
		{
			Vehicle car=resultSetToCar(rs);
			availablecars.add(car);
		}
		connection.close();
		System.out.println(availablecars.toString());
		return availablecars;
		
	}
    private Vehicle resultSetToCar(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		Vehicle car = new Vehicle();
        car.setVehicleID(rs.getInt("vehicleID"));
        car.setMake(rs.getString("make"));
        car.setModel(rs.getString("model"));
        car.setYear(rs.getString("year_of_make"));
        car.setDailyrate(rs.getFloat("dailyRate"));
        car.setStatus(rs.getString("status_availability"));
        car.setPassengerCapacity(rs.getInt("passengerCapacity"));
        car.setEngineCapacity(rs.getInt("engineCapacity"));
        return car;
	}

	@Override
	public List<Vehicle> listRentedCars() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		connection=DBConnection.getConnection();
		List<Vehicle> rentedcars=new ArrayList<>();
		String sq="select * from vehicle where status_availability='Not_available'";
		s=connection.createStatement();
		rs=s.executeQuery(sq);
		while(rs.next())
		{
			Vehicle car=resultSetToCar(rs);
			rentedcars.add(car);
		}
		connection.close();
		System.out.println(rentedcars.toString());
		return rentedcars;
		
		
	}

	@Override
	public Vehicle findCarById(int vehicleID) throws CarNotFoundException,ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		connection=DBConnection.getConnection();
		String sq="select * from vehicle where vehicleid=?";
		ps=connection.prepareStatement(sq);
		ps.setInt(1, vehicleID);
		rs=ps.executeQuery();
		if(rs.next())
		{
			System.out.println(resultSetToCar(rs).toString());
			 resultSetToCar(rs);
			 //return null;
			 
		}
		else
		{
			throw new CarNotFoundException("Car not found with ID: "+ vehicleID);
		}
		return null;
	}
	//customer management

	@Override
	public void addCustomer(Customer customer) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		connection=DBConnection.getConnection();
		String sq="insert into customer (firstname,lastname,email,phonenumber)values(?,?,?,?)";
		ps=connection.prepareStatement(sq);
		//ps.setInt(1, customer.getCustomerID());
		ps.setString(1, customer.getFirstname());
		ps.setString(2, customer.getLastname());
		ps.setString(3, customer.getEmail());
		ps.setString(4, customer.getPhoneNumber());
		int noofrow=ps.executeUpdate();
		System.out.println(noofrow + "Customer added successfully /n" +customer);
		connection.close();
	}

	@Override
	public void removeCustomer(int customerID) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		try {
			connection=DBConnection.getConnection();
			String sq="delete from customer where customerid=?";
			ps=connection.prepareStatement(sq);
			ps.setInt(1, customerID);
			int noofrows=ps.executeUpdate();
			System.out.println(noofrows+" Deleted Sucsessfully");
		}catch(CustomerNotFoundException e)
		{
			throw new CustomerNotFoundException ("Customer not found with ID" + customerID);
		}
		
	}

	private Customer resultSetToCus(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		Customer cus = new Customer();
        cus.setCustomerID(rs.getInt("customerID"));
        cus.setFirstname(rs.getString("Firstname"));
        cus.setLastname(rs.getString("Lastname"));
        cus.setEmail(rs.getString("Email"));
        cus.setPhoneNumber(rs.getString("PhoneNumber"));
        return cus;
	}
	@Override
	public List<Customer> customers() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		connection=DBConnection.getConnection();
		List<Customer> availablecus=new ArrayList<>();
		String sq="select * from customer";
		s=connection.createStatement();
		rs=s.executeQuery(sq);
		while(rs.next())
		{
			Customer cus=resultSetToCus(rs);
			availablecus.add(cus);
		}
		System.out.println(availablecus.toString());
		connection.close();
		return availablecus;
	}
	

	@Override
	public Customer findCustomerByid(int customerID) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		connection=DBConnection.getConnection();
		String sq="select * from customer where customerid=?";
		ps=connection.prepareStatement(sq);
		ps.setInt(1, customerID);
		rs=ps.executeQuery();
		if(rs.next())
		{
			System.out.println(resultSetToCus(rs).toString()); 
		}
		else
		{
			throw new CustomerNotFoundException("Customer not found with ID: "+ customerID);
		}
		connection.close();
		return null;
	}
	//lease management

	@Override
	public Lease createLease(Lease l) {
		// TODO Auto-generated method stub
		try {
		connection = DBConnection.getConnection();
		String sq="insert into lease (vehicleid,customerid,startdate,enddate,type_of_lease) values (?,?,?,?,?)";
		ps=connection.prepareStatement(sq);
		//ps.setInt(1,l.getLeaseID());
		ps.setInt(1, l.getVehicleID());
		ps.setInt(2, l.getCustomerID());
		ps.setString(3, l.getStartDate());
		ps.setString(4,l.getLastDate());
		ps.setString(5, l.getType());
		int noofrow=ps.executeUpdate();
		System.out.println(noofrow + " Lease Created");
		connection.close();
		}catch(SQLException  e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	public Lease createLeasee(int vehicleid,int customerid,String sdate,String edate,String typeoflease) {
		// TODO Auto-generated method stub
		try {
		connection = DBConnection.getConnection();
		String sq="insert into lease (vehicleid,customerid,startdate,enddate,type_of_lease) values (?,?,?,?,?)";
		ps=connection.prepareStatement(sq);
		//ps.setInt(1,l.getLeaseID());
		ps.setInt(1, vehicleid);
		ps.setInt(2, customerid);
		ps.setString(3, sdate);
		ps.setString(4,edate);
		ps.setString(5, typeoflease);
		int noofrow=ps.executeUpdate();
		System.out.println(noofrow + " Lease Created");
		connection.close();
		}catch(SQLException  e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void returnCar(int leaseID) throws SQLException {
		// TODO Auto-generated method stub
		try {
			connection=DBConnection.getConnection();
			String sq="update lease set enddate = current_date where leaseid=?";
			ps=connection.prepareStatement(sq);
			ps.setInt(1, leaseID);
			int noofrow=ps.executeUpdate();
			System.out.println(noofrow + " Updated Successfully!!");
			connection.close();
		}catch(LeaseNotFoundException  | ClassNotFoundException e)
		{
			throw new LeaseNotFoundException ("Lease Not Found with Id : "+ leaseID);
		}
		
	}
	private Lease resultSetToLease(ResultSet rs) throws SQLException {
	    Lease lease = new Lease();
	    lease.setLeaseID(rs.getInt("leaseID"));
	    lease.setVehicleID(rs.getInt("vehicleID"));
	    lease.setCustomerID(rs.getInt("customerID"));
	    lease.setStartDate(rs.getString("startDate"));
	    lease.setLastDate(rs.getString("endDate"));
	    lease.setType(rs.getString("type_of_lease"));
	    return lease;
	}

	@Override
	public List<Lease> listActiveLeases() {
		// TODO Auto-generated method stub
		List<Lease> activeleases=new ArrayList<>();
		try {
			connection=DBConnection.getConnection();
		String sq="select * from lease where enddate >= current_date";
		s=connection.createStatement();
		rs=s.executeQuery(sq);
		while(rs.next())
		{
			Lease lease=resultSetToLease(rs);
			activeleases.add(lease);
		}
		System.out.println(activeleases.toString());
		connection.close();
		}catch(SQLException e )
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return activeleases;
	}

	@Override
	public List<Lease> listLeaseHistory() {
		// TODO Auto-generated method stub
		List<Lease> leasehistory =new ArrayList<>();
		try {
			connection=DBConnection.getConnection();
			String sq="select * from lease where enddate < current_date";
			s=connection.createStatement();
			rs=s.executeQuery(sq);
			while(rs.next())
			{
				Lease lease=resultSetToLease(rs);
				leasehistory.add(lease);
			}
			System.out.println(leasehistory.toString());
			connection.close();
			}catch(SQLException e)
		{
				e.printStackTrace();
		} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return leasehistory;
	}
	//payment management

	@Override
	public void recordPayment(Lease lease, float amount,int leaseid) {
		// TODO Auto-generated method stub
		try {
			connection=DBConnection.getConnection();
			String sq="insert into payment (leaseid,paymentdate,amount) values(?,?,?)";
			ps=connection.prepareStatement(sq);
			System.out.println(lease.getLeaseID());
			ps.setInt(1, leaseid);
			ps.setString(2, lease.getStartDate());
			ps.setFloat(3, amount);
			int noofrow=ps.executeUpdate();
		}catch(SQLException | ClassNotFoundException e)
		{
		}
		
	}
	
	

}
