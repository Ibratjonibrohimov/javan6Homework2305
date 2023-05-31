package uz.najottalim.javan6.repository.extension;

import uz.najottalim.javan6.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryExtension {
    List<ProductDto> getByFilter(Optional<String> name, Optional<List<String>> category,  Optional<Double> minValue,Optional<Double> maxValue);
}
