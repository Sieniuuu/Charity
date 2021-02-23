package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.security.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String prepRegistry (Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping
    public String createDonation(@ModelAttribute @Valid User user, BindingResult result, @RequestParam String confirmPassword, Model model){
        if(result.hasErrors()){
            return "register";
        } else if (!confirmPassword.equals(user.getPassword())) {
            model.addAttribute("error", " Podane hasła nie są identyczne! ");
            return "register";
        } else {
            userService.saveUser(user);
        }
        return "redirect:/login";
    }
}
