package al.cit.supermarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("users/")
public class UserController {

    @GetMapping("dashboard")
    public String getUserDashboard(){

        return null;
    }
}
