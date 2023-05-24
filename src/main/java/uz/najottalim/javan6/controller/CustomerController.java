package uz.najottalim.javan6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.najottalim.javan6.dto.CustomerDto;
import uz.najottalim.javan6.service.CustomerService;
import java.util.*;

@RestController
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @GetMapping()
    public List<CustomerDto> getAllCustomers(){
        return customerService.getAllCustomers();
    }
    @GetMapping("/{id}")
    public CustomerDto getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }
    @PostMapping()
    public CustomerDto addCustomer(@RequestBody CustomerDto customerDto) throws Exception {
        return customerService.addCustomer(customerDto);
    }
    @PutMapping("/{id}")
    public CustomerDto updateCustomer(@RequestBody CustomerDto customerDto,@PathVariable Long id)throws Exception{
        return customerService.upadtecustomer(id,customerDto);
    }
    @DeleteMapping("/{id}")
    public CustomerDto deleteCustomer(@PathVariable Long id)throws Exception{
        return customerService.deleteCustomer(id);
    }
}
