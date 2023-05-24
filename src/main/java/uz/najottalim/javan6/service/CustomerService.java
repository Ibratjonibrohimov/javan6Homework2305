package uz.najottalim.javan6.service;

import uz.najottalim.javan6.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(Long id);

    CustomerDto addCustomer(CustomerDto customerDto) throws Exception;
}
