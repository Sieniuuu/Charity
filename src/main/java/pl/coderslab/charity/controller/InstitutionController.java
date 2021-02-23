package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.CurrentUser;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.InstitutionService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("institution")
public class InstitutionController {

    private final InstitutionService institutionService;

    public InstitutionController(InstitutionService institutionService) {
        this.institutionService = institutionService;

    }

    @ModelAttribute("adminFullName")
    public String adminName(@AuthenticationPrincipal CurrentUser currentUser) {
        return currentUser.getUser().getFullName();
    }

    @ModelAttribute("allInstitutions")
    public List<Institution> lastDonations() {
        return institutionService.findAll();
    }

    @GetMapping("/all")
    public String manageList() {
        return "admin/institution/all";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("institution", new Institution());
        return "admin/institution/add";
    }

    @PostMapping("/add")
    public String addInstitution(@ModelAttribute @Valid Institution institution, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/institution/add";
        }
        institutionService.createInstitution(institution);
        return "redirect:/institution/all";
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam Long id, Model model) {
        model.addAttribute("institution", institutionService.findById(id));
        return "admin/institution/edit";
    }

    @PostMapping("/edit")
    public String editInstitution(@ModelAttribute @Valid Institution institution, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/institution/edit";
        }
        institutionService.createInstitution(institution);
        return "redirect:/institution/all";
    }


    @GetMapping("/delete")
    public String deleteCar(@RequestParam Long id) {
        institutionService.deleteInstitution(id);
        return "redirect:/institution/all";
    }
}
