package uz.najottalim.javan6.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerFilterDto {
    private String name;
    private Integer tier;
    private LocalDate orderDate;
}
