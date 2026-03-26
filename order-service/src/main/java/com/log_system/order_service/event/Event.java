package com.log_system.order_service.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event<T> {
    private String eventId;
    private String eventType;
    private String source;
    private String version;
    private long timestamp;
    private T data;
}
