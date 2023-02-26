package com.Hackthon.coderclan.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailTemplateDTO {
    private String  wareHouse_email_id;
    private String source_address;
    private String truckLocationReachedTo;
    private String delivery_location;
    private String timeRemaining;
    private String wareHouse_Contact_no;
    private  String loadingStuatus;

}
