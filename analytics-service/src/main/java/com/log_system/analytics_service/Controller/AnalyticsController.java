package com.log_system.analytics_service.Controller;

import com.log_system.analytics_service.Service.AnalyticsConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor
public class AnalyticsController {
    private final StringRedisTemplate redisTemplate;

    @GetMapping("/data")
    public Map<String,String> stats(){
        return Map.of(
                "totalEvents", redisTemplate.opsForValue().get("totalEvents"),
                "orderCreated", redisTemplate.opsForValue().get("orderCreated")
        );
    }
}
