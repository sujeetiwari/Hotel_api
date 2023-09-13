package com.hm.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.hm.entity.Customer;
import com.hm.entity.Hotel;
import com.hm.entity.RoomType;

import lombok.Data;

@Data
public class BookingDTO {

    private int bookingId;

    @NotNull(message = "Check-In date is required")
    private LocalDate checkInDate; // yyyy-mm-dd

    @NotNull(message = "Check-Out date is required")
    private LocalDate checkOutDate;

//    @NotNull(message = "Customer ID is required")
//    private int customerId; // Include the customerId field

   
	private String roomId;

	@Enumerated(EnumType.STRING)
	@Column(nullable=false,length=20)
	private RoomType roomType;


	private double price;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    private Hotel hotel;
		
	 public void calculatePrice() {
		 price = roomType.getPrice();
	    }
	 public void generateRandomRoomId() {
	        int length = 6;
	        String character = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	        StringBuilder room = new StringBuilder();
	        for (int i = 0; i < length; i++) {
	            room.append(character.charAt((int) (Math.random() * character.length())));
	        }
	       roomId=  room.toString();
	    }
}
