package uz.najottalim.javan6.service;

import org.springframework.http.ResponseEntity;
import uz.najottalim.javan6.dto.CustomerDto;
import uz.najottalim.javan6.dto.CustomerDtoForMostValuable;

import java.util.List;

public interface CustomerService {
    ResponseEntity<List<CustomerDto>> getAllCustomers();

    ResponseEntity<CustomerDto> getCustomerById(Long id);

    ResponseEntity<CustomerDto> addCustomer(CustomerDto customerDto) ;

    ResponseEntity<CustomerDto> upadteCustomer(Long id, CustomerDto customerDto) ;

    ResponseEntity<CustomerDto> deleteCustomer(Long id) ;

    ResponseEntity<List<CustomerDtoForMostValuable>> getMostValuable();
}
