package com.hexaware.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import com.hexaware.entity.*;
import com.hexaware.myexceptions.CarNotFoundException;
import com.hexaware.util.DBConnection;

class CarLeaseRepositoryImplTest {
	
	private CarLeaseRepositoryImpl c;
	Connection connection;
	boolean isDone=false;
	@BeforeEach
	
	void setup() {
		
		try {
			connection=DBConnection.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c=new CarLeaseRepositoryImpl(connection);
		
		//clearDatabase();
	}
	private void clearDatabase() {
	    try (Statement stmt = connection.createStatement()) {
	        // Truncate or delete data from relevant tables
	        stmt.executeUpdate("TRUNCATE TABLE vehicle");
	        stmt.executeUpdate("TRUNCATE TABLE customer");
	        stmt.executeUpdate("TRUNCATE TABLE lease");
	        stmt.executeUpdate("TRUNCATE TABLE payment");
	    } catch (SQLException e) {
	        e.printStackTrace();
	        fail("Error clearing database: " + e.getMessage());
	    }
	}
	

	@Test
	void testaddcar() {
		Vehicle v=new Vehicle();
		//v.setVehicleID(1);
		v.setMake("Suzuki");
		v.setModel("Swift");
		v.setYear("2023");
		v.setDailyrate(600000);
		v.setStatus("Available");
		v.setPassengerCapacity(4);
		v.setEngineCapacity(1200);
		try {
			c.addCar(v);
			isDone=true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				assertTrue(isDone);
		
		
	}

	@Test
	 void testlease()
	 {
		isDone=false;
		c.createLeasee(1,1,"2023-12-27","2023-12-31","1");
		isDone=true;
		assertTrue(isDone);
        
	 }
	@Test
	void leaseretrive()
	{
		isDone=false;
		c.listActiveLeases();
		isDone=true;
		assertTrue(isDone);
	}
	@Test
	void testCarNotFoundException() {
        
        int nonExistentCarID = 999;
        assertThrows(CarNotFoundException.class, () -> c.findCarById(nonExistentCarID));
    }

}
