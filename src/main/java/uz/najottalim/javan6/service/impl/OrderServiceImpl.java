package uz.najottalim.javan6.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.najottalim.javan6.dto.OrderDto;
import uz.najottalim.javan6.entity.Order;
import uz.najottalim.javan6.exeption.NoResourceFoundException;
import uz.najottalim.javan6.mapping.OrderMapper;
import uz.najottalim.javan6.repository.OrderRepository;
import uz.najottalim.javan6.service.OrderService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Override
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> collect = orderRepository.findAll()
                .stream()
                .map(OrderMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }

    @Override
    public ResponseEntity<OrderDto> getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isEmpty())throw new NoResourceFoundException("No order found");
        return ResponseEntity.ok(OrderMapper.toDto(order.get()));
    }

    @Override
    public ResponseEntity<OrderDto> addOrder(OrderDto orderDto) {
        OrderDto saved = OrderMapper.toDto(orderRepository.save(OrderMapper.toEntity(orderDto)));
        return ResponseEntity.ok(saved);
    }

    @Override
    public ResponseEntity<OrderDto> updateOrder(Long id, OrderDto orderDto) {
        ResponseEntity<OrderDto> orderById = getOrderById(id);
        orderDto.setId(id);
        OrderDto update = OrderMapper.toDto(orderRepository.save(OrderMapper.toEntity(orderDto)));
        return ResponseEntity.ok(update);
    }

    @Override
    public ResponseEntity<OrderDto> deleteOrder(Long id) {
        ResponseEntity<OrderDto> orderById = getOrderById(id);
        orderRepository.deleteById(id);
        return orderById;
    }

}
