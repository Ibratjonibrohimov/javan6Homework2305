package uz.najottalim.javan6.dto;

import lombok.Builder;
import lombok.Data;

import java.util.*;

@Data
@Builder
public class ErrorDtoMap {
    private Map<String,List<String>> errors;

}
