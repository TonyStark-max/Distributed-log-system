package com.log_system.order_service.Service;


import com.log_system.order_service.event.Event;
import com.log_system.order_service.event.OrderData;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderProducer {
    private final KafkaTemplate<String, Event<OrderData>> kafkaTemplate;

    public void sendOrderCreatedEvent(OrderData orderData){

        Event<OrderData> event=new Event<>(
        UUID.randomUUID().toString(),
                "order_created",
                "order_service",
                "v1",
                System.currentTimeMillis(),
                orderData
        );

        kafkaTemplate.send("orders-topic",orderData.getOrderId(),event);
    }
}
