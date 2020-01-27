package al.cit.supermarket.controller;

import al.cit.supermarket.service.HomeService;
import al.cit.supermarket.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/")
    public String getLandingPage(Model model){

        model.addAttribute("stores", homeService.getStoresAndProducts());
        return "index";
    }
}
