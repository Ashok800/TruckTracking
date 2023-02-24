package com.Hackthon.coderclan.Service;

import com.Hackthon.coderclan.Entity.EmailDetails;

public interface EmailService {
    String sendSimpleMail(EmailDetails details);

    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details);
}
