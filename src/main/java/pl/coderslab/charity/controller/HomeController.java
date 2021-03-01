package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.email.MailService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import javax.mail.MessagingException;


@Controller
public class HomeController {

    private InstitutionService institutionService;
    private DonationService donationService;
    private MailService mailService;

    public HomeController(InstitutionService institutionService, DonationService donationService, MailService mailService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.mailService = mailService;
    }


    @GetMapping("/")
    public String homeAction(Model model) {
        model.addAttribute("institution", institutionService.findAll());
        model.addAttribute("sumDonationsQuantity", donationService.sumQuantity());
        model.addAttribute("sumDonations", donationService.countAll());
        return "index";
    }

    @PostMapping("/message")
    public String contact(@RequestParam String name, @RequestParam String surname, @RequestParam String message) {
        try {
            mailService.sendMail("chairity@chairity.pl",
                    "Wiadomość od - " + name + " " + surname,
                    message, false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

}
