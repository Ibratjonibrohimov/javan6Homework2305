package uz.najottalim.javan6.service;

import org.springframework.http.ResponseEntity;
import uz.najottalim.javan6.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long id);

    ProductDto addProduct(ProductDto productDto) throws Exception;

    ProductDto updateProduct(Long id, ProductDto productDto) throws Exception;

    ProductDto deleteProduct(Long id) throws Exception;
}
