package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.CurrentUser;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.security.UserService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.PageUserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    private final DonationService donationService;
    private final PageUserService pageUserService;

    public AdminController(DonationService donationService, PageUserService pageUserService) {
        this.donationService = donationService;
        this.pageUserService = pageUserService;

    }

    //// MODELE ----------------------------------------------------------------------------------------------------------------

    @ModelAttribute("userFullName")
    public String adminName(@AuthenticationPrincipal CurrentUser currentUser){
        return currentUser.getUser().getFullName();
    }

    @ModelAttribute("admins")
    public List<User> adminsList(){
        return pageUserService.findByRole("ROLE_ADMIN");
    }

    @ModelAttribute("users")
    public List<User> usersList(){
        return pageUserService.findByRole("ROLE_USER");
    }

    @ModelAttribute("lastDonations")
    public List<Donation> lastDonations(){
        return donationService.reversDonationList();
    }

    //// STRONY STARTOWE ----------------------------------------------------------------------------------------------------------

    @GetMapping("/")
    public String adminHome() {
        return "admin/main";
    }

    @GetMapping("/usersAdmin")
    public String manageAdminsList(@RequestParam(required = false) String error, Model model) {
        if("true".equals(error)) {
            model.addAttribute("error", "Aktualnie zalogowany administrator nie może zostać usunięty!");
            return "admin/usersAdmins/all";
        }
        return "admin/usersAdmins/all";
    }

    @GetMapping("/usersUser")
    public String manageUsersList() {
        return "admin/usersUsers/all";
    }

    //// ZARZĄDZANIE ADMINISTRATORAMI - START -----------------------------------------------------------------------------------------

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/usersAdmins/add";
    }

    @PostMapping("/add")
    public String addUserAdmin(@ModelAttribute @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/usersAdmins/add";
        }
        pageUserService.createAdmin(user);
        return "redirect:/admin/usersAdmin";
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam Long id, Model model) {
        model.addAttribute("user", pageUserService.findById(id));
        return "admin/usersAdmins/edit";
    }

    @PostMapping("/edit")
    public String editUserAdmin(@ModelAttribute @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/usersAdmins/edit";
        }
        pageUserService.commitEdit(user);
        return "redirect:/admin/usersAdmin";
    }

    @GetMapping("/editPassword")
    public String editPasswordForm(@RequestParam Long id, Model model) {
        model.addAttribute("user", pageUserService.findById(id));
        return "admin/usersAdmins/editPassword";
    }

    @PostMapping("/editPassword")
    public String editPasswordAdmin(@ModelAttribute @Valid User user, BindingResult result,
                                    @RequestParam String confirmPassword, Model model) {
        if(result.hasErrors()){
            return "admin/usersAdmins/editPassword";
        } else if (!confirmPassword.equals(user.getPassword())) {
            model.addAttribute("error", " Podane hasła nie są identyczne! ");
            return "admin/usersAdmins/editPassword";
        } else {
            pageUserService.commitPasswordEdit(user);
        }
        return "redirect:/admin/usersAdmin";
    }

    @GetMapping("/delete")
    public String deleteUserAdmin(@RequestParam Long id, Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        if(pageUserService.adminCheck(currentUser.getUser().getId(), id)) {
            return "redirect:/admin/usersAdmin?error=true";
        }
        pageUserService.deleteUser(id);
        return "redirect:/admin/usersAdmin";
    }

    //// ZARZĄDZANIE ADMINISTRATORAMI - KONIEC

    // -----------------------------------------------------------------------------------------------------------------------------------

    //// ZARZĄDZANIE UŻYTKOWNIKAMI - START

    @GetMapping("/editUser")
    public String editUserForm(@RequestParam Long id, Model model) {
        model.addAttribute("user", pageUserService.findById(id));
        return "admin/usersAdmins/edit";
    }

    @PostMapping("/editUser")
    public String editUserUser(@ModelAttribute @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/usersUsers/edit";
        }
        pageUserService.commitEdit(user);
        return "redirect:/admin/usersUser";
    }

    @GetMapping("/editUserPassword")
    public String editUserPasswordForm(@RequestParam Long id, Model model) {
        model.addAttribute("user", pageUserService.findById(id));
        return "admin/usersUsers/editPassword";
    }

    @PostMapping("/editUserPassword")
    public String editPasswordUser(@ModelAttribute @Valid User user, BindingResult result,
                                    @RequestParam String confirmPassword, Model model) {
        if(result.hasErrors()){
            return "admin/usersUsers/editPassword";
        } else if (!confirmPassword.equals(user.getPassword())) {
            model.addAttribute("error", " Podane hasła nie są identyczne! ");
            return "admin/usersUsers/editPassword";
        } else {
            pageUserService.commitPasswordEdit(user);
        }
        return "redirect:/admin/usersUser";
    }

    @GetMapping("/block")
    public String blockUser(@RequestParam Long id) {
        pageUserService.blockUser(id);
        return "redirect:/admin/usersUser";
    }

    @GetMapping("/unblock")
    public String unblockUser(@RequestParam Long id) {
        pageUserService.unblockUser(id);
        return "redirect:/admin/usersUser";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam Long id) {
        pageUserService.deleteUser(id);
        return "redirect:/admin/usersUser";
    }

    //// ZARZĄDZANIE UYTKOWNIKAMI - KONIEC

    // -----------------------------------------------------------------------------------------------------------------------------------

    //// ZARZĄDZANIE DOTACJAMI - START

    @GetMapping("/recived")
    public String recived(@RequestParam Long id) {
        donationService.recived(id);
        return "redirect:/admin/";
    }

    @GetMapping("/unclimed")
    public String unclimed(@RequestParam Long id) {
        donationService.unclimed(id);
        return "redirect:/admin/";
    }

}
