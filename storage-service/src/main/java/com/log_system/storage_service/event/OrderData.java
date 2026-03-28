package com.log_system.storage_service.event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderData {
    private String orderId;
    private String userId;
    private double amount;
    private String status;
}
