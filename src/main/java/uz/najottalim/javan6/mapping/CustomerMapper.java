package uz.najottalim.javan6.mapping;

import lombok.Data;
import uz.najottalim.javan6.dto.CustomerDto;
import uz.najottalim.javan6.entity.Customer;

import java.util.stream.Collectors;

@Data
public class
CustomerMapper {
    public static CustomerDto toDto(Customer customer){
        return new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getTier(),
                customer.getOrders()==null?null:customer.getOrders()
                        .stream()
                        .map(OrderMapper::toDtoWithoutCustomerAndProducts)
                        .collect(Collectors.toList())
        );
    }
    public static Customer toEntity(CustomerDto customerDto){
        if (customerDto == null)return null;
        return new Customer(customerDto.getId(),customerDto.getName(),customerDto.getTier());
    }
    public static Customer toEntityForUpdate(CustomerDto customerDto){
        if(customerDto == null ) return null;
        return new Customer(
                customerDto.getId(),
                customerDto.getName(),
                customerDto.getTier()
        );
    }
    public static CustomerDto toDtoWithoutOrders(Customer customer){
        if (customer == null) return null;
        return new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getTier()
        );
    }
}
