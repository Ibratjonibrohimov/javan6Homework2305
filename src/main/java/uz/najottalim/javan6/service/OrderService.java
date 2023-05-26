package uz.najottalim.javan6.service;

import org.springframework.http.ResponseEntity;
import uz.najottalim.javan6.dto.OrderDto;
import uz.najottalim.javan6.dto.OrderDtoForHighCost;

import java.util.List;

public interface OrderService {
    ResponseEntity<List<OrderDto>> getAllOrders();

    ResponseEntity<OrderDto> getOrderById(Long id);

    ResponseEntity<OrderDto> addOrder(OrderDto orderDto);

    ResponseEntity<OrderDto> updateOrder(Long id, OrderDto orderDto);

    ResponseEntity<OrderDto> deleteOrder(Long id);

    ResponseEntity<List<OrderDtoForHighCost>> getHighCost();
}
