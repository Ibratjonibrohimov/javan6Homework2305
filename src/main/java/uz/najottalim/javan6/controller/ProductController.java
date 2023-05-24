package uz.najottalim.javan6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.najottalim.javan6.dto.ProductDto;
import uz.najottalim.javan6.service.ProductService;
import java.util.*;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping()
    public List<ProductDto> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping({"/{id}"})
    public ProductDto getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }
    @PostMapping()
    public ProductDto addProduct(@RequestBody ProductDto productDto) throws Exception {
        return productService.addProduct(productDto);
    }
    @PutMapping("/{id}")
    public ProductDto updateProduct(@RequestBody ProductDto productDto,@PathVariable Long id) throws Exception {
        return productService.updateProduct(id,productDto);
    }
    @DeleteMapping("/{id}")
    public ProductDto deleteProduct(@PathVariable Long id) throws Exception {
        return productService.deleteProduct(id);
    }
}
