package top.zxk.springboot.configure.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.zxk.springboot.configure.entity.Mail;
import top.zxk.springboot.configure.service.EmailService;

@RestController
@AllArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/mail")
    public void sendMail(@RequestBody Mail mail){
        emailService.sendSimpleMail(mail.getTo(), mail.getSubject(), mail.getBody());
    }
}
