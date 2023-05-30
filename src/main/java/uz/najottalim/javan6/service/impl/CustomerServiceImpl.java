package uz.najottalim.javan6.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.najottalim.javan6.dto.CustomerDto;
import uz.najottalim.javan6.dto.CustomerDtoForMostValuable;
import uz.najottalim.javan6.dto.CustomerFilterDto;
import uz.najottalim.javan6.dto.ProductDto;
import uz.najottalim.javan6.entity.Customer;
import uz.najottalim.javan6.exeption.NoResourceFoundException;
import uz.najottalim.javan6.mapping.CustomerMapper;
import uz.najottalim.javan6.repository.CustomerRepository;
import uz.najottalim.javan6.service.CustomerService;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

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
    public ResponseEntity<CustomerDto> upadteCustomer(Long id, CustomerDto customerDto) {
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

    @Override
    public ResponseEntity<List<CustomerDtoForMostValuable>> getMostValuable() {
        List<CustomerDtoForMostValuable> mostValuable = customerRepository.getMostValuable().stream().toList();
        return ResponseEntity.ok(mostValuable);
    }

    @Override
    public ResponseEntity<List<CustomerDto>> getBYTierAndSort(Integer tier, String sortColumnName) {
         List<Customer> customers = customerRepository.findByTier(tier, Sort.by(sortColumnName));
         return ResponseEntity.ok(customers.stream().map(CustomerMapper::toDtoWithoutOrders).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<List<CustomerDto>> getByTierAndSortPageable(Integer tier, String sortColumnName, Integer pageNum, Integer size) {
        List<Customer> collect = customerRepository.findByTier(tier,PageRequest.of(pageNum, size, Sort.by(sortColumnName)));
        return ResponseEntity.ok(collect.stream().map(CustomerMapper::toDtoWithoutOrders).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<List<CustomerDto>> getCustomersByFilter(String filterJson) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> criteria;
        try {
            criteria = mapper.readValue(filterJson, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(customerRepository.getCustomersByFilter(criteria));
    }

}
