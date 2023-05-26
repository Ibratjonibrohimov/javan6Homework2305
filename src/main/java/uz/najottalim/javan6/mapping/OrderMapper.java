package uz.najottalim.javan6.mapping;

import lombok.Data;
import uz.najottalim.javan6.dto.OrderDto;
import uz.najottalim.javan6.entity.Order;

import java.util.stream.Collectors;

@Data
public class OrderMapper {
    public static Order toEntity(OrderDto orderDto)
    {
        if (orderDto == null) return null;
        return new Order(
                orderDto.getId(),
                orderDto.getOrderDate(),
                orderDto.getDeliveryDate(),
                orderDto.getStatus(),
                orderDto.getCustomerDto()==null?null:CustomerMapper.toEntity(orderDto.getCustomerDto()),
                orderDto.getProducts() == null? null :orderDto.getProducts()
                        .stream()
                        .map(ProductMapper::toEntity)
                        .collect(Collectors.toList()));
    }
    public static OrderDto toDto(Order order){
        if(order == null)return null;
        return new OrderDto(
                order.getId(),
                order.getOrderDate(),
                order.getDeliveryDate(),
                order.getStatus(),
                order.getCustomer() == null ? null : CustomerMapper.toDtoWithoutOrders(order.getCustomer()),
                order.getProducts() == null ? null : order.getProducts()
                        .stream()
                        .map(ProductMapper::toDtoWithoutOrders)
                        .collect(Collectors.toList())
        );
    }
    public static OrderDto toDtoWithoutCustomerAndProducts(Order order){
        if (order == null) return null;
        return new OrderDto(order.getId(),order.getOrderDate(),order.getDeliveryDate(),order.getStatus());
    }
}
