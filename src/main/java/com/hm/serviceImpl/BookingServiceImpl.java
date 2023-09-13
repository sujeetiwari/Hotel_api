package com.hm.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dto.BookingDTO;
import com.hm.entity.Booking;
import com.hm.entity.Customer;
import com.hm.exception.ResourceNotFoundException;
import com.hm.repository.BookingRepository;
import com.hm.repository.CustomerRepository;
import com.hm.service.BookingService;
import com.hm.utils.BookingConverter;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingConverter bookingConverter;
    private final CustomerRepository customerRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, BookingConverter bookingConverter, CustomerRepository customerRepository) {
        this.bookingRepository = bookingRepository;
        this.bookingConverter = bookingConverter;
        this.customerRepository = customerRepository;
    }

    @Override
    public BookingDTO createBooking(Booking booking, int customerId) {
    	
    	 // Retrieve the Customer entity by customer ID
        Customer customer = customerRepository.findById(customerId).orElseThrow(()->
        new ResourceNotFoundException("Customer", "id", customerId));

        // Set the retrieved customer in the Booking entity
        booking.setCustomer(customer);
        booking.calculatePrice();
        booking.generateRandomRoomId();
        
        Booking savedBooking = bookingRepository.save(booking);
        return bookingConverter.convertBkEntityToDto(savedBooking);
    }

	@Override
	public List<BookingDTO> getAllBookings() {
		List<Booking>b=bookingRepository.findAll();
		List<BookingDTO>bDto=new ArrayList<>();
		for(Booking book: b) {
			bDto.add(bookingConverter.convertBkEntityToDto(book));
		}
		return bDto;
	}

	@Override
	public BookingDTO getBookingById(int bookingId) throws ResourceNotFoundException {
		Booking b=bookingRepository.findById(bookingId).orElseThrow(()-> new ResourceNotFoundException("Booking", "id", bookingId));
		
		return bookingConverter.convertBkEntityToDto(b);
	}

	@Override
	public BookingDTO updateBooking(int bookingId, Booking booking) throws ResourceNotFoundException {
		Booking b=bookingRepository.findById(bookingId).orElseThrow(()-> new ResourceNotFoundException("Booking", "id", bookingId));
		b.setCheckInDate(booking.getCheckInDate());
		b.setCheckOutDate(booking.getCheckOutDate());
		b.setRoomType(booking.getRoomType());
		bookingRepository.save(b);
		return bookingConverter.convertBkEntityToDto(booking);
	}

	@Override
	public void deleteBooking(int bookingId) {
		Booking b=bookingRepository.findById(bookingId).orElseThrow(()-> new ResourceNotFoundException("Booking","id", bookingId));
		bookingRepository.deleteById(bookingId);
	}


}
