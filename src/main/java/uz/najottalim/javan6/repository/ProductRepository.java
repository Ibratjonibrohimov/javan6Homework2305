package uz.najottalim.javan6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.najottalim.javan6.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
