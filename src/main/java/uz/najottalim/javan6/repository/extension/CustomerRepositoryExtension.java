package uz.najottalim.javan6.repository.extension;
import uz.najottalim.javan6.dto.CustomerDto;

import java.util.*;
public interface CustomerRepositoryExtension {
    List<CustomerDto> getCustomersByFilter(Map<String,Object> criteria);


}
