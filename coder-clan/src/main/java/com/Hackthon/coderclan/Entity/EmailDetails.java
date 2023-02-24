package com.Hackthon.coderclan.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

// Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor

// Class
public class EmailDetails {

    // Class data members
    private String  to;
    private String cc;
    private String subject;
    private String attachment;

    private String sourceAddress;
    private String truckLocationReachedTo;
    private String deliverLocation;
    private String timeRemaining;
}
