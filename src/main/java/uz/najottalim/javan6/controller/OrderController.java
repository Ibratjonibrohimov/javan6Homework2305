package uz.najottalim.javan6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.najottalim.javan6.dto.OrderDto;
import uz.najottalim.javan6.dto.OrderDtoForHighCost;
import uz.najottalim.javan6.service.OrderService;

import java.util.*;

@RestController
@RequestMapping("orders")
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping()
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        return orderService.getAllOrders();
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }
    @PostMapping()
    public ResponseEntity<OrderDto> addOrder(@Validated@RequestBody OrderDto orderDto){
        return orderService.addOrder(orderDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@Validated@RequestBody OrderDto orderDto,@PathVariable Long id){
        return orderService.updateOrder(id,orderDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDto> deleteOrder(@PathVariable Long id){
        return orderService.deleteOrder(id);
    }

    @GetMapping("/high-cost")
    public ResponseEntity<List<OrderDtoForHighCost>> getHighCost(){
        return orderService.getHighCost();
    }

}
