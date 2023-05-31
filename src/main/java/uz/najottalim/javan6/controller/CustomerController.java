package uz.najottalim.javan6.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.najottalim.javan6.dto.CustomerDto;
import uz.najottalim.javan6.dto.CustomerDtoForMostValuable;
import uz.najottalim.javan6.dto.CustomerFilterDto;
import uz.najottalim.javan6.dto.ProductDto;
import uz.najottalim.javan6.service.CustomerService;
import java.util.*;

@RestController
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @GetMapping()
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        return customerService.getAllCustomers();
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }
    @PostMapping()
    public ResponseEntity<CustomerDto> addCustomer(@Validated @RequestBody CustomerDto customerDto)  {
        return customerService.addCustomer(customerDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@Validated @RequestBody CustomerDto customerDto,@PathVariable Long id){
        return customerService.upadteCustomer(id,customerDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerDto> deleteCustomer(@PathVariable Long id){
        return customerService.deleteCustomer(id);
    }

    @GetMapping("/most-valuable")
    public ResponseEntity<List<CustomerDtoForMostValuable>> getMostValuable(){
        return customerService.getMostValuable();
    }
    @GetMapping("/tier/{tier}/sortBy/{sortColumnName}")
    public ResponseEntity<List<CustomerDto>> getByTierAndSort(@PathVariable Integer tier,@PathVariable String sortColumnName ){
        return customerService.getBYTierAndSort(tier,sortColumnName);
    }
    @GetMapping("/tier/{tier}/sortBy/{sortColumnName}/page-num/{pageNum}/size/{size}")
    public ResponseEntity<List<CustomerDto>> getByTierAndSortPageable(@PathVariable Integer tier,
                                                                     @PathVariable String sortColumnName,
                                                                     @PathVariable Integer pageNum,
                                                                     @PathVariable Integer size)
    {
        return customerService.getByTierAndSortPageable(tier,sortColumnName,pageNum,size);
    }
    @PostMapping("/filter")
    public ResponseEntity<List<CustomerDto>>getCustomersByFilter(@RequestBody String filterJson ){
        return customerService.getCustomersByFilter(filterJson);
    }

    @GetMapping("tier/{tier}")
    public ResponseEntity<List<CustomerDto>> getByTier(@PathVariable Integer tier,
                                                       @RequestParam Optional<String> sortBy,
                                                       @RequestParam Optional<Integer> pageNum,
                                                       @RequestParam Optional<Integer> size)
    {
        return customerService.getByTier(tier,sortBy,pageNum,size);
    }


}
