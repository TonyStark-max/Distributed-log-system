package com.log_system.order_service.Controller;


import com.log_system.order_service.Event.OrderData;
import com.log_system.order_service.Service.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/orders/v1")
@RequiredArgsConstructor
public class OrderController {

    private final OrderProducer orderProducer;

    @PostMapping("/orderCreation")
    public String createOrder(){
        OrderData order=new OrderData(
                UUID.randomUUID().toString(),
                "user-1",
                500,
                "CREATED"
        );
        orderProducer.sendOrderCreatedEvent(order);
        return "Order created and sent";
    }
}
