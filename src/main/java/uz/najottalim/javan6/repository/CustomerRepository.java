package uz.najottalim.javan6.repository;

import jakarta.persistence.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.najottalim.javan6.dto.CustomerDto;
import uz.najottalim.javan6.dto.CustomerDtoForMostValuable;
import uz.najottalim.javan6.entity.Customer;
import uz.najottalim.javan6.repository.extension.CustomerRepositoryExtension;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.*;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> , CustomerRepositoryExtension {
    @Query(
            value = "select po.customer_id id,c.name,c.tier,sum(p.price) payedPrice\n" +
                    "    from customer c join product_order po on c.id = po.customer_id\n" +
                    "    join order_product_relationship opr\n" +
                    "        on po.id = opr.order_id\n" +
                    "    join product p\n" +
                    "        on p.id = opr.product_id\n" +
                    "    where po.order_date\n" +
                    "          between\n" +
                    "              (select max(order_date) from product_order)-interval '1 months'\n" +
                    "          and\n" +
                    "          (select max(order_date) from product_order)\n" +
                    "    group by po.customer_id,c.name,c.tier\n" +
                    "    order by payedPrice desc",
            nativeQuery = true
    )
    Collection<CustomerDtoForMostValuable> getMostValuable();
    List<Customer> findByTier(Integer tier, Sort sort);
    List<Customer> findByTier(Integer tier, PageRequest pageRequest);
    List<Customer> findByTier(Integer tier);
}
