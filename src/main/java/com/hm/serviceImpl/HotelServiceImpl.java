package com.hm.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dto.HotelDTO;
import com.hm.entity.Booking;
import com.hm.entity.Hotel;
import com.hm.exception.ResourceNotFoundException;
import com.hm.repository.BookingRepository;
import com.hm.repository.HotelRepository;
import com.hm.service.HotelService;
import com.hm.utils.BookingConverter;
import com.hm.utils.HotelConverter;






@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	HotelRepository hotelRepo;
	
	@Autowired
	HotelConverter hotelConvert;
	
	@Autowired
	BookingRepository bookRepo;
	
	@Autowired
	BookingConverter bookConvert;
	
	@Override
	public HotelDTO createHotel(Hotel h) {
		hotelRepo.save(h);
		return hotelConvert.convertHlEntityToDto(h);
	}

	@Override
	public List<HotelDTO> getAllHotel() {
		List<Hotel>hotels=hotelRepo.findAll();
		List<HotelDTO>hDto=new ArrayList<>();
		
		for(Hotel h:hotels) {
			hDto.add(hotelConvert.convertHlEntityToDto(h));
		}
		
		return hDto;
		
	}

	@Override
	public HotelDTO getHotelById(int hId) throws ResourceNotFoundException {
		Hotel h=hotelRepo.findById(hId).orElseThrow(()->
		new ResourceNotFoundException("Hotel", "id", hId));
		return hotelConvert.convertHlEntityToDto(h);
	}

	@Override
	public HotelDTO updateHotelDetail(int id, Hotel h) throws ResourceNotFoundException {
		Hotel hotel=hotelRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel", "id", id));
		hotel.setHotelName(h.getHotelName());
		hotel.setLocation(h.getLocation());
		hotel.setRating(h.getRating());
		hotelRepo.save(hotel);
		return hotelConvert.convertHlEntityToDto(hotel);
		
	}

	@Override
	public void deleteHotelById(int id) {
		Hotel h=hotelRepo.findById(id).orElseThrow(()-> 
		new ResourceNotFoundException("Hotel","id" ,id ));
			hotelRepo.deleteById(id);
		}

	@Override
	public void hotelAssignedToBooking(int hId, int bId) {
		Hotel h=hotelRepo.findById(hId).orElseThrow(()-> new ResourceNotFoundException("Hotel", "id", hId));
		Booking b=bookRepo.findById(bId).orElseThrow(()-> new ResourceNotFoundException("Booking", "id", bId));
		b.setHotel(h);
		hotelRepo.save(h);
		bookRepo.save(b);
		
		
	}
	}


