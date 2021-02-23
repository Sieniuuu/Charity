package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.service.PageUserService;



@Controller
@RequestMapping("login")
public class LoginController {

    private PageUserService pageUserService;

    public LoginController(PageUserService pageUserService) {
        this.pageUserService = pageUserService;
    }

    @GetMapping
    public String prepRegistry (@RequestParam(required = false) String error, Model model) {
        if("true".equals(error)) {
           model.addAttribute("error", "Niepoprawny email bądź hasło!");
           return "login";
        }
        return "login";
    }

//    @PostMapping
//    public String successLogin(@AuthenticationPrincipal CurrentUser currentUser, @RequestParam String email, @RequestParam String password, Model model){
//        if(!userService.authenticateCheck(email, password)) {
//            model.addAttribute("error", "error");
//            return "login";
//        }
//        if (userService.rolesCheck(currentUser.getUser())){
//            return "redirect:/admin/";
//        }
//        return "redirect:/user/";
//    }
}
