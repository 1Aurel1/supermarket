package al.cit.supermarket.controller;

import al.cit.supermarket.service.AuthService;
import al.cit.supermarket.service.dto.auth.UserAuthenticationRequest;
import al.cit.supermarket.service.dto.auth.UserRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String login() {

        return "auth/login";
    }

    @GetMapping("/register")
    public String register(Model model) {

        UserAuthenticationRequest request = new UserAuthenticationRequest();
        model.addAttribute("user", request);
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") UserRegistrationRequest request) {

        authService.createUser(request);
        return "auth/login";
    }
}
