package com.log_system.analytics_service.Service;

import com.log_system.analytics_service.event.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalyticsConsumer {

    private long totalEvents=0;
    private long orderCount=0;
    private final StringRedisTemplate redisTemplate;

    @KafkaListener(topics = "orders-topic", groupId = "analytics-group")
    public void consume(Event event){
        redisTemplate.opsForValue().increment("totalEvents");

        if("order_created".equalsIgnoreCase(event.getEventType())){
            redisTemplate.opsForValue().increment("orderCreated");
        }
    }
}
