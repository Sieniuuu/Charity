package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;


@Controller
public class HomeController {

   private InstitutionService institutionService;
   private DonationService donationService;

    public HomeController(InstitutionService institutionService, DonationService donationService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
    }


    @GetMapping("/")
    public String homeAction(Model model){
        model.addAttribute("institution", institutionService.findAll());
        model.addAttribute("sumDonationsQuantity", donationService.sumQuantity());
        model.addAttribute("sumDonations", donationService.countAll());

        return "index";
    }




}
