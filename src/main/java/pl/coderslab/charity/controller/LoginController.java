package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.Token;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.service.PageUserService;
import pl.coderslab.charity.service.TokenService;

import java.util.Optional;


@Controller
@RequestMapping("login")
public class LoginController {

    private final PageUserService pageUserService;
    private final TokenService tokenService;

    public LoginController(PageUserService pageUserService, TokenService tokenService) {
        this.pageUserService = pageUserService;
        this.tokenService = tokenService;
    }

    @GetMapping
    public String prepRegistry(@RequestParam(required = false) String error, Model model) {
        if (StringUtils.isEmpty(error)) {
            return "login";
        }
        switch (error) {
            case "true":
                model.addAttribute("errorLogin", "Niepoprawny email bądź hasło - spróbuj jeszcze raz!");
                model.addAttribute("errorActive", "Konto mogło również zostać nieaktywowane lub zablokowane!");
                break;
            case "token":
                model.addAttribute("errorToken", "Link do aktywacji jest niepoprawny lub wygasł!");
                break;
            case "process":
                model.addAttribute("errorNone", "Link do aktywacji konta zostały wysłany na Twojego maila!");
                break;
            case "none":
                model.addAttribute("errorNone", "Konto zostało aktywowane, możesz się zalogować!");
                break;
            case "passChanged":
                model.addAttribute("errorNone", "Hasło zostało zmienione, możesz się zalogować!");
                break;
        }
        return "login";
    }


    @GetMapping("/passwordReset")
    public String passwordResetForm() {
        return "passwordReset";
    }

    @PostMapping("/passwordReset")
    public String passwordReset(@RequestParam(required = false) String username, Model model) {
        User user = pageUserService.findByEmail(username);
        if (StringUtils.isEmpty(username) || user == null) {
            model.addAttribute("error", "Podany email nie istnieje w bazie!");
            return "passwordReset";
        }
        tokenService.sendPasswordResetToken(user);
        model.addAttribute("succes", "Link do resetu hasła został wysłany na Twojego maila!");
        return "passwordReset";
    }


    @GetMapping("/changePassword")
    public String changePasswordForm(@RequestParam String value, Model model) {
        Optional<Token> optionalToken = tokenService.findByValue(value);
        if (optionalToken.isPresent()) {
            Token token = optionalToken.get();
            User user = token.getUser();
            model.addAttribute("user", user);
            return "passwordChange";
        }
        model.addAttribute("error", "Link do resetu hasła jest niepoprawny lub wygasł!");
        return "passwordChange";
    }

    @PostMapping("/changePassword")
    public String changePassword(@ModelAttribute @Validated({User.editUserPassword.class}) User user,
                                 BindingResult result, @RequestParam String confirmPassword,
                                 @RequestParam String password, Model model) {
        if(result.hasErrors()) {
            return "passwordChange";
        } else if (!confirmPassword.equals(password)) {
            model.addAttribute("error", "Podany hasła nie są indentyczne!");
            return "passwordChange";
        }
        pageUserService.commitEditPassword(user, confirmPassword);
        return "redirect:/login?error=passChanged";
    }

}
