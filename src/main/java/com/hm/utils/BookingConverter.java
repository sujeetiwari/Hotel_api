package com.hm.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import com.hm.dto.BookingDTO;
import com.hm.entity.Booking;
import com.hm.entity.Customer; // Import the Customer entity if it's not imported already.

@Component
public class BookingConverter {
    
    public Booking convertBkDtoToEntity(BookingDTO bDto) {
        Booking b = new Booking();
        if (bDto != null) {
            BeanUtils.copyProperties(bDto, b);
            
            // If you want to set the customerId in the Booking entity based on the DTO
//            Customer customer = new Customer();
//            customer.setCustomerId(bDto.getCustomerId());
//            b.setCustomer(customer);
        }
        return b;
    }

    public BookingDTO convertBkEntityToDto(Booking booking) {
        BookingDTO bDto = new BookingDTO();
        if (booking != null) {
            BeanUtils.copyProperties(booking, bDto);
            
            // If you want to retrieve the customerId from the Booking entity and set it in the DTO
//            bDto.setCustomerId(booking.getCustomer().getCustomerId());
        }
        return bDto;
    }
}
