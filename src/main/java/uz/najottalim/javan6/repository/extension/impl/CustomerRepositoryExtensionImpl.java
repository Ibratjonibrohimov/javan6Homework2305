package uz.najottalim.javan6.repository.extension.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import uz.najottalim.javan6.dto.CustomerDto;
import uz.najottalim.javan6.entity.Customer;
import uz.najottalim.javan6.entity.Order;
import uz.najottalim.javan6.mapping.CustomerMapper;
import uz.najottalim.javan6.repository.extension.CustomerRepositoryExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CustomerRepositoryExtensionImpl implements CustomerRepositoryExtension {
    private  final EntityManager entityManager;
    @Override
    public List<CustomerDto> getCustomersByFilter(Map<String, Object> criteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> queryRoot = query.from(Customer.class);

        List<Predicate> predicates = new ArrayList<>();

        criteria.forEach(
                (column, value) -> {
                    if(value instanceof String stringValue){
                        Predicate like = criteriaBuilder.like(queryRoot.get(column), "%"+stringValue+"%");
                        predicates.add(like);
                    } else if (value instanceof Number n) {
                        Predicate equal = criteriaBuilder.equal(queryRoot.get(column), n);
                        predicates.add(equal);
                    } else if (value instanceof LocalDate date) {
                        CriteriaBuilder criteriaBuilderOrder = entityManager.getCriteriaBuilder();
                        CriteriaQuery<Order> queryOrder = criteriaBuilderOrder.createQuery(Order.class);
                        Root<Order> queryRootOrder = queryOrder.from(Order.class);
                        Predicate p = criteriaBuilderOrder.equal(queryRootOrder.get(column), date);
                        predicates.add(p);
                    }
                }
        );
        query.select(queryRoot).where(criteriaBuilder.and(predicates.toArray(Predicate[]::new)));
        TypedQuery<Customer> result = entityManager.createQuery(query);
        return result.getResultList().stream().map(
                CustomerMapper::toDtoWithoutOrders
        ).collect(Collectors.toList());
    }
}
