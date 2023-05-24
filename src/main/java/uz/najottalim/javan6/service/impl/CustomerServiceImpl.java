package uz.najottalim.javan6.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.najottalim.javan6.dto.CustomerDto;
import uz.najottalim.javan6.entity.Customer;
import uz.najottalim.javan6.exeption.NoResourceFoundException;
import uz.najottalim.javan6.mapping.CustomerMapper;
import uz.najottalim.javan6.repository.CustomerRepository;
import uz.najottalim.javan6.service.CustomerService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream().map(CustomerMapper::toDto).collect(Collectors.toList());

    }
    @Override
    public CustomerDto getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()){
            throw new NoResourceFoundException("No Data Found");
        }
        else {
            return CustomerMapper.toDto(customer.get());
        }
    }

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) throws Exception {
        customerRepository.save(CustomerMapper.toEntity(customerDto));
        return null;
    }
}
