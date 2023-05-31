package uz.najottalim.javan6.repository.extension.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import uz.najottalim.javan6.dto.ProductDto;
import uz.najottalim.javan6.entity.Product;
import uz.najottalim.javan6.mapping.ProductMapper;
import uz.najottalim.javan6.repository.extension.ProductRepositoryExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ProductRepositoryExtensionImpl implements ProductRepositoryExtension {
    private final EntityManager entityManager;

    @Override
    public List<ProductDto> getByFilter(Optional<String> name, Optional<List<String>> category, Optional<Double> minValue, Optional<Double> maxValue) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> queryRoot = query.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();
        List<Predicate> predicatesForCategory = new ArrayList<>();
        name.ifPresent(value->{predicates.add(criteriaBuilder.like(queryRoot.get("name"),"%"+value+"%"));});
        category.ifPresent(values->{
            values.forEach(
                            value->{predicatesForCategory.add(criteriaBuilder.like(queryRoot.get("category"),"%"+value+"%"));}
                    );
            predicates.add(criteriaBuilder.or(predicatesForCategory.toArray(Predicate[]::new)));
        }
        );
        maxValue.ifPresent(value->{predicates.add(criteriaBuilder.lessThanOrEqualTo(queryRoot.get("price"),value));});
        minValue.ifPresent(value->{predicates.add(criteriaBuilder.greaterThanOrEqualTo(queryRoot.get("price"),value));});

        TypedQuery<Product> result = entityManager.createQuery(query.where(predicates.toArray(Predicate[]::new)));
        return result.getResultList().stream().map(ProductMapper::toDtoWithoutOrders).collect(Collectors.toList());
    }
}
