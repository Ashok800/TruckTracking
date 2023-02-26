package com.Hackthon.coderclan.Service;

import com.Hackthon.coderclan.DTO.EmailTemplateDTO;
import com.Hackthon.coderclan.Entity.EmailDetails;

public interface EmailService {
    String sendSimpleMail(EmailTemplateDTO emailTemplateDTO);
}
