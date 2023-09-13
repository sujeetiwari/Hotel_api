package com.hm.entity;

import java.time.LocalDate;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	
	@Column(nullable=false)
	private LocalDate checkInDate;
	
	@Column(nullable=false)
	private LocalDate checkOutDate;
	
	@Column(nullable=false,length=10)
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
