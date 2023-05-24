package uz.najottalim.javan6.mapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.najottalim.javan6.dto.CustomerDto;
import uz.najottalim.javan6.entity.Customer;

@Data
public class CustomerMapper {
    public static CustomerDto toDto(Customer customer){
        return null;
    }
    public static Customer toEntity(CustomerDto customerDto){
        return null;
    }
}
