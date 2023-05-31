package uz.najottalim.javan6.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.najottalim.javan6.dto.ProductDto;
import uz.najottalim.javan6.dto.ProductDtoWithCount;
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

    @Override
    public ResponseEntity<List<ProductDtoWithCount>> getPopulerCuurently() {
        return ResponseEntity.ok(productRepository.getPopularCurrently().stream().toList());
    }

    @Override
    public ResponseEntity<List<ProductDto>> getByCategoryAndSortBy(String category, String sortColumnName) {
        List<Product> collect = productRepository.findByCategory(category, Sort.by(sortColumnName));
        return ResponseEntity.ok(collect.stream().map(ProductMapper::toDtoWithoutOrders).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<List<ProductDto>> getByCategoryAndSortByPageable(String category, String sortColumnName, Integer pageNum, Integer size) {
        List<Product> collect = productRepository.findByCategory(category, PageRequest.of(pageNum, size, Sort.by(sortColumnName)));
        return ResponseEntity.ok(collect.stream().map(ProductMapper::toDtoWithoutOrders).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<List<ProductDto>> getByCategory(String category, Optional<String> sortBy, Optional<Integer> pageNum, Optional<Integer> size) {
        List<Product> result;
        if(pageNum.isPresent()&&size.isPresent()){
            PageRequest pageRequest = PageRequest.of(pageNum.get(),size.get());
            if(sortBy.isPresent()){
                pageRequest=pageRequest.withSort(Sort.by(sortBy.get()));
            }
            result = productRepository.findByCategory(category, pageRequest);
        }else if(sortBy.isPresent()){
            Sort sort = Sort.by(sortBy.get());
            result = productRepository.findByCategory(category,sort);
        }
        else{
            result = productRepository.findByCategory(category);
        }
        return ResponseEntity.ok(result.stream().map(ProductMapper::toDtoWithoutOrders).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<List<ProductDto>> getByFilter(Optional<String> name, Optional<List<String>> category, Optional<Double> minValue, Optional<Double> maxValue) {
        return ResponseEntity.ok(productRepository.getByFilter(name,category, minValue, maxValue));
    }

}
