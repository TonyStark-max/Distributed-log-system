package com.log_system.storage_service.Service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.log_system.storage_service.document.LogDocument;
import com.log_system.storage_service.event.Event;
import com.log_system.storage_service.event.OrderData;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderConsumer {

    private final LogService logService;
    private final ObjectMapper mapper;

    @KafkaListener(topics = "orders-topic",groupId = "storage-group")
    public void consume(Event event){
        OrderData orderData=
                mapper.convertValue(event.getData(), OrderData.class);

        LogDocument doc= null;
        try {
            doc = new LogDocument(
                    event.getEventId(),
                    event.getEventType(),
                    event.getSource(),
                    event.getTimestamp(),
                    mapper.writeValueAsString(orderData)
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        logService.index(doc);

        System.out.println("Indexed: "+orderData.getOrderId());
    }
}
