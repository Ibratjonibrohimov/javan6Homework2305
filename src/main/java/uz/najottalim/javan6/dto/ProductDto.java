package uz.najottalim.javan6.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductDto {
    private Long id;
    @NotBlank(message = "category can  not null")
    private String category;
    @NotBlank(message = "name can  not null")
    private String name;
    @NotBlank(message = "price can  not null")
    private Double price;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<OrderDto> orders;

    public ProductDto(Long id, String category, String name, Double price) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
    }
}
