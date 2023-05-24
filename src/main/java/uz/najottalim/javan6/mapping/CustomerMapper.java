package uz.najottalim.javan6.mapping;

import lombok.Data;
import uz.najottalim.javan6.dto.CustomerDto;
import uz.najottalim.javan6.entity.Customer;

import java.util.stream.Collectors;

@Data
public class CustomerMapper {
    public static CustomerDto toDto(Customer customer){
        return new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getTier(),
                customer.getOrders()==null?null:customer.getOrders()
                        .stream()
                        .map(OrderMapper::toDtoForOther)
                        .collect(Collectors.toList())
        );
    }
    public static Customer toEntity(CustomerDto customerDto){
        return new Customer(customerDto.getId(),customerDto.getName(),customerDto.getTier());
    }
}
