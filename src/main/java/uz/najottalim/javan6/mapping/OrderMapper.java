package uz.najottalim.javan6.mapping;

import lombok.Data;
import uz.najottalim.javan6.dto.OrderDto;
import uz.najottalim.javan6.entity.Order;

@Data
public class OrderMapper {
    public static Order toEntity(OrderDto orderDto){
        return null;
    }
    public static OrderDto toDto(Order order){
        return null;
    }
    public static OrderDto toDtoForProduct(Order order){
        return new OrderDto(order.getId(),order.getOrderDate(),order.getDeliveryDate(),order.getStatus());
    }
}
