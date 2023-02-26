package com.Hackthon.coderclan.Service;

import com.Hackthon.coderclan.DTO.EmailTemplateDTO;
import com.Hackthon.coderclan.Entity.EmailDetails;
import com.Hackthon.coderclan.Repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    private  EmailRepository emailRepository;

    @Value("${spring.mail.username}")
    private String sender;

    public EmailServiceImpl(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public String sendSimpleMail(EmailTemplateDTO emailTemplateDTO) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            String bodyMessageInEmail = "\n Hi,\n\n" +
                    "\n Please find the below tracking details. \n"+
                    "Truck is stated at: " + emailTemplateDTO.getSource_address()+
                    "\nTruck is  currently reached to:" + emailTemplateDTO.getTruckLocationReachedTo() +
                    "\n Truck delivery location :" + emailTemplateDTO.getDelivery_location() +
                    "\n\n\n\n"+
                    "Thanks& Regards\n"+
                    "Ttracking Team\n"+
                    "\nNote:This is System Genereted Email PLease Do NOt Reply To This Mail";


            mailMessage.setFrom(sender);
            mailMessage.setTo(emailTemplateDTO.getWareHouse_email_id());
            mailMessage.setText(bodyMessageInEmail);
            mailMessage.setSubject("Track Trackiing Alert");
            javaMailSender.send(mailMessage);

            EmailDetails emailDetails=new EmailDetails();
            emailDetails.setWareHouse_email_id(emailTemplateDTO.getWareHouse_email_id());
            emailDetails.setSource_address(emailTemplateDTO.getSource_address());
            emailDetails.setTruckLocationReachedTo(emailTemplateDTO.getTruckLocationReachedTo());
            emailDetails.setDelivery_location(emailTemplateDTO.getDelivery_location());
            emailDetails.setWareHouse_Contact_no(emailTemplateDTO.getWareHouse_Contact_no());
            if(emailTemplateDTO.getTruckLocationReachedTo().equals(emailTemplateDTO.getDelivery_location())){
                emailDetails.setLoadingStuatus("Deliverd");
            }
            else {
                emailDetails.setLoadingStuatus("Yet to Deliver");
            }
            emailRepository.inserEmailDetails(emailDetails);
            return "Mail Sent Successfully to Mail id:" + emailTemplateDTO.getWareHouse_email_id();
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Error while Sending Mail" + e.getMessage();
        }
    }

    public Object getAllEmails() {
        try {
            return emailRepository.getAllEmailData();
        }
        catch (Exception e){
            return "error while getting the documets :"+e.getMessage();
        }


    }

    public Object getEmailById(String emailId) {
        try{
            return emailRepository.getEmailDataById(emailId);
        }
        catch (Exception e){
            return "error while gettting the document:"+e.getMessage();
        }
    }


}
