package uz.najottalim.javan6.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private Long id;
    @NotBlank(message = "name can not null")
    private String name;
    @Max(value = 5,message = "max tier is 5")
    @Min(value = 1,message = "min tier is 1")
    private Integer tier;
    private List<OrderDto> orders;

    public CustomerDto(Long id, String name, Integer tier) {
        this.id = id;
        this.name = name;
        this.tier = tier;
    }
}
