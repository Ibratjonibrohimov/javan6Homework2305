package uz.najottalim.javan6.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "order date can  not be null")
    private LocalDate orderDate;
    @NotBlank(message = "delivery date can  not be null")
    private LocalDate deliveryDate;
    @NotBlank(message = "status can  not be null")
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CustomerDto customerDto;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ProductDto> products;

    public OrderDto(Long id, LocalDate orderDate, LocalDate deliveryDate, String status) {
        this.id = id;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }
}
