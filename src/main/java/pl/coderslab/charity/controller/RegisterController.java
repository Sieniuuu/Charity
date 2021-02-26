package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.Token;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.TokenRepository;
import pl.coderslab.charity.security.UserService;
import pl.coderslab.charity.service.PageUserService;
import pl.coderslab.charity.service.TokenService;

import java.util.Optional;

@Controller
@RequestMapping("register")
public class RegisterController {

    private final UserService userService;
    private final TokenService tokenService;
    private final PageUserService pageUserService;
    private final TokenRepository tokenRepository;

    public RegisterController(UserService userService, TokenService tokenService,
                              PageUserService pageUserService, TokenRepository tokenRepository) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.pageUserService = pageUserService;
        this.tokenRepository = tokenRepository;
    }

    @GetMapping
    public String prepRegistry (Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping
    public String createDonation(@ModelAttribute @Validated({User.addUser.class}) User user, BindingResult result,
                                 @RequestParam String confirmPassword, Model model){
        if(result.hasErrors()){
            return "register";
        } else if (!confirmPassword.equals(user.getPassword())) {
            model.addAttribute("error", " Podane hasła nie są identyczne! ");
            return "register";
        } else {
            userService.saveUser(user);
            tokenService.sendActiveToken(user);
        }
        return "redirect:/login?error=process";
    }


    @GetMapping("/token")
    public String checkToken(@RequestParam String value, Model model) {
        Optional<Token> optionalToken = tokenRepository.findByValue(value);
        if (optionalToken.isPresent()) {
            Token token = optionalToken.get();
            User user = token.getUser();
            pageUserService.createUser(user);
        } else {
            return "redirect:/login?error=token";
        }
        return "redirect:/login?error=none";
    }

}
