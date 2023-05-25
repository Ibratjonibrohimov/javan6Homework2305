package uz.najottalim.javan6.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.najottalim.javan6.dto.CustomerDto;
import uz.najottalim.javan6.entity.Customer;
import uz.najottalim.javan6.exeption.NoResourceFoundException;
import uz.najottalim.javan6.mapping.CustomerMapper;
import uz.najottalim.javan6.repository.CustomerRepository;
import uz.najottalim.javan6.service.CustomerService;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> collect = customerRepository.findAll()
                .stream().map(CustomerMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }
    @Override
    public ResponseEntity<CustomerDto> getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()){
            throw new NoResourceFoundException("No Data Found");
        }
        else {
            CustomerDto customerDto = CustomerMapper.toDto(customer.get());
            return ResponseEntity.ok(customerDto);
        }
    }

    @Override
    public ResponseEntity<CustomerDto> addCustomer(CustomerDto customerDto)  {
        CustomerDto add = CustomerMapper.toDto(customerRepository.save(CustomerMapper.toEntity(customerDto)));
        return ResponseEntity.ok(add);
    }

    @Override
    public ResponseEntity<CustomerDto> upadtecustomer(Long id, CustomerDto customerDto) {
        ResponseEntity<CustomerDto> customerById = getCustomerById(id);
        customerDto.setId(id);
        CustomerDto update =
                CustomerMapper.toDto(customerRepository.save(CustomerMapper.toEntityForUpdate(customerDto)));
        return ResponseEntity.ok(update);
    }

    @Override
    public ResponseEntity<CustomerDto> deleteCustomer(Long id) {
        ResponseEntity<CustomerDto> customerById = getCustomerById(id);
        customerRepository.deleteById(id);
        return customerById;
    }

}
