package com.log_system.storage_service.document;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogDocument {
    private String eventId;
    private String eventType;
    private String source;
    private long timestamp;
    private String data;
}
