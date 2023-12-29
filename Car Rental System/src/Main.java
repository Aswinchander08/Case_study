import com.hexaware.entity.*;
import com.hexaware.myexceptions.CarNotFoundException;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.*;

import com.hexaware.dao.*;
public class Main {
	public static void main(String[] args) throws CarNotFoundException, ClassNotFoundException, SQLException {
		Scanner in=new Scanner (System.in);
		CarLeaseRepositoryImpl c = new CarLeaseRepositoryImpl(null);
		int m=0;
		while(true)
		{
			System.out.println("******************************");
			System.out.println("1.ADD CAR");
			System.out.println("2.REMOVE CAR");
			System.out.println("3.LIST AVAILABLE CARS");
			System.out.println("4.LIST RENTED CARS");
			System.out.println("5.FIND A CAR");
			System.out.println("6.ADD CUSTOMER");
			System.out.println("7.REMOVE CUSTOMER");
			System.out.println("8.LIST ALL CUSTOMERS");
			System.out.println("9.FIND A CUSTOMER");
			System.out.println("10.ADD LEASE RECORD");
			System.out.println("11.CANCEL THE LEASE");
			System.out.println("12.LIST ACTIVE LEASES");
			System.out.println("13.LIST LEASE HISTORY");
			System.out.println("14.EXIT");
			System.out.println("******************************");
			System.out.println("ENTER YOUR CHOICE");
			int choice=in.nextInt();
			switch(choice)
			{
			case 1:
				System.out.println("Enter Make Of The Car :");
				String make=in.next();
				System.out.println("Enter Model Name :");
				String model=in.next();
				System.out.println("Enter Year Of The Make");
				String year=in.next();
				System.out.println("Enter Daily Rate");
				float rate=in.nextFloat();
				System.out.println("Enter Status Of Availability (1.Available , 2.Not Available");
				String status=in.next();
				System.out.println("Enter Passenger Capacity");
				int pc=in.nextInt();
				System.out.println("Enter Engine Capacity");
				int ec=in.nextInt();
				Vehicle v=new Vehicle(1,make,model,year,rate,status,pc,ec);
				try {
					c.addCar(v);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.println("Enter Vehicle ID");
				int vehicleid = in.nextInt();
				try {
					c.removeCar(vehicleid);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:
				try {
				c.listAvailableCars();
				
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:
				try {
					c.listRentedCars();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 5:
				System.out.println("Enter Car ID");
				int carid=in.nextInt();
				c.findCarById(carid);
				break;
			case 6:
				System.out.println("Enter First Name :");
				String firstname=in.next();
				System.out.println("Enter Last Name :");
				String lastname=in.next();
				System.out.println("Enter email :");
				String email=in.next();
				System.out.println("Enter Phonenumber :");
				String phno=in.next();
				Customer cus = new Customer(1,firstname,lastname,email,phno);
				c.addCustomer(cus);
				break;
			case 7:
				System.out.println("Enter Customer ID :");
				int cusid=in.nextInt();
				c.removeCustomer(cusid);
				break;
			case 8:
				c.customers();
				break;
			case 9:
				System.out.println("Enter Customer ID :");
				int cuid=in.nextInt();
				c.findCustomerByid(cuid);
				break;
			case 10:
				    System.out.println("Enter Lease ID:");
				    int liid=in.nextInt();
					System.out.println("Enter Vehicle ID:");
					int caid=in.nextInt();
					System.out.println("Enter Customer ID:");
					int cussid=in.nextInt();
					System.out.println("Enter Start Date:");
					String startdate=in.next();
					System.out.println("Enter End Date:");
					String enddate=in.next();
					System.out.println("Enter Type Of Lease \n 1. Dailly Lease  2. Monthly Lease");
					String lease=in.next();
					System.out.println("Enter Lease Amount:");
					float amount=in.nextFloat();
					Lease l=new Lease(m+1, caid, cussid, startdate, enddate,lease);
					Lease a=c.createLease(l);
					c.recordPayment(a, amount,liid);
					
				
				break;
			case 11:
				System.out.println("Enter Lease ID:");
				int leaseid=in.nextInt();
				c.returnCar(leaseid);
				break;
			case 12:
				c.listActiveLeases();
				break;
			case 13:
				c.listLeaseHistory();
				break;
			case 14:
				System.out.println("Exiting the application. Good Bye!");
            	in.close();
                System.exit(0);
				default :
					System.out.println("Invalid choice. Please choose a valid option.");
				
				
				
			}
		}
		
	}

	

}
