package com.log_system.order_service.Event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventGen<T> {
    private String eventId;
    private String eventType;
    private String source;
    private String version;
    private long timestamp;
    private T data;
}
