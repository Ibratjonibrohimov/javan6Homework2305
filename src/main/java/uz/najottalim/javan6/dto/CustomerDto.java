package uz.najottalim.javan6.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CustomerDto {
    private Long id;

    private String name;
    private Integer tier;
}
