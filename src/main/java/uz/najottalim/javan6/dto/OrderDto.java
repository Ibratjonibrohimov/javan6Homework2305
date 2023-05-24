package uz.najottalim.javan6.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import uz.najottalim.javan6.entity.Customer;
import uz.najottalim.javan6.entity.Product;

import java.time.LocalDate;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderDto {
    private Long id;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Customer customer;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Product> products;

    public OrderDto(Long id, LocalDate orderDate, LocalDate deliveryDate, String status) {
        this.id = id;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }
}
