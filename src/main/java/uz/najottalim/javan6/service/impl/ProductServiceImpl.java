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
    public List<ProductDto> getAllProducts() {
         return productRepository.findAll()
                 .stream()
                 .map(ProductMapper::toDto)
                 .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new NoResourceFoundException("No Data found");
        }
        else{
            return product.map(ProductMapper::toDto).get();
        }
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) throws Exception{
        Product save = productRepository.save(ProductMapper.toEntity(productDto));
        return ProductMapper.toDto(save);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) throws Exception{
        ProductDto productById = getProductById(id);
        productById.setCategory(productDto.getCategory());
        productById.setName(productDto.getName());
        productById.setPrice(productDto.getPrice());
        return ProductMapper.toDto(productRepository.save(ProductMapper.toEntity(productById)));
    }

    @Override
    public ProductDto deleteProduct(Long id)throws Exception {
        ProductDto productById = getProductById(id);
        productRepository.deleteById(id);
        return productById;
    }
}
