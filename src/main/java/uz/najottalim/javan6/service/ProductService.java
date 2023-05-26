package uz.najottalim.javan6.service;

import org.springframework.http.ResponseEntity;
import uz.najottalim.javan6.dto.ProductDto;
import uz.najottalim.javan6.dto.ProductDtoWithCount;

import java.util.List;

public interface ProductService {
    ResponseEntity<List<ProductDto>> getAllProducts();

    ResponseEntity<ProductDto> getProductById(Long id);

    ResponseEntity<ProductDto> addProduct(ProductDto productDto) ;

    ResponseEntity<ProductDto> updateProduct(Long id, ProductDto productDto) ;

    ResponseEntity<ProductDto> deleteProduct(Long id) ;

    ResponseEntity<List<ProductDtoWithCount>> getPopulerCuurently();
}
