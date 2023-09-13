package com.hm.entity;

public enum RoomType {
	  SINGLE(3000.0),
	    DOUBLE(4000.0),
	    SUITE(5000.0),
	    DELUXE(6000.0);

	    private final double price;

	    RoomType(double price) {
	        this.price = price;
	    }

	    public double getPrice() {
	        return price;
	    }
}
