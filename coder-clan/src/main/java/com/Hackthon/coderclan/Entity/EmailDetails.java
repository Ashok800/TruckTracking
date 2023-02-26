package com.Hackthon.coderclan.Entity;

import lombok.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

// Annotations
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailDetails {

    private  String email_id;
    private String  wareHouse_email_id;

    private String wareHouse_Contact_no;
    private String source_address;
    private String delivery_location;
    private String truckLocationReachedTo;
    private  String loadingStuatus;
}
