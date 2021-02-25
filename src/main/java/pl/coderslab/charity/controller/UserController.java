package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.CurrentUser;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.DonationSortService;
import pl.coderslab.charity.service.PageUserService;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    private final PageUserService pageUserService;
    private final DonationSortService donationSortService;

    public UserController(PageUserService pageUserService, DonationSortService donationSortService) {
        this.pageUserService = pageUserService;
        this.donationSortService = donationSortService;
    }

    @ModelAttribute("userFullName")
    public String userName(@AuthenticationPrincipal CurrentUser currentUser){
        return currentUser.getUser().getFullName();
    }

    @GetMapping("/")
    public String userHome (@AuthenticationPrincipal CurrentUser currentUser,
                            @RequestParam(required = false) String searchMode, Model model) {
        model.addAttribute("donations", donationSortService.executeQuery(searchMode, currentUser.getUser()));
        return "user/main";
    }

    @GetMapping("/editDetails")
    public String editForm(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("user", pageUserService.findById(currentUser.getUser().getId()));
        return "user/edit";
    }

    @PostMapping("/editDetails")
    public String editUserDetails(@ModelAttribute @Validated({User.editUser.class}) User user, BindingResult result, @RequestParam String email,
                                  @AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if (result.hasErrors()) {
            return "user/edit";
        }
        pageUserService.commitEdit(user);
        return "redirect:/user/";
    }

    @GetMapping("/editPassword")
    public String editPasswordForm(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("user", pageUserService.findById(currentUser.getUser().getId()));
        return "user/editPassword";
    }

    @PostMapping("/editPassword")
    public String createDonation(@ModelAttribute @Validated({User.editUser.class}) User user, BindingResult result,
                                 @RequestParam String confirmPassword, Model model){
        if(result.hasErrors()){
            return "user/editPassword";
        } else if (!confirmPassword.equals(user.getPassword())) {
            model.addAttribute("error", " Podane hasła nie są identyczne! ");
            return "user/editPassword";
        } else {
            pageUserService.commitPasswordEdit(user);
        }
        return "redirect:/user/";
    }

    @GetMapping("/editEmail")
    public String editEmailForm(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("user", pageUserService.findById(currentUser.getUser().getId()));
        return "user/editEmail";
    }

    @PostMapping("/editEmail")
    public String editUserEmail(@ModelAttribute @Validated({User.addUser.class}) User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user/editEmail";
        }
        pageUserService.commitEdit(user);
        return "redirect:/user/";
    }

}
