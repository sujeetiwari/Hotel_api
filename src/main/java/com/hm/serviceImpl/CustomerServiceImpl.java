package com.hm.serviceImpl;




import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dto.CustomerDTO;
import com.hm.entity.Customer;
import com.hm.exception.ResourceNotFoundException;
import com.hm.repository.BookingRepository;
import com.hm.repository.CustomerRepository;
import com.hm.service.CustomerService;
import com.hm.utils.CustomerConverter;



@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerConverter customerConvert;
	
	@Autowired
	CustomerRepository cusRepo;
	
	@Autowired
	BookingRepository bookRepo;
	
	@Override
	public CustomerDTO createCustomer(Customer c) {
		String pass=c.getCustomerName().substring(0,3).toLowerCase()+"123";
		c.setPassword(pass);
				cusRepo.save(c);
		return customerConvert.convertBkEntityToDto(c);
	}

	@Override
	public List<CustomerDTO> getAllCustomer() {
		List<Customer>c=cusRepo.findAll();
		List<CustomerDTO>cDto=new ArrayList<>();
		for(Customer cus:c) {
			cDto.add(customerConvert.convertBkEntityToDto(cus));
			
		}
		return cDto;
	}

	@Override
	public CustomerDTO getCustomerById(int cId) throws ResourceNotFoundException {
		Customer c=cusRepo.findById(cId).orElseThrow(()-> new ResourceNotFoundException("Customer","id",cId));
		return customerConvert.convertBkEntityToDto(c);
	}

	@Override
	public CustomerDTO updateCustomer(int cId, Customer c) throws ResourceNotFoundException {
	Customer customer=cusRepo.findById(cId).orElseThrow(()-> new ResourceNotFoundException("Customer", "id", cId));
	customer.setAddress(c.getAddress());
	customer.setContact(c.getContact());
	customer.setGender(c.getGender());
	customer.setPassword(c.getPassword());
	cusRepo.save(customer);
	
		return customerConvert.convertBkEntityToDto(c);
	}

	@Override
	public void customerDeleteById(int cId) {
		Customer c=cusRepo.findById(cId).orElseThrow(()-> new ResourceNotFoundException("Customer", "id", cId));
		 cusRepo.deleteById(cId);
		
	}

	
}
