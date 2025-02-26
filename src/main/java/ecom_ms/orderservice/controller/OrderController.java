package ecom_ms.orderservice.controller;

import ecom_ms.orderservice.entity.Order;
import ecom_ms.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order placeOrder(@RequestParam Long userId, @RequestBody List<Long> cartItemIds, @RequestParam Double totalAmount){
        return orderService.placeOrder(userId,cartItemIds,totalAmount);

    }
    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Long orderId){
        return orderService.getOrderById(orderId);
    }
    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable Long userId){
        return orderService.getOrderByUserId(userId);
    }

    @PutMapping("/{orderId}/cancel")
    public Order cancelOrder(@PathVariable Long orderId){
        return orderService.cancelOrder(orderId);
    }
}
