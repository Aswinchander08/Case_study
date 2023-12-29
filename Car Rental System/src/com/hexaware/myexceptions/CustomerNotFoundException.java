package com.hexaware.myexceptions;

public class CustomerNotFoundException extends RuntimeException {
	public CustomerNotFoundException(String m) {
        super(m);
    }

}
