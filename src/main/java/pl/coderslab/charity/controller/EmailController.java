package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/email")
public class EmailController {

//    private EmailSendService emailSendService;
//    private CustomerRepository customerRepository;
//    private UserRepository userRepository;
//
//    public EmailController(EmailSendService emailSendService, CustomerRepository customerRepository, UserRepository userRepository) {
//        this.emailSendService = emailSendService;
//        this.customerRepository = customerRepository;
//        this.userRepository = userRepository;
//    }
//
//
//    @GetMapping("/sendOfferDetail/{userId}/{customerId}")
//    public String sendOfferDetail(@PathVariable long userId, @PathVariable long customerId) {
//        Customer customer = customerRepository.findById(customerId).get();
//        if (customer.getOffer() == null || customer.getCar() == null) {
//            return "redirect:http://localhost:8080/email/emailAlert/" + userId;
//        } else {
//            emailSendService.sendOfferDetail(customerId, userId);
//            return "redirect:http://localhost:8080/worker/" + userId;
//        }
//    }
//
//    @GetMapping("/emailAlert/{userId}")
//    public String emailAlert(Model model, @PathVariable long userId) {
//        model.addAttribute("user", userRepository.findById(userId).get());
//        return "worker/emailAlert";
//    }
//
//    @GetMapping("/sendOfferFleet/{userId}/{customerId}")
//    public String sendOfferFleet(@PathVariable long userId, @PathVariable long customerId) {
//        Customer customer = customerRepository.findById(customerId).get();
//        if (customer.getOffer() == null || customer.getCar() == null) {
//            return "redirect:http://localhost:8080/email/emailAlert/" + userId;
//        } else {
//            emailSendService.sendOfferFleet(customerId, userId);
//            return "redirect:http://localhost:8080/worker/" + userId;
//        }
//    }
}

