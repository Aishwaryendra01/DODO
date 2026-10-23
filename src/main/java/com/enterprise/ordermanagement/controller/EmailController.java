//package com.enterprise.ordermanagement.controller;
//
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import com.enterprise.ordermanagement.service.EmailService;
//
//import lombok.RequiredArgsConstructor;
//
//@RestControllerAdvice
//@RequestMapping("/api/email")
//@RequiredArgsConstructor
//public class EmailController {
//
//    public EmailController(EmailService emailService) {
//		super();
//		this.emailService = emailService;
//	}
//
//	private final EmailService emailService;
//
//    @PostMapping("/send")
//    public String sendEmail() {
//
//        return emailService.sendEmail(
//                "a.usepurpose@gmail.com",
//                "use2purpose@gmail.com",
//                "Order Service Test",
//                "Hello! This email was sent from my Spring Boot application."
//        );
//    }
//}