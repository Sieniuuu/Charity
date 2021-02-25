package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.*;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("donation")
public class DonationController {

    private CategoryService categoryService;
    private InstitutionService institutionService;
    private DonationService donationService;

    public DonationController(CategoryService categoryService, InstitutionService institutionService,
                              DonationService donationService) {
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @ModelAttribute("userFullName")
    public String adminName(@AuthenticationPrincipal CurrentUser currentUser){
        if(currentUser == null) {
            return "";
        }
        return currentUser.getUser().getFullName();
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.findAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> institutions() {
        return institutionService.findAll();
    }

    //// FORM BEZ ZALOGOWANIA ------------------------------------------------------------------------------------------

    @GetMapping("/form")
    public String prepCreateDonation(Model model) {
        model.addAttribute("donation", new Donation());
        return "formDonation";
    }

    @PostMapping("/form")
    public String createDonation(@ModelAttribute @Validated({Donation.addDonation.class}) Donation donation,
                                 BindingResult result){
        if(result.hasErrors()){
            return "formDonation";
        }
        donationService.createDonation(donation);
        return "form-confirmation";
    }

    //// FORM BEZ ZALOGOWANIA KONIEC -----------------------------------------------------------------------------------


    //// FORM Z ZALOGOWANIEM -------------------------------------------------------------------------------------------

    @GetMapping("/addDonate")
    public String prepCreateDonationWithUser(Model model) {
        model.addAttribute("donation", new Donation());
        return "formDonation";
    }

    @PostMapping("/addDonate")
    public String createDonationWithUser(@ModelAttribute @Validated({Donation.addDonation.class}) Donation donation,
                                 BindingResult result, @AuthenticationPrincipal CurrentUser currentUser){
        if(result.hasErrors()){
            return "formDonation";
        }
        donationService.createDonationWithUser(donation, currentUser.getUser());
        return "redirect:/user/";
    }

    @GetMapping("/recived")
    public String recived(@RequestParam Long id) {
        donationService.recived(id);
        return "redirect:/user/";
    }

    @GetMapping("/unclimed")
    public String unclimed(@RequestParam Long id) {
        donationService.unclimed(id);
        return "redirect:/user/";
    }

    @GetMapping("/details")
    public String details(@RequestParam Long id, Model model) {
        model.addAttribute("donation", donationService.findById(id));
        return "user/donationDetails";
    }


}





