package ecom_ms.orderservice.service;

import ecom_ms.orderservice.entity.Order;
import ecom_ms.orderservice.entity.OrderStatus;
import ecom_ms.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository=orderRepository;
    }
@Transactional
    public Order placeOrder(Long userId, List<Long> cartItemsId,Double totalAmount){
        Order order= new Order();

        order.setUserId(userId);
        order.setCartItemsId(cartItemsId);
        order.setStatus(OrderStatus.PENDING);
        order.setTotalAmount(totalAmount);

        return orderRepository.save(order);
    }

    public Order getOrderById(Long orderId){
        return orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("Order not Found!"));

    }

    public List<Order> getOrderByUserId(Long userId){
        return orderRepository.findUserByUserId(userId);
    }

    @Transactional
    public Order cancelOrder(Long orderId){
       Order orderToCancel = getOrderById(orderId);
       orderToCancel.setStatus(OrderStatus.CANCELED);

       return orderRepository.save(orderToCancel);

    }

}
