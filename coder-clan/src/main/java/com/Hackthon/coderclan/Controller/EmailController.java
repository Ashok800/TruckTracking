package com.Hackthon.coderclan.Controller;

import com.Hackthon.coderclan.Entity.EmailDetails;
import com.Hackthon.coderclan.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private  JavaMailSender sender;

    @PostMapping("/sendMail")
    public ResponseEntity<String> sendmail(@RequestBody EmailDetails details){
        return ResponseEntity.status(HttpStatus.OK).body(emailService.sendSimpleMail(details));
    }


    // Sending email with attachment
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(@RequestBody EmailDetails details) {
        String status = emailService.sendMailWithAttachment(details);
        return status;
    }

    @RequestMapping("/sendEMail")
    public String sendMail(@RequestBody EmailDetails details) {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(details.getTo());
//            helper.setText(details.getMsgBody());
            helper.setSubject(details.getSubject());
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error while sending mail ..";
        }
        sender.send(message);
        return "Mail Sent Success! to :"+details.getTo();
    }
}
