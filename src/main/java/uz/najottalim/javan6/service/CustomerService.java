package uz.najottalim.javan6.service;

import uz.najottalim.javan6.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(Long id);

    CustomerDto addCustomer(CustomerDto customerDto) throws Exception;

    CustomerDto upadtecustomer(Long id, CustomerDto customerDto) throws Exception;

    CustomerDto deleteCustomer(Long id) throws Exception;
}
