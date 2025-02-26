package ecom_ms.orderservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="orders")
public class Order {

    @Id
    private Long id;

    private Long userId;

    @ElementCollection
    @CollectionTable
    private List<Long> cartItemsId;

    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;



}
