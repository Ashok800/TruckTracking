package com.Hackthon.coderclan.Service;

import com.Hackthon.coderclan.Entity.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
// Class
// Implementing EmailService interface
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public String sendSimpleMail(EmailDetails details) {
        FileSystemResource file = new FileSystemResource(new File("C:/Users/Dilep/Downloads/alert imagi.png"));
        try {
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            String bodyMessageInEmail=file+ "\n         tracking alert   \n"+
                                "Truck is stated at: "+details.getSourceAddress()+
                                "\n currently reached to:"+details.getTruckLocationReachedTo()+
                                "\n and will need to deliver upto:"+details.getDeliverLocation()+
                                "  and the time for reaching the destination location is :"+details.getTimeRemaining();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getTo());
//            mailMessage.setCc(details.getCc());
//            mailMessage.getBcc(details.getBcc);

            mailMessage.setText(bodyMessageInEmail);
            mailMessage.setSubject(details.getSubject());
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully to Mail id:"+details.getTo();
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            e.printStackTrace();
            return "Error while Sending Mail"+e.getMessage();
        }
    }

    // Method 2
    // To send an email with attachment
    public String
    sendMailWithAttachment(EmailDetails details)
    {
        // Creating a mime message
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            // Setting multipart as true for attachments to
            // be send
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getTo());
//            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(
                    details.getSubject());

            // Adding the attachment
            FileSystemResource file
                    = new FileSystemResource(
                    new File(details.getAttachment()));
            FileSystemResource file1 = new FileSystemResource(new File("C:/Users/Dilep/Downloads/alert imagi.png"));


            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);

            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
            return "Error while sending mail!!!"+e.getMessage();
        }
    }
}
