package uz.najottalim.javan6.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.najottalim.javan6.dto.OrderDto;

import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;
    private String name;
    private Double price;
    @ManyToMany(mappedBy = "products")
    List<Order> orders;

    public Product(Long id, String category, String name, Double price) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
    }
}
