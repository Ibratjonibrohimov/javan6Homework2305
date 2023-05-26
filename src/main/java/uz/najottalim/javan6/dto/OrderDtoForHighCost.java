package uz.najottalim.javan6.dto;

import java.time.LocalDate;

public interface OrderDtoForHighCost {
    Long getId();
    LocalDate getOrderDate();
    String getStatus();
    Long getCustomerId();

}
