package uz.najottalim.javan6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.najottalim.javan6.dto.OrderDtoForHighCost;
import uz.najottalim.javan6.entity.Order;

import java.util.Collection;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query(
            value = "select opr.order_id id,po.order_date orderDate,po.status,po.customer_id customerId from  product p\n" +
                    "    join order_product_relationship opr\n" +
                    "        on p.id = opr.product_id\n" +
                    "    join product_order po\n" +
                    "        on opr.order_id = po.id\n" +
                    "    where po.order_date\n" +
                    "        between\n" +
                    "            (select max(order_date) from product_order)-interval '1 months'\n" +
                    "        and\n" +
                    "            (select max(order_date) from product_order)\n" +
                    "    group by opr.order_id,po.order_date,po.customer_id,po.status\n" +
                    "    order by sum(p.price) desc",
            nativeQuery = true
    )
    Collection<OrderDtoForHighCost> getHighCost();
}
