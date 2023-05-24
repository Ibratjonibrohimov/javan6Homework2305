package uz.najottalim.javan6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.najottalim.javan6.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
