package com.Hackthon.coderclan.Controller;

import com.Hackthon.coderclan.DTO.EmailTemplateDTO;
import com.Hackthon.coderclan.Service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmailController {
    @Autowired
    private  EmailServiceImpl emailService;

    @PostMapping("/sendMail")
    public ResponseEntity sendmail(@RequestBody EmailTemplateDTO emailTemplateDTO){
//        for (EmailTemplateDTO emailTemplateDTO:emailTemplateRequestDTO.getEmailTemplate()){
//            emailService.sendSimpleMail(emailTemplateDTO);
//        }
        emailService.sendSimpleMail(emailTemplateDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/getAllEmail")
    public ResponseEntity getAllEmails(){
        return ResponseEntity.status(HttpStatus.OK).body(emailService.getAllEmails());
    }

    @GetMapping("getEmail/{emailId}")
    public  ResponseEntity getEmailById(@PathVariable String emailId){
        return  ResponseEntity.status(HttpStatus.OK).body(emailService.getEmailById(emailId));
    }
}
