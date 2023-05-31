package uz.najottalim.javan6.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.najottalim.javan6.dto.OrderDto;
import uz.najottalim.javan6.dto.ProductDto;
import uz.najottalim.javan6.dto.ProductDtoWithCount;
import uz.najottalim.javan6.service.ProductService;
import java.util.*;

@RestController
@RequestMapping("products")
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping({"/{id}"})

    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }
    @PostMapping()
    public ResponseEntity<ProductDto> addProduct(@Validated @RequestBody ProductDto productDto)  {
        return productService.addProduct(productDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@Validated @RequestBody ProductDto productDto, @PathVariable Long id) {
        return productService.updateProduct(id,productDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable Long id)  {
        return productService.deleteProduct(id);
    }
    @GetMapping("popular-currently")
    public ResponseEntity<List<ProductDtoWithCount>> getPopularCurrently(){
        return productService.getPopulerCuurently();
    }
    @GetMapping("category/{category}/sortBy/{sortColumnName}")
    public ResponseEntity<List<ProductDto>> getByCategoryAndSortBy(@PathVariable String category,@PathVariable String sortColumnName){
        return productService.getByCategoryAndSortBy(category,sortColumnName);
    }
    @GetMapping("/category/{category}/sortBy/{sortColumnName}/page-num/{pageNum}/size/{size}")
    public ResponseEntity<List<ProductDto>> getByCategoryAndSortByPageable(@PathVariable String category,
                                                                           @PathVariable String sortColumnName,
                                                                           @PathVariable Integer pageNum,
                                                                           @PathVariable Integer size)
    {
        return productService.getByCategoryAndSortByPageable(category,sortColumnName,pageNum,size);
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductDto>> getByCategory(@PathVariable String category,
                                                          @RequestParam Optional<String> sortBy,
                                                          @RequestParam Optional<Integer> pageNum,
                                                          @RequestParam Optional<Integer> size)
    {
        return productService.getByCategory(category,sortBy,pageNum,size);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> getByFilter(@RequestParam Optional<String> name,
                                                        @RequestParam Optional<List<String>> category,
                                                        @RequestParam Optional<Double> minValue,
                                                        @RequestParam Optional<Double> maxValue)
    {
        return productService.getByFilter(name,category,minValue,maxValue);
    }
}
