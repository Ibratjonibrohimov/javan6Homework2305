package uz.najottalim.javan6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.najottalim.javan6.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
