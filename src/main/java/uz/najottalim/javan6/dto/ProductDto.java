package uz.najottalim.javan6.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductDto {
    private Long id;
    private String category;
    private String name;
    private Double price;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<OrderDto> orders;
}
