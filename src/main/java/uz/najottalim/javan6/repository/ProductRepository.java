package uz.najottalim.javan6.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import uz.najottalim.javan6.dto.ProductDtoWithCount;
import uz.najottalim.javan6.entity.Product;
import uz.najottalim.javan6.repository.extension.ProductRepositoryExtension;

import java.util.*;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>, ProductRepositoryExtension {
    @Query(
            value = "select opr.product_id id,p.category,p.name,p.price , count(opr.product_id) count from product p\n" +
                    "    join order_product_relationship opr\n" +
                    "        on p.id = opr.product_id\n" +
                    "    join product_order po\n" +
                    "        on opr.order_id = po.id\n" +
                    "    where po.order_date\n" +
                    "          between\n" +
                    "              (select max(order_date) from product_order)-interval '1 months'\n" +
                    "          and\n" +
                    "                (select max(order_date) from product_order)\n" +
                    "    group by opr.product_id,p.category,p.name,p.price\n" +
                    "    order by count desc\n" +
                    "    limit 10;",nativeQuery = true
    )
    Collection<ProductDtoWithCount> getPopularCurrently();


    List<Product> findByCategory(String category, Sort sort);
    List<Product> findByCategory(String category, PageRequest pageRequest);
    List<Product> findByCategory(String category);
}
