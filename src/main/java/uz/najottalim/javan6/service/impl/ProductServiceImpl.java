package uz.najottalim.javan6.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.najottalim.javan6.dto.ErrorDto;
import uz.najottalim.javan6.dto.ProductDto;
import uz.najottalim.javan6.entity.Product;
import uz.najottalim.javan6.exeption.NoResourceFoundException;
import uz.najottalim.javan6.mapping.ProductMapper;
import uz.najottalim.javan6.repository.ProductRepository;
import uz.najottalim.javan6.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;
    @Override
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> collect = productRepository.findAll()
                .stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }

    @Override
    public ResponseEntity<ProductDto> getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new NoResourceFoundException("No Data found");
        }
        else{
            ProductDto get = product.map(ProductMapper::toDto).get();
            return ResponseEntity.ok(get);
        }
    }

    @Override
    public ResponseEntity<ProductDto> addProduct(ProductDto productDto) {
        Product save = productRepository.save(ProductMapper.toEntity(productDto));
        ProductDto saved = ProductMapper.toDto(save);
        return ResponseEntity.ok(saved);
    }

    @Override
    public ResponseEntity<ProductDto> updateProduct(Long id, ProductDto productDto) {
        ResponseEntity<ProductDto> productById = getProductById(id);
        productDto.setId(id);
        ProductDto update = ProductMapper.toDto(productRepository.save(ProductMapper.toEntity(productDto)));
        return ResponseEntity.ok(update);
    }

    @Override
    public ResponseEntity<ProductDto> deleteProduct(Long id){
        ResponseEntity<ProductDto> productById = getProductById(id);
        productRepository.deleteById(id);
        return productById;
    }
}
