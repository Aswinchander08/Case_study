package com.hexaware.myexceptions;

public class LeaseNotFoundException extends RuntimeException {
	public LeaseNotFoundException(String m) {
        super(m);
    }

}
