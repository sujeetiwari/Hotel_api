package com.hm.service;

import com.hm.dto.BookingDTO;
import com.hm.entity.Booking;
import com.hm.exception.ResourceNotFoundException;

import java.util.List;

public interface BookingService {

    BookingDTO createBooking(Booking booking, int customerId);

    List<BookingDTO> getAllBookings();
    
    BookingDTO getBookingById(int bookingId) throws ResourceNotFoundException;
    
    BookingDTO updateBooking(int bookingId, Booking booking) throws ResourceNotFoundException;

    void deleteBooking(int bookingId);


}
