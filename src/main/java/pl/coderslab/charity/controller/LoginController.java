package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.Token;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.TokenRepository;
import pl.coderslab.charity.service.PageUserService;

import java.util.Optional;


@Controller
@RequestMapping("login")
public class LoginController {


    @GetMapping
    public String prepRegistry(@RequestParam(required = false) String error, Model model) {
        if ("true".equals(error)) {
            model.addAttribute("errorLogin", "Niepoprawny email bądź hasło!");
            model.addAttribute("errorActive", "Konto zostało zablokowane lub " +
                    "nie zostało aktywowane - sprawdź email!");
            return "login";
        } else if ("token".equals(error)) {
            model.addAttribute("errorToken", "Link do aktywacji jest niepoprawny lub wygasł!");
            return "login";
        } else if ("none".equals(error)) {
            model.addAttribute("errorNone", "Konto zostało aktywowane, możesz się zalogować!");
            return "login";
        }
        return "login";
    }
}
