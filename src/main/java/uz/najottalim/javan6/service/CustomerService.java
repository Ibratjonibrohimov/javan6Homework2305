package uz.najottalim.javan6.service;

import org.springframework.http.ResponseEntity;
import uz.najottalim.javan6.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    ResponseEntity<List<CustomerDto>> getAllCustomers();

    ResponseEntity<CustomerDto> getCustomerById(Long id);

    ResponseEntity<CustomerDto> addCustomer(CustomerDto customerDto) ;

    ResponseEntity<CustomerDto> upadtecustomer(Long id, CustomerDto customerDto) ;

    ResponseEntity<CustomerDto> deleteCustomer(Long id) ;
}
