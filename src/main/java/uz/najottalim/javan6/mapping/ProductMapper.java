package uz.najottalim.javan6.mapping;

import lombok.Data;
import uz.najottalim.javan6.dto.ProductDto;
import uz.najottalim.javan6.entity.Product;

import java.util.stream.Collectors;

@Data
public class ProductMapper {
    public static Product toEntity(ProductDto productDto){
        return new Product(productDto.getId(),productDto.getCategory(), productDto.getName(), productDto.getPrice());
    }
    public static ProductDto toDto(Product product){
        return new
                ProductDto(
                    product.getId(),
                    product.getCategory(),
                    product.getName(),
                    product.getPrice(),
                    product.getOrders()==null?null:product.getOrders()
                            .stream()
                            .map(OrderMapper::toDtoForProduct)
                            .collect(Collectors.toList())
        );
    }

}
