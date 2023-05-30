package uz.najottalim.javan6.service;

import org.springframework.http.ResponseEntity;
import uz.najottalim.javan6.dto.CustomerDto;
import uz.najottalim.javan6.dto.CustomerDtoForMostValuable;
import uz.najottalim.javan6.dto.CustomerFilterDto;
import uz.najottalim.javan6.dto.ProductDto;

import java.util.List;

public interface CustomerService {
    ResponseEntity<List<CustomerDto>> getAllCustomers();

    ResponseEntity<CustomerDto> getCustomerById(Long id);

    ResponseEntity<CustomerDto> addCustomer(CustomerDto customerDto) ;

    ResponseEntity<CustomerDto> upadteCustomer(Long id, CustomerDto customerDto) ;

    ResponseEntity<CustomerDto> deleteCustomer(Long id) ;

    ResponseEntity<List<CustomerDtoForMostValuable>> getMostValuable();

    ResponseEntity<List<CustomerDto>> getBYTierAndSort(Integer tier, String sortColumnName);

    ResponseEntity<List<CustomerDto>> getByTierAndSortPageable(Integer tier, String sortColumnName, Integer pageNum, Integer size);

    ResponseEntity<List<CustomerDto>> getCustomersByFilter(String filterJson);
}
